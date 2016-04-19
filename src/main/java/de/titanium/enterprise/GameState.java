package de.titanium.enterprise;

public enum GameState {

    /**
     * Wenn die aktuellen Rendering-Calls ausgefuehrt werden, im Game-Loop.
     */
    RENDER,

    /**
     * Wenn die Update-Calls ausgefuehrt werden, im Game-Loop.
     */
    UPDATE,

    /**
     * Wenn das Spiel startet.
     */
    POSTING,

    /**
     * Wenn sich das Spiel im Loading-Screen befindet.
     */
    LOADING,

    /**
     * Wenn zwischen den Ticks im Game-Loop gewartet wird.
     */
    WAITING

}
