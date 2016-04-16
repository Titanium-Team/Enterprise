package de.titanium.enterprise;

import de.SweetCode.SweetDB.SweetDB;
import de.titanium.enterprise.Achievment.AchievementManager;
import de.titanium.enterprise.Achievment.Achievements;
import de.titanium.enterprise.Data.DataContainer.*;
import de.titanium.enterprise.Data.DataManager;
import de.titanium.enterprise.Entity.EntityGenerator;
import de.titanium.enterprise.Loading.LoadingManager;
import de.titanium.enterprise.Sound.SoundPlayer;
import de.titanium.enterprise.Sound.Sounds;
import de.titanium.enterprise.Sprite.Animation.Animations;
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
import java.io.File;
import java.io.IOException;
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

    // Music
    private final SoundPlayer soundPlayer = new SoundPlayer();

    // Database
    private final DataContainers dataContainers = new DataContainers();

    private final SweetDB database;

    private static Enterprise game;

    {

        this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        this.renderingHints.put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        this.renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        this.renderingHints.put(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        this.renderingHints.put(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
        this.renderingHints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

    }

    public Enterprise() {

        Enterprise.game = this;

        // Datenbank
        File path = new File(System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "Enterprise-Game");
        if(!(path.exists())) {
            path.mkdirs();
        }

        this.database = new SweetDB(path.getAbsolutePath(), "entityTypes", "achievements", "settings", "highscores");
        this.database.debugging(true);
        try {
            this.database.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Game State
        this.dataManager.set("game.state", GameState.POSTING);

        //Adding loading screen
        this.viewManager.register(new LoadingView());
        this.viewManager.switchView(LoadingView.class);

        // DataContainer
        this.dataContainers.add(new EntitiesContainer());
        this.dataContainers.add(new AchievementContainer());
        this.dataContainers.add(new SettingsContainer());
        this.dataContainers.add(new HighscoreContainer());

        //managing loading
        this.dataManager.set("game.state", GameState.LOADING);
        this.loadingManager.add(this.dataContainers.values());
        this.loadingManager.add(Textures.values());
        this.loadingManager.add(Animations.values());
        this.loadingManager.add(Sounds.values());

        this.loadingManager.load();

        // Den Music Thread starten
        this.soundPlayer.add(Sounds.MUSIC_ONE);
        this.soundPlayer.start();

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

    public static void main(String[] args) {

        new Enterprise();

    }

    /**
     * Diese Methode fuegt dem Update-'n-Render-Loop ein neue Komponente hinzu, diese wird im naechsten durchlauf des Loops beruecksichtigt.
     * @param gameComponent
     */
    public void addComponent(GameComponent gameComponent) {
        this.gameComponents.add(gameComponent);
    }

    /**
     * Diese Methode startet den Game-Loop.
     *
     * Der Loop ist aktuell auf 50 Ticks/Sekunde festgesetzt, es sollten also immer 50 Ticks ausgefuehrt werden.
     *
     */
    public void start() {

        // Den Game-Loop starten
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
     * Gibt den ViewManager der aktuellen Instanz zur�ck.
     * @return
     */
    public ViewManager getViewManager() {
        return this.viewManager;
    }

    /**
     * Gibt die aktuelle GameView zur�ck.
     * @return
     */
    public GameView getGameView() {
        return this.gameView;
    }

    /**
     * Gibt den atkuellen KeyManager zur�ck.
     * @return
     */
    public KeyManager getKeyManager() {
        return this.keyManager;
    }

    /**
     * Gibt den aktuellen DataManager zur�ck.
     * @return
     */
    public DataManager getDataManager() {
        return this.dataManager;
    }

    /**
     * Gibt den aktuellen LoadingManager zur�ck.
     * @return
     */
    public LoadingManager getLoadingManager() {
        return this.loadingManager;
    }

    /**
     * Gibt den aktuellen TextBuilder zur�ck.
     * @return
     */
    public TextBuilder getTextBuilder() {
        return this.textBuilder;
    }

    /**
     * Gibt den aktuellen AchievementManager zur�ck.
     * @return
     */
    public AchievementManager getAchievementManager() {
        return this.achievementManager;
    }

    /**
     * Gibt den aktuellen EntityGenerator zur�ck.
     * @return
     */
    public EntityGenerator getEntityGenerator() {
        return this.entityGenerator;
    }


    /**
     * Gibt den aktuellen SoundPlayer zur�ck.
     * @return
     */
    public SoundPlayer getSoundPlayer() {
        return this.soundPlayer;
    }

    /**
     * Gibt die aktuelle Datenbank zur�ck.
     * @return
     */
    public SweetDB getDatabase() {
        return this.database;
    }

    /**
     * Gibt die aktullen DataContainers zur�ck.
     * @return
     */
    public DataContainers getDataContainers() {
        return this.dataContainers;
    }

}
