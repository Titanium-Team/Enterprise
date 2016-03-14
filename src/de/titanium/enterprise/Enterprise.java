package de.titanium.enterprise;

import de.titanium.enterprise.KeyManager.KeyManager;
import de.titanium.enterprise.View.GameView;
import de.titanium.enterprise.View.ViewManager;
import de.titanium.enterprise.View.DefenseGame.DefenseMenu;
import de.titanium.enterprise.View.Views.FightMenu;
import de.titanium.enterprise.View.Views.FightView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 204g01 on 07.03.2016.
 */
public class Enterprise {

    private List<GameComponent> gameComponents = new ArrayList<>();

    private final GameView gameView = new GameView();
    private final ViewManager viewManager = new ViewManager(this);
    private final KeyManager keyManager = new KeyManager();

    private static Enterprise game;

    public Enterprise() {

        Enterprise.game = this;

        this.viewManager.register(new FightView(new DefenseMenu()));
        this.viewManager.switchTo(FightView.class);

        this.start();

    }

    /**
     * Gibt das aktulle Spiel zurück.
     * @return
     */
    public static Enterprise getGame() {
        return Enterprise.game;
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

        double lastTime = System.currentTimeMillis();
        int MAX_TICKS = 50;
        int CURRENT_TICK = 0;

        long a = System.currentTimeMillis();

        while (true) {

            double currentTime = System.currentTimeMillis();
            double deltaTime = currentTime - lastTime;

            Iterator<GameComponent> updateComponents = this.gameComponents.iterator();
            while (updateComponents.hasNext()) {

                GameComponent component = updateComponents.next();

                if (component.isActive()) {
                    component.update((deltaTime < 1 ? 1 : deltaTime), CURRENT_TICK);
                }

            }

            CURRENT_TICK++;
            if(CURRENT_TICK >= MAX_TICKS) {
                System.out.println("TIME PASSED: " + (System.currentTimeMillis() - a));
                a = System.currentTimeMillis();
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

            lastTime = currentTime;

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
}
