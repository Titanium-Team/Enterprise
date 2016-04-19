package de.titanium.enterprise.GameUtils;

public interface GameComponent {

    /**
     * Wird bei jedem Tick aufgerufen.
     */
    void update(int tick);

    /**
     * Diese Methode wird immer dann aufgerufen, wenn das Bild erneut gezeichnet werden sollte.
     */
    void render();

    /**
     * Diese Methode prueft, ob das GameComponent geupdated und gerendert werden soll.
     *
     * Wenn diese Methode true zurueck gibt, wird der GameLoop die GameComponent#update und die
     * GameComponent#render Methode aufrufen.
     * @return
     */
    boolean isActive();

}
