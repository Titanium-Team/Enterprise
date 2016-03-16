package de.titanium.enterprise.Entity;

import de.titanium.enterprise.Data.DataEntry;
import de.titanium.enterprise.Entity.EntityType.EntityType;
import de.titanium.enterprise.GameComponent;

import java.util.UUID;

/**
 * Created by Yonas on 08.03.2016.
 */
public abstract class Entity implements GameComponent, DataEntry {

    private final UUID identifier;
    private final EntityType entityType;

    public Entity(UUID identifier, EntityType entityType) {
        this.identifier = identifier;
        this.entityType = entityType;
    }

    /**
     * Gibt die eindeutige ID des Entitys zurück.
     * @return
     */
    public UUID getIdentifier() {
        return identifier;
    }

    /**
     * Gibt den Typ des Entitys zurück.
     * @return
     */
    public EntityType getEntityType() {
        return this.entityType;
    }

}
