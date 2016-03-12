package de.titanium.enterprise;

/**
 * Created by Yonas on 08.03.2016.
 */
public interface GameComponent {

    /**
     * Wird bei jedem Tick aufgerufen.
     */
    void update(double deltaTime, int tick);

    void render();

    boolean isActive();

}
