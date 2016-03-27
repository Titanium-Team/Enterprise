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

/**
 * Created by 204g01 on 07.03.2016.
 */
public class Enterprise {

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
        this.dataManager.add("game.state", GameState.POSTING);

        //Adding loading screen
        this.viewManager.register(new LoadingView());
        this.viewManager.switchTo(LoadingView.class);

        //managing loading
        this.dataManager.add("game.state", GameState.LOADING);
        this.loadingManager.add(Textures.values());
        this.loadingManager.add(Animations.values());
        this.loadingManager.load();


        //set default heroes
        this.getDataManager().add("game.heroes", new LivingEntity[] {

                new Archer(UUID.randomUUID(), "Archer", 100, 100, 3, 7, 20),
                new Warrior(UUID.randomUUID(), "Warrior", 100, 100, 4, 6, 22),
                new Rogue(UUID.randomUUID(), "Rogue", 100, 100, 7, 5, 18)

        });


        //set default enemy
        this.getDataManager().add("game.enemy", new Archer(UUID.randomUUID(), "Enemy", 10, 100, 5, 5, 12));

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
     * Gibt das aktulle Spiel zur�ck.
     * @return
     */
    public static Enterprise getGame() {
        return Enterprise.game;
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
        this.dataManager.add("game.state", GameState.WAITING);

        while (true) {

            double currentTime = System.currentTimeMillis();

            Iterator<GameComponent> updateComponents = Arrays.asList(this.gameComponents.toArray(new GameComponent[this.gameComponents.size()]).clone()).iterator();

            while (updateComponents.hasNext()) {

                this.dataManager.add("game.state", GameState.UPDATE);
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

                this.dataManager.add("game.state", GameState.RENDER);
                GameComponent component = renderComponents.next();

                if(component.isActive()) {
                    component.render();
                }

            }

            this.dataManager.add("game.state", GameState.WAITING);
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
