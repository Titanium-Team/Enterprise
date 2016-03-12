package de.titanium.enterprise.Entity.Upgrade;

import de.titanium.enterprise.Entity.Entity;

/**
 * Created by Yonas on 09.03.2016.
 */
public interface Upgrade<T extends Entity> {

    boolean upgradeUnlocked(T entity);

    void performUpgrade(T entity);

}
