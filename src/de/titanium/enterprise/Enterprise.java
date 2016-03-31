package de.titanium.enterprise;

import de.SweetCode.SweetDB.SweetDB;
import de.titanium.enterprise.Achievment.AchievementManager;
import de.titanium.enterprise.Achievment.Achievements;
import de.titanium.enterprise.Data.DataContainer.AchievementContainer;
import de.titanium.enterprise.Data.DataContainer.DataContainers;
import de.titanium.enterprise.Data.DataContainer.EnemyTypesContainer;
import de.titanium.enterprise.Data.DataManager;
import de.titanium.enterprise.Entity.EntityGenerator;
import de.titanium.enterprise.Loading.LoadingManager;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.DefaultMenu;
import de.titanium.enterprise.View.FightView.FightMenu;
import de.titanium.enterprise.View.FightView.FightView;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.GameView;
import de.titanium.enterprise.View.HeroesView.HeroesView;
import de.titanium.enterprise.View.LoadingView.LoadingView;
import de.titanium.enterprise.View.SettingsView.SettingsView;
import de.titanium.enterprise.View.SkillView.SkillView;
import de.titanium.enterprise.View.StoryView.StoryView;
import de.titanium.enterprise.View.ViewManager;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by 204g01 on 07.03.2016.
 */
public class Enterprise {

    private final Logger logger = Logger.getLogger("Enterprise");

    private List<GameComponent> gameComponents = new ArrayList<>();
    private final Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
    private final int MAX_TICKS = 50;

    {

        this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        this.renderingHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        this.renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        this.renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        this.renderingHints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        this.renderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

    }

    // Manager
    private final GameView gameView = new GameView();
    private final ViewManager viewManager = new ViewManager();
    private final KeyManager keyManager = new KeyManager();
    private final DataManager dataManager = new DataManager();
    private final LoadingManager loadingManager = new LoadingManager();
    private final TextBuilder textBuilder = new TextBuilder();
    private final AchievementManager achievementManager = new AchievementManager();
    private final EntityGenerator entityGenerator = new EntityGenerator();
    private final DataContainers dataContainers = new DataContainers();

    private final SweetDB database;

    private static Enterprise game;

    public Enterprise() {

        Enterprise.game = this;

        // Datenbank
        File path = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Enterprise-Game");
        if(!(path.exists())) {
            path.mkdirs();
        }

        this.database = new SweetDB(path.getAbsolutePath(), "entityTypes", "achievements");
        try {
            this.database.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Game State
        this.dataManager.set("game.state", GameState.POSTING);

        //Adding loading screen
        this.viewManager.register(new LoadingView());
        this.viewManager.switchTo(LoadingView.class);

        // DataContainer
        this.dataContainers.add(new EnemyTypesContainer());
        this.dataContainers.add(new AchievementContainer());

        //managing loading
        this.dataManager.set("game.state", GameState.LOADING);
        this.loadingManager.add(Textures.values());
        this.loadingManager.add(Animations.values());
        this.loadingManager.add(this.dataContainers.values());
        this.loadingManager.load();

        //set default enemy
        this.getDataManager().set("game.enemy", this.entityGenerator.generate(1));

        //default menu
        DefaultMenu defaultMenu = new DefaultMenu();

        //default view
        this.viewManager.register(new GameMenuView(defaultMenu));
        this.viewManager.register(new SettingsView(defaultMenu));
        this.viewManager.register(new StoryView(defaultMenu));
        this.viewManager.register(new SkillView(defaultMenu));
        this.viewManager.register(new HeroesView(defaultMenu));
        this.viewManager.register(new FightView(new FightMenu()));

        this.viewManager.switchTo(GameMenuView.class);

        // @Achievement
        this.achievementManager.add(Achievements.WELCOME);

        //start game
        this.start();
    }

    /**
     * Gibt das aktulle Spiel zur�ck.
     * @return
     */
    public static Enterprise getGame() {
        return Enterprise.game;
    }

    public Logger getLogger() {
        return this.logger;
    }

    /**
     * Diese Methode gibt alle RenderingHints zur�ck, die global beim Rendern genutzt werden sollen.
     * @return
     */
    public Map<RenderingHints.Key, Object> getRenderingHints() {
        return this.renderingHints;
    }

    public static void main(String[] args) {

        new Enterprise();

    }

    /**
     * Diese Methode f�gt dem Update-'n-Render-Loop ein neue Komponente hinzu, diese wird im n�chsten durchlauf des Loops ber�cksichtigt.
     * @param gameComponent
     */
    public void addComponent(GameComponent gameComponent) {
        this.gameComponents.add(gameComponent);
    }

    /**
     * Diese Methode startet den Game-Loop.
     *
     * Der Loop ist aktuell auf 50 Ticks/Sekunde festgesetzt, es sollten also immer 50 Ticks ausgef�hrt werden.
     *
     */
    public void start() {

        int CURRENT_TICK = 1;
        this.dataManager.set("game.state", GameState.WAITING);

        while (true) {

            double currentTime = System.currentTimeMillis();

            Iterator<GameComponent> updateComponents = Arrays.asList(this.gameComponents.toArray(new GameComponent[this.gameComponents.size()]).clone()).iterator();

            while (updateComponents.hasNext()) {

                this.dataManager.set("game.state", GameState.UPDATE);
                GameComponent component = updateComponents.next();

                if (component.isActive()) {
                    component.update(CURRENT_TICK);
                }

            }

            CURRENT_TICK++;
            if (CURRENT_TICK > MAX_TICKS) {
                CURRENT_TICK = 1;
            }


            Iterator<GameComponent> renderComponents = this.gameComponents.iterator();

            while(renderComponents.hasNext()) {

                this.dataManager.set("game.state", GameState.RENDER);
                GameComponent component = renderComponents.next();

                if(component.isActive()) {
                    component.render();
                }

            }

            this.dataManager.set("game.state", GameState.WAITING);
            try {
                long sleep = (long) (1000 / MAX_TICKS - (currentTime - System.currentTimeMillis()));

                if(sleep > 0) {
                    Thread.sleep(sleep);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public ViewManager getViewManager() {
        return this.viewManager;
    }

    public GameView getGameView() {
        return this.gameView;
    }

    public KeyManager getKeyManager() {
        return this.keyManager;
    }

    public DataManager getDataManager() {
        return this.dataManager;
    }

    public LoadingManager getLoadingManager() {
        return this.loadingManager;
    }

    public TextBuilder getTextBuilder() {
        return this.textBuilder;
    }

    public AchievementManager getAchievementManager() {
        return this.achievementManager;
    }

    public EntityGenerator getEntityGenerator() {
        return this.entityGenerator;
    }

    public SweetDB getDatabase() {
        return database;
    }

    public DataContainers getDataContainers() {
        return this.dataContainers;
    }
}
