package de.titanium.enterprise.Entity.Statistic;

public interface Statistic {

    /**
     * Gibt den Namen der Statistk zurueck.
     * @return
     */
    String getName();

    /**
     * Wenn diese Methode true zurueck gibt, dann wird bei einem neuen Wert geprueft, ob dieser groesser ist als der aktuelle
     * und falls das der fall ist, dann wird der aktuelle ueberschrieben, ansonsten passiert nichts.
     * @return
     */
    boolean isMax();

    /**
     * Wenn diese Methode true zurueck gibt, dann wird der uebergebene Wert auf den aktuellen Wert drauf addiert.
     * @return
     */
    boolean isAdd();

}
