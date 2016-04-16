package de.titanium.enterprise.Entity.Statistic;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameStatistic {

    private final Map<Statistic, Double> values = new LinkedHashMap<>();

    public GameStatistic() {}

    public void update(Statistic key, double value) {
        if(this.values.containsKey(key)) {
            if(key.isMax()) {
                this.values.put(key, Math.max(this.values.get(key), value));
            } else if(key.isAdd()) {
                this.values.put(key, this.values.get(key) + value);
            }
        } else {
            this.values.put(key, value);
        }
    }

    @Override
    public String toString() {
        return String.format("{values: %s}", this.values.toString());
    }

}
