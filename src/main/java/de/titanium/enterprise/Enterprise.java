package de.titanium.enterprise;

import com.github.zafarkhaja.semver.Version;
import de.SweetCode.SweetDB.SweetDB;
import de.titanium.enterprise.Achievment.Achievement;
import de.titanium.enterprise.Achievment.AchievementManager;
import de.titanium.enterprise.Achievment.Achievements;
import de.titanium.enterprise.Data.DataContainer.*;
import de.titanium.enterprise.Data.DataManager;
import de.titanium.enterprise.Entity.EntityGenerator;
import de.titanium.enterprise.GameUtils.*;
import de.titanium.enterprise.Loading.LoadingManager;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Texture;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.DefaultMenu;
import de.titanium.enterprise.View.FightView.FightMenu;
import de.titanium.enterprise.View.FightView.FightView;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.GameMenu.HeroesView;
import de.titanium.enterprise.View.GameMenu.ScoreView;
import de.titanium.enterprise.View.GameMenu.SettingsView.SettingsView;
import de.titanium.enterprise.View.GameView;
import de.titanium.enterprise.View.LoadingView.LoadingView;
import de.titanium.enterprise.View.SkillView.SkillView;
import de.titanium.enterprise.View.StoryView.StoryView;
import de.titanium.enterprise.View.ViewManager;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;

public class Enterprise {

    private final Logger logger = Logger.getLogger("Enterprise");

    private List<GameComponent> gameComponents = new ArrayList<>();
    private final Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
    private final int MAX_TICKS = 50;

    // Manager
    private final GameView gameView = new GameView();
    private final ViewManager viewManager = new ViewManager();
    private final KeyManager keyManager = new KeyManager();
    private final DataManager dataManager = new DataManager();
    private final LoadingManager loadingManager = new LoadingManager();
    private final TextBuilder textBuilder = new TextBuilder();
    private final AchievementManager achievementManager = new AchievementManager();
    private final EntityGenerator entityGenerator = new EntityGenerator();

    // Database
    private final DataContainers dataContainers = new DataContainers();

    private final SweetDB database;

    private static Enterprise game;

    public Enterprise(final Version latestVersion, boolean updateAvailable, URL latestJar) {

        Enterprise.game = this;


        // Datenbank
        this.database = new SweetDB(Game.getGameFolder().getAbsolutePath(), "entityTypes", "achievements", "settings", "highscores");
        this.database.debugging(true);
        try {
            this.database.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //GameUtils State
        this.dataManager.set("game.state", GameState.POSTING);

        //Adding loading screen
        this.viewManager.register(new LoadingView());
        this.viewManager.switchView(LoadingView.class);

        // DataContainer
        this.dataContainers.add(new EntityContainer());
        this.dataContainers.add(new AchievementContainer());
        this.dataContainers.add(new SettingsContainer());
        this.dataContainers.add(new HighscoreContainer());

        //managing loading
        this.dataManager.set("game.state", GameState.LOADING);
        this.loadingManager.add(new GameUpdate(latestJar));
        this.loadingManager.add(this.dataContainers.values());
        this.loadingManager.add(Textures.values());
        this.loadingManager.add(Animations.values());

        this.loadingManager.load();

        //default menu
        DefaultMenu defaultMenu = new DefaultMenu();

        //default view
        this.viewManager.register(new GameMenuView(defaultMenu));
        this.viewManager.register(new SettingsView(defaultMenu));
        this.viewManager.register(new StoryView(defaultMenu));
        this.viewManager.register(new SkillView(defaultMenu));
        this.viewManager.register(new HeroesView(defaultMenu));
        this.viewManager.register(new FightView(new FightMenu()));
        this.viewManager.register(new ScoreView(defaultMenu));

        this.viewManager.switchView(GameMenuView.class);

        // @Achievement
        this.achievementManager.add(Achievements.WELCOME);

        if(updateAvailable) {
            this.achievementManager.add(new Achievement() {

                @Override
                public String getName() {
                    return String.format("Version %s ist da.", latestVersion.toString());
                }

                @Override
                public String getDescription() {
                    return "Update available.";
                }

                @Override
                public Texture getTexture() {
                    return Textures.ACHIEVEMENT_ICON_WOODS;
                }

            }, true, true);
        }

        //start game
        this.start();
    }

    /**
     * Gibt das aktulle Spiel zurueck.
     * @return
     */
    public static Enterprise getGame() {
        return Enterprise.game;
    }

    public Logger getLogger() {
        return this.logger;
    }

    /**
     * Diese Methode gibt alle RenderingHints zurueck, die global beim Rendern genutzt werden sollen.
     * @return
     */
    public Map<RenderingHints.Key, Object> getRenderingHints() {
        return this.renderingHints;
    }

    /**
     * Diese Methode fuegt dem Update-'n-Render-Loop ein neue Komponente hinzu, diese wird im naechsten durchlauf des Loops beruecksichtigt.
     * @param gameComponent
     */
    public void addComponent(GameComponent gameComponent) {
        this.gameComponents.add(gameComponent);
    }

    /**
     * Diese Methode startet den GameUtils-Loop.
     *
     * Der Loop ist aktuell auf 50 Ticks/Sekunde festgesetzt, es sollten also immer 50 Ticks ausgefuehrt werden.
     *
     */
    public void start() {

        // Den GameUtils-Loop starten
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

    /**
     * Gibt den ViewManager der aktuellen Instanz zurueck.
     * @return
     */
    public ViewManager getViewManager() {
        return this.viewManager;
    }

    /**
     * Gibt die aktuelle GameView zurueck.
     * @return
     */
    public GameView getGameView() {
        return this.gameView;
    }

    /**
     * Gibt den atkuellen KeyManager zurueck.
     * @return
     */
    public KeyManager getKeyManager() {
        return this.keyManager;
    }

    /**
     * Gibt den aktuellen DataManager zurueck.
     * @return
     */
    public DataManager getDataManager() {
        return this.dataManager;
    }

    /**
     * Gibt den aktuellen LoadingManager zurueck.
     * @return
     */
    public LoadingManager getLoadingManager() {
        return this.loadingManager;
    }

    /**
     * Gibt den aktuellen TextBuilder zurueck.
     * @return
     */
    public TextBuilder getTextBuilder() {
        return this.textBuilder;
    }

    /**
     * Gibt den aktuellen AchievementManager zurueck.
     * @return
     */
    public AchievementManager getAchievementManager() {
        return this.achievementManager;
    }

    /**
     * Gibt den aktuellen EntityGenerator zurueck.
     * @return
     */
    public EntityGenerator getEntityGenerator() {
        return this.entityGenerator;
    }

    /**
     * Gibt die aktuelle Datenbank zurueck.
     * @return
     */
    public SweetDB getDatabase() {
        return this.database;
    }

    /**
     * Gibt die aktullen DataContainers zurueck.
     * @return
     */
    public DataContainers getDataContainers() {
        return this.dataContainers;
    }

}
