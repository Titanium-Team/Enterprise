package de.titanium.enterprise.Entity.Statistic;

public interface Statistic {

    /**
     * Gibt den Namen der Statistk zur�ck.
     * @return
     */
    String getName();

    /**
     * Wenn diese Methode true zur�ck gibt, dann wird bei einem neuen Wert gepr�ft, ob dieser gr��er ist als der aktuelle
     * und falls das der fall ist, dann wird der aktuelle �berschrieben, ansonsten passiert nichts.
     * @return
     */
    boolean isMax();

    /**
     * Wenn diese Methode true zur�ck gibt, dann wird der �bergebene Wert auf den aktuellen Wert drauf addiert.
     * @return
     */
    boolean isAdd();

}
