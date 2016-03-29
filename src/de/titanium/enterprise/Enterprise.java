package de.titanium.enterprise;

import de.titanium.enterprise.Data.DataManager;
import de.titanium.enterprise.Entity.Entities.Archer;
import de.titanium.enterprise.Entity.Entities.Rogue;
import de.titanium.enterprise.Entity.Entities.Warrior;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.KeyManager.KeyManager;
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

    private final GameView gameView = new GameView();
    private final ViewManager viewManager = new ViewManager();
    private final KeyManager keyManager = new KeyManager();
    private final DataManager dataManager = new DataManager();
    private final LoadingManager loadingManager = new LoadingManager();
    private final TextBuilder textBuilder = new TextBuilder();

    private static Enterprise game;

    public Enterprise() {

        Enterprise.game = this;

        //Game State
        this.dataManager.set("game.state", GameState.POSTING);

        //Adding loading screen
        this.viewManager.register(new LoadingView());
        this.viewManager.switchTo(LoadingView.class);

        //managing loading
        this.dataManager.set("game.state", GameState.LOADING);
        this.loadingManager.add(Textures.values());
        this.loadingManager.add(Animations.values());
        this.loadingManager.load();

        //set default hero types
        this.getDataManager().set("game.heroes.types", new LivingEntity[]{

                new Archer(UUID.randomUUID(), "Robin Trump", 40, 40, 6, 8, 0),
                new Archer(UUID.randomUUID(), "Georg von Wald", 60, 60, 3, 3, 0),
                new Archer(UUID.randomUUID(), "Eddy Penny", 52, 52, 5, 6, 0),
                new Archer(UUID.randomUUID(), "Tromo Domo", 20, 20, 4, 10, 0),
                new Archer(UUID.randomUUID(), "Ranger Ben", 33, 33, 15, 20, 0),

                new Warrior(UUID.randomUUID(), "Big Meyer", 120, 120, 0, 2, 0),
                new Warrior(UUID.randomUUID(), "Sir Isaac", 80, 80, 0, 3, 0),
                new Warrior(UUID.randomUUID(), "Robby Flobby", 100, 100, 0, 2, 10),
                new Warrior(UUID.randomUUID(), "Lord Washington", 60, 60, 0, 4, 0),
                new Warrior(UUID.randomUUID(), "Ben Jerry", 70, 70, 0, 3, 5),

                new Rogue(UUID.randomUUID(), "Sneaky Pete", 20, 20, 7, 14, 0),
                new Rogue(UUID.randomUUID(), "Chacky Chan", 12, 12, 5, 14, 0),
                new Rogue(UUID.randomUUID(), "The Knife", 10, 10, 8, 20, 0),
                new Rogue(UUID.randomUUID(), "Robert Rice", 30, 30, 5, 8, 0),
                new Rogue(UUID.randomUUID(), "Sam Dodge", 15, 15, 10, 22, 0),

        });

        //set default heroes
        this.getDataManager().set("game.heroes", new LivingEntity[]{

                this.getDataManager().<LivingEntity[]>get("game.heroes.types")[0],
                this.getDataManager().<LivingEntity[]>get("game.heroes.types")[5],
                this.getDataManager().<LivingEntity[]>get("game.heroes.types")[10]

        });

        //set default enemy
        this.getDataManager().set("game.enemy", new Archer(UUID.randomUUID(), "Enemy", 10, 100, 5, 5, 12));

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

        //start game
        this.start();
    }

    /**
     * Gibt das aktulle Spiel zurück.
     * @return
     */
    public static Enterprise getGame() {
        return Enterprise.game;
    }

    public Logger getLogger() {
        return this.logger;
    }

    /**
     * Diese Methode gibt alle RenderingHints zurück, die global beim Rendern genutzt werden sollen.
     * @return
     */
    public Map<RenderingHints.Key, Object> getRenderingHints() {
        return this.renderingHints;
    }

    public static void main(String[] args) {

        new Enterprise();

    }

    /**
     * Diese Methode fügt dem Update-'n-Render-Loop ein neue Komponente hinzu, diese wird im nächsten durchlauf des Loops berücksichtigt.
     * @param gameComponent
     */
    public void addComponent(GameComponent gameComponent) {
        this.gameComponents.add(gameComponent);
    }

    /**
     * Diese Methode startet den Game-Loop.
     *
     * Der Loop ist aktuell auf 50 Ticks/Sekunde festgesetzt, es sollten also immer 50 Ticks ausgeführt werden.
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
}
