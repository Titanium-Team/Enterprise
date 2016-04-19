package de.titanium.enterprise.GameUtils;

public enum GameState {

    /**
     * Wenn die aktuellen Rendering-Calls ausgefuehrt werden, im GameUtils-Loop.
     */
    RENDER,

    /**
     * Wenn die Update-Calls ausgefuehrt werden, im GameUtils-Loop.
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
     * Wenn zwischen den Ticks im GameUtils-Loop gewartet wird.
     */
    WAITING

}
