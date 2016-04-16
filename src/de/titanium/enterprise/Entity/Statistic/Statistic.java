package de.titanium.enterprise.Entity.Statistic;

public interface Statistic {

    /**
     * Gibt den Namen der Statistk zurück.
     * @return
     */
    String getName();

    /**
     * Wenn diese Methode true zurück gibt, dann wird bei einem neuen Wert geprüft, ob dieser größer ist als der aktuelle
     * und falls das der fall ist, dann wird der aktuelle überschrieben, ansonsten passiert nichts.
     * @return
     */
    boolean isMax();

    /**
     * Wenn diese Methode true zurück gibt, dann wird der übergebene Wert auf den aktuellen Wert drauf addiert.
     * @return
     */
    boolean isAdd();

}
