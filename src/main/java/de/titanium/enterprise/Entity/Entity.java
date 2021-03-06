package de.titanium.enterprise.Entity;

import de.titanium.enterprise.GameUtils.GameComponent;

import java.util.UUID;

public abstract class Entity implements GameComponent {

    private final UUID identifier;

    public Entity(UUID identifier) {
        this.identifier = identifier;
    }

    /**
     * Gibt die eindeutige ID des Entitys zurueck.
     * @return
     */
    public UUID getIdentifier() {
        return identifier;
    }

    @Override
    public boolean equals(Object o) {

        if(o == null || !(o instanceof Entity)) {
            return false;
        }

        return this.identifier.compareTo(((Entity)o).getIdentifier()) == 0;
    }

    @Override
    public String toString() {
        return String.format("{identifier: %s}", this.identifier);
    }

}
