package de.titanium.enterprise.Data.DataContainer;

import de.SweetCode.SweetDB.DataSet.DataSet;
import de.SweetCode.SweetDB.DataType.DataTypes;
import de.SweetCode.SweetDB.Optional;
import de.SweetCode.SweetDB.Table.Syntax.SyntaxRuleBuilder;
import de.SweetCode.SweetDB.Table.Table;
import de.titanium.enterprise.Data.BinarySearchTree;
import de.titanium.enterprise.Enterprise;

public class HighscoreContainer implements DataContainer {

    @Override
    public String getName() {
        return "Highscore - DataContainer";
    }

    @Override
    public void store() {

        Table table = Enterprise.getGame().getDatabase().table("highscores").get();
        table.all().clear();

        BinarySearchTree<Double> binarySearchTree = Enterprise.getGame().getDataManager().get("game.scores");

        if(!(binarySearchTree == null)) {
            this.store(table, binarySearchTree);
        }
    }


    private void store(Table table, BinarySearchTree<Double> t) {

        if(!(t.getLeftTree() == null)) {
            this.store(table, t.getLeftTree());
        }

        if(!(t.getRightTree() == null)) {
            this.store(table, t.getRightTree());
        }

        if(!(t.isEmpty())) {
            table.insert()
                    .add("value", t.getContent().doubleValue())
                    .build();
        }

    }

    @Override
    public void load() {

        Optional<Table> tableOptional = Enterprise.getGame().getDatabase().table("highscores");
        Enterprise.getGame().getDataManager().set("game.scores", new BinarySearchTree<Double>());

        if (tableOptional.isPresent()) {

            Table table = tableOptional.get();

            for(DataSet dataSet : table.all()) {
                Enterprise.getGame().getDataManager().<BinarySearchTree<Double>>get("game.scores").insert((Double) dataSet.get("value").get().as(DataTypes.DOUBLE));
            }

        } else {

            Enterprise.getGame().getDatabase().createTable()
                .name("highscores")
                .add(
                        SyntaxRuleBuilder.create()
                                .fieldName("value")
                                .dataType(DataTypes.DOUBLE)
                                .build()
                )
            .build();

        }

    }

}
