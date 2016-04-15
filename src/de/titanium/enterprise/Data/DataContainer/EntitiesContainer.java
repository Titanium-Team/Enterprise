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

/**
 * Created by Yonas on 31.03.2016.
 */
public class EntitiesContainer implements DataContainer {

    public EntitiesContainer() {}

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

                switch (type) {

                    case "Archer":
                        entity = new Archer(identifier, name, health, maxHealth, dexterity,  attackValue, skillPoints);
                        break;

                    case "Rogue":
                        entity = new Rogue(identifier, name, health, maxHealth, dexterity,  attackValue, skillPoints);
                        break;

                    case "Warrior":
                        entity = new Warrior(identifier, name, health, maxHealth, dexterity,  attackValue, skillPoints);
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
                    .build();

            //set default hero types
            Enterprise.getGame().getDataManager().set("game.heroes.types", new LivingEntity[]{

                    new Archer(UUID.randomUUID(), "Robin Trump", 40, 40, 6, 8, 0),
                    new Archer(UUID.randomUUID(), "Georg von Wald", 60, 60, 3, 3, 0),
                    new Archer(UUID.randomUUID(), "Eddy Penny", 52, 52, 5, 6, 0),
                    new Archer(UUID.randomUUID(), "Tromo Domo", 20, 20, 4, 10, 0),
                    new Archer(UUID.randomUUID(), "Ranger Ben", 33, 33, 15, 20, 0),

                    new Rogue(UUID.randomUUID(), "Sneaky Pete", 20, 20, 7, 14, 0),
                    new Rogue(UUID.randomUUID(), "Chacky Chan", 12, 12, 5, 14, 0),
                    new Rogue(UUID.randomUUID(), "The Knife", 10, 10, 8, 20, 0),
                    new Rogue(UUID.randomUUID(), "Robert Rice", 30, 30, 5, 8, 0),
                    new Rogue(UUID.randomUUID(), "Sam Dodge", 15, 15, 10, 22, 0),

                    new Warrior(UUID.randomUUID(), "Big Meyer", 120, 120, 0, 2, 0),
                    new Warrior(UUID.randomUUID(), "Sir Isaac", 80, 80, 0, 3, 0),
                    new Warrior(UUID.randomUUID(), "Robby Flobby", 100, 100, 0, 2, 10),
                    new Warrior(UUID.randomUUID(), "Lord Washington", 60, 60, 0, 4, 0),
                    new Warrior(UUID.randomUUID(), "Ben Jerry", 70, 70, 0, 3, 5),

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
