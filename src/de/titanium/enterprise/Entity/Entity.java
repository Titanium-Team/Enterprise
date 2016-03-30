package de.titanium.enterprise.Entity;

import de.titanium.enterprise.GameComponent;

import java.util.UUID;

/**
 * Created by Yonas on 08.03.2016.
 */
public abstract class Entity implements GameComponent {

    private final UUID identifier;

    public Entity(UUID identifier) {
        this.identifier = identifier;
    }

    /**
     * Gibt die eindeutige ID des Entitys zurück.
     * @return
     */
    public UUID getIdentifier() {
        return identifier;
    }

    @Override
    public String toString() {
        return String.format("{identifier: %s}", this.identifier);
    }

}
