package de.titanium.enterprise.View.SettingsView;

/**
 * Created by Yonas on 25.03.2016.
 */
public abstract class Setting<T> {

    private final String name;
    private final String[] options;

    public Setting(String name, String[] options) {
        this.name = name;
        this.options = options;
    }

    public String getName() {
        return this.name;
    }

    public String[] getOptions() {
        return this.options;
    }

    public abstract String[][] getDescription();

    public abstract int getDefaultSelected();

    public abstract void change(T input);

}
