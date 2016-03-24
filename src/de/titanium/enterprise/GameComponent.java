package de.titanium.enterprise;

/**
 * Created by Yonas on 08.03.2016.
 */
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
     * Diese Methode prüft, ob das GameComponent geupdated und gerendert werden soll.
     *
     * Wenn diese Methode true zurück gibt, wird der GameLoop die GameComponent#update und die
     * GameComponent#render Methode aufrufen.
     * @return
     */
    boolean isActive();

}
