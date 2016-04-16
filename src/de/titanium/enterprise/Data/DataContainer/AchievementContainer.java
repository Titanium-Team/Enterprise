package de.titanium.enterprise.Data.DataContainer;

import de.SweetCode.SweetDB.DataSet.DataSet;
import de.SweetCode.SweetDB.DataType.DataTypes;
import de.SweetCode.SweetDB.Optional;
import de.SweetCode.SweetDB.Table.Syntax.SyntaxRuleBuilder;
import de.SweetCode.SweetDB.Table.Table;
import de.titanium.enterprise.Achievment.Achievement;
import de.titanium.enterprise.Achievment.Achievements;
import de.titanium.enterprise.Enterprise;

public class AchievementContainer implements DataContainer {

    @Override
    public String getName() {
        return "Achievement - DataContainer";
    }

    @Override
    public void store() {

        Table table = Enterprise.getGame().getDatabase().table("achievements").get();
        table.all().clear();

        for(Achievement achievement : Enterprise.getGame().getAchievementManager().getUnlocked()) {

            table.insert()
                    .add("name", achievement.getName())
                    .build();

        }

    }

    @Override
    public void load() {

        Optional<Table> tableOptional = Enterprise.getGame().getDatabase().table("achievements");

        if(tableOptional.isPresent()) {

            // Die Tabelle existiert, die Daten können geladen werden
            Table table = tableOptional.get();

            for(DataSet dataSet : table.all()) {
                Enterprise.getGame().getAchievementManager().add(Achievements.byName((String) dataSet.get("name").get().as(DataTypes.STRING)), false, false);
            }

        } else {

            Enterprise.getGame().getDatabase().createTable()
                    .name("achievements")
                    .add(
                         SyntaxRuleBuilder.create()
                                .fieldName("name")
                                .dataType(DataTypes.STRING)
                                .build()
                    )
                    .build();

        }

    }

}
