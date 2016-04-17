package de.titanium.enterprise.Data.DataContainer;

import de.SweetCode.SweetDB.DataSet.DataSet;
import de.SweetCode.SweetDB.DataType.DataTypes;
import de.SweetCode.SweetDB.Optional;
import de.SweetCode.SweetDB.Table.Syntax.SyntaxRuleBuilder;
import de.SweetCode.SweetDB.Table.Table;
import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.Entities.Archer;
import de.titanium.enterprise.Entity.Entities.Rogue;
import de.titanium.enterprise.Entity.Entities.Warrior;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Skill.Skill;
import de.titanium.enterprise.Skill.Skills;

import java.util.UUID;

public class EntityContainer implements DataContainer {

    public EntityContainer() {}

    @Override
    public String getName() {
        return "Entities - DataContainer";
    }

    @Override
    public void store() {

        Table table =  Enterprise.getGame().getDatabase().table("entityTypes").get();
        table.all().clear();
        LivingEntity[] entities = Enterprise.getGame().getDataManager().get("game.heroes.types");
        for(int i = 0; i < entities.length; i++) {

            LivingEntity entity = entities[i];

            // Die Skills in einen String "packen"
            StringBuilder unlockedSkills = new StringBuilder();
            for(Skill skill : entity.getSkills()) {
                unlockedSkills.append(skill.getName() + ";");
            }

            table.insert()
                    .add("type", entity.getClass().getSimpleName())
                    .add("identifier", entity.getIdentifier())
                    .add("name", entity.getName())
                    .add("health", entity.getHealth())
                    .add("maxHealth", entity.getMaxHealth())
                    .add("dexterity", entity.getDexterity())
                    .add("attackValue", entity.getAttackValue())
                    .add("skillPoints", entity.getSkillPoints())
                    .add("unlockedSkills", unlockedSkills)
                    .add("selectedValue", (!(this.selectedIndex(entity) == -1)))
                    .add("selectedIndex", this.selectedIndex(entity))
                    .add("index", i)
                    .add("isUnlocked", entity.isUnlocked())
                    .add("scoreToUnlock", entity.getScoreToUnlock())
                    .build();

        }

    }

    /**
     * Gibt den Index im Array der aktuell  ausgewaehlen Helden zurueck.
     * @return
     */
    private int selectedIndex(LivingEntity entity) {

        LivingEntity[] entities = Enterprise.getGame().getDataManager().get("game.heroes");
        for(int i = 0; i < entities.length; i++) {

            if(entities[i] == entity) {
                return i;
            }

        }

        return -1;

    }

    @Override
    public void load() {

        Optional<Table> tableOptional = Enterprise.getGame().getDatabase().table("entityTypes");
        
        // generate the first enemy
        Enterprise.getGame().getDataManager().set("game.run.level", 1);
        Enterprise.getGame().getDataManager().set("game.enemy", Enterprise.getGame().getEntityGenerator().generate(1));

        // Falls die Tabelle existiert, dann können die Entities geladen werden.
        if(tableOptional.isPresent()) {

            Table table = tableOptional.get();

            Enterprise.getGame().getDataManager().set("game.heroes.types", new LivingEntity[table.all().size()]);
            Enterprise.getGame().getDataManager().set("game.heroes", new LivingEntity[3]);

            for(DataSet dataSet : table.all()) {

                LivingEntity entity;

                // Alle Werte des Entitys
                String type = dataSet.get("type").get().as(DataTypes.STRING);
                UUID identifier = dataSet.get("identifier").get().as(DataTypes.UUID);
                String name = dataSet.get("name").get().as(DataTypes.STRING);
                double health = dataSet.get("health").get().as(DataTypes.DOUBLE);
                double maxHealth = dataSet.get("maxHealth").get().as(DataTypes.DOUBLE);
                double dexterity = dataSet.get("dexterity").get().as(DataTypes.DOUBLE);
                double attackValue = dataSet.get("attackValue").get().as(DataTypes.DOUBLE);
                int skillPoints = dataSet.get("skillPoints").get().as(DataTypes.INTEGER);
                String unlockedSkills = dataSet.get("unlockedSkills").get().as(DataTypes.STRING);
                boolean isUnlocked = dataSet.get("isUnlocked").get().as(DataTypes.BOOLEAN);
                double scoreToUnlock = dataSet.get("scoreToUnlock").get().as(DataTypes.DOUBLE);

                switch (type) {

                    case "Archer":
                        entity = new Archer(identifier, name, health, maxHealth, dexterity,  attackValue, skillPoints, isUnlocked, scoreToUnlock);
                        break;

                    case "Rogue":
                        entity = new Rogue(identifier, name, health, maxHealth, dexterity,  attackValue, skillPoints, isUnlocked, scoreToUnlock);
                        break;

                    case "Warrior":
                        entity = new Warrior(identifier, name, health, maxHealth, dexterity,  attackValue, skillPoints, isUnlocked, scoreToUnlock);
                        break;

                    default: throw new IllegalArgumentException(String.format("[DataContainer] EntityType %s is unknown.", type));

                }

                // Alle Skills wieder hinzufügen, die bereits freigeschaltet wurden.
                String[] explodedSkills = unlockedSkills.split(";");
                for(String skillName : explodedSkills) {

                    Skill skill = Skills.byName(skillName);

                    if(!(skill == null)) {
                        entity.addSkill(skill);
                    }

                }

                // Das Entity hinzufügen
                int index = dataSet.get("index").get().as(DataTypes.INTEGER);
                Enterprise.getGame().getDataManager().<LivingEntity[]>get("game.heroes.types")[index] = entity;

                // Wenn es auch ein ausgewählter Held ist, dann wird dieser auch dem Hero Array hinzugefügt.
                if(dataSet.get("selectedValue").get().as(DataTypes.BOOLEAN)) {
                    int i = dataSet.get("selectedIndex").get().as(DataTypes.INTEGER);
                    Enterprise.getGame().getDataManager().<LivingEntity[]>get("game.heroes")[i] = entity;
                }

            }

        } else {

            // Falls die Tabell noch nicht existiert, dann wird sie erstellt.
            Enterprise.getGame().getDatabase().createTable()
                    .name("entityTypes")
                    .add(
                            SyntaxRuleBuilder.create()
                                    .fieldName("type")
                                    .dataType(DataTypes.STRING)
                                    .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("identifier")
                                .dataType(DataTypes.UUID)
                                .isUnique(true)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("name")
                                .dataType(DataTypes.STRING)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("health")
                                .dataType(DataTypes.DOUBLE)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("maxHealth")
                                .dataType(DataTypes.DOUBLE)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("dexterity")
                                .dataType(DataTypes.DOUBLE)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("attackValue")
                                .dataType(DataTypes.DOUBLE)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("skillPoints")
                                .dataType(DataTypes.INTEGER)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("unlockedSkills")
                                .dataType(DataTypes.STRING)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("selectedValue")
                                .dataType(DataTypes.BOOLEAN)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("selectedIndex")
                                .dataType(DataTypes.INTEGER)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("index")
                                .dataType(DataTypes.INTEGER)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("isUnlocked")
                                .dataType(DataTypes.BOOLEAN)
                                .build()
                    )
                    .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("scoreToUnlock")
                                .dataType(DataTypes.DOUBLE)
                                .build()
                    )
                    .build();

            //set default hero types
            Enterprise.getGame().getDataManager().set("game.heroes.types", new LivingEntity[]{

                    new Archer(UUID.randomUUID(), "Robin Trump", 55, 55, 5, 5, 0, true, -1),
                    new Archer(UUID.randomUUID(), "Georg van Waald", 40, 40, 6, 9, 0, true, -1),
                    new Archer(UUID.randomUUID(), "Eddy Penny", 60, 60, 5, 4, 0, false, 500),
                    new Archer(UUID.randomUUID(), "Trevor Denver", 50, 50, 8, 10, 0, false, 1000),
                    new Archer(UUID.randomUUID(), "Ranger Ben", 30, 30, 4, 8, 0, false, 1500),

                    new Warrior(UUID.randomUUID(), "Big Meyer", 160, 160, 0, 3, 0, true, -1),
                    new Warrior(UUID.randomUUID(), "Sir Isaac", 130, 130, 0, 4, 0, true, -1),
                    new Warrior(UUID.randomUUID(), "Robby Rock", 100, 100, 0, 5, 0, false, 500),
                    new Warrior(UUID.randomUUID(), "Lord Washington", 200, 200, 0, 2, 0, false, 1000),
                    new Warrior(UUID.randomUUID(), "Ben Jerry", 80, 80, 0, 6, 0, false, 1500),

                    new Rogue(UUID.randomUUID(), "Sneaky Pete", 25, 25, 6, 14, 0, true, -1),
                    new Rogue(UUID.randomUUID(), "Chacky Chan", 13, 13, 5, 15, 0, true, -1),
                    new Rogue(UUID.randomUUID(), "The Knife", 20, 20, 10, 22, 0, false, 500),
                    new Rogue(UUID.randomUUID(), "Robert Rice", 14, 14, 12, 30, 0, false, 1000),
                    new Rogue(UUID.randomUUID(), "Sam Dodge", 6, 6, 8, 25, 0, false, 1500),

            });

            //set default heroes
            Enterprise.getGame().getDataManager().set("game.heroes", new LivingEntity[]{

                    Enterprise.getGame().getDataManager().<LivingEntity[]>get("game.heroes.types")[0],
                    Enterprise.getGame().getDataManager().<LivingEntity[]>get("game.heroes.types")[5],
                    Enterprise.getGame().getDataManager().<LivingEntity[]>get("game.heroes.types")[10]

            });

        }

    }

}
