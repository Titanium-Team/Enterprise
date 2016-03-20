package de.titanium.enterprise;

import de.titanium.enterprise.Data.DataManager;
import de.titanium.enterprise.KeyManager.KeyManager;
import de.titanium.enterprise.Loading.LoadingManager;
import de.titanium.enterprise.Sprite.Animation.Animations;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.FightView.FightMenu;
import de.titanium.enterprise.View.FightView.FightView;
import de.titanium.enterprise.View.GameView;
import de.titanium.enterprise.View.LoadingView.LoadingView;
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

    {

        this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

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

        //Adding loading screen
        this.viewManager.register(new LoadingView());
        this.viewManager.switchTo(LoadingView.class);

        //managing loading
        this.loadingManager.add(Textures.values());
        this.loadingManager.add(Animations.values());
        this.loadingManager.load();

        //default view
        this.viewManager.register(new FightView(new FightMenu()));
        this.viewManager.switchTo(FightView.class);

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
     * Der Loop ist aktuell auf 50 Ticks/Sekunde festgesetzt, es sollten also immer 20 Ticks ausgeführt werden.
     * Falls es zu einer Verzögerung im Code kommt, werden die verpassten Ticks nachgeholt, um den Rhytmus aufrechtzuerhalten.
     *
     */
    public void start() {

        int MAX_TICKS = 50;
        int CURRENT_TICK = 0;

        while (true) {

            double currentTime = System.currentTimeMillis();

            Iterator<GameComponent> updateComponents = Arrays.asList(this.gameComponents.toArray(new GameComponent[this.gameComponents.size()]).clone()).iterator();
            while (updateComponents.hasNext()) {

                GameComponent component = updateComponents.next();

                if (component.isActive()) {
                    component.update(CURRENT_TICK);
                }

            }

            CURRENT_TICK++;
            if (CURRENT_TICK > MAX_TICKS) {
                CURRENT_TICK = 0;
            }


            Iterator<GameComponent> renderComponents = this.gameComponents.iterator();

            while(renderComponents.hasNext()) {

                GameComponent component = renderComponents.next();

                if(component.isActive()) {
                    component.render();
                }

            }

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
