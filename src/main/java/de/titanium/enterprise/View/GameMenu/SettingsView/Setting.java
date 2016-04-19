package de.titanium.enterprise.View.GameMenu.SettingsView;

public abstract class Setting<T> {

    private final String name;
    private final String[] options;

    public Setting(String name, String[] options) {
        this.name = name;
        this.options = options;
    }

    /**
     * Gibt den Namen der Option zurueck.
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gibt einen Array mit allen moeglichen Optionen zurueck, die ausgewaehlt werden koennen.
     * @return
     */
    public String[] getOptions() {
        return this.options;
    }

    /**
     * Gibt eine Beschreibung fuer jede moegliche Option zurueck.
     * @return
     */
    public abstract String[][] getDescription();

    /**
     * Gibt den Index im Array von #getOptions an, der als standart Wert ausgewaehlt werden soll.
     * @return
     */
    public abstract int getDefaultSelected();

    /**
     * Wird aufgerufen, wenn der Wert geupdated werden soll.
     * @param input
     */
    public abstract void change(T input);

}
