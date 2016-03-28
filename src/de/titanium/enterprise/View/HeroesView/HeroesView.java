package de.titanium.enterprise.View.HeroesView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.Entities.Archer;
import de.titanium.enterprise.Entity.Entities.Rogue;
import de.titanium.enterprise.Entity.Entities.Warrior;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.TextBuilder;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.SkillView.SkillView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Comparator;

/**
 * Created by Yonas on 23.03.2016.
 */
public class HeroesView extends View {

    private LivingEntity[] types = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes.types");

    private int currentRow = 0;
    private final int maxRows = 13;

    private int selectedHero = 0;

    private boolean sortNameValue = false;
    private boolean sortHealthValue = false;
    private boolean sortAttackValue = false;
    private boolean sortDexterity = false;
    private boolean sortSkillPoints = false;

    public HeroesView(MenuView viewMenu) {
        super(viewMenu);
    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //backgroud
        g.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);

        //Draw Table
        TextBuilder textBuilder = Enterprise.getGame().getTextBuilder();

        //In diesem Abschnitt werden alle Bezeichner für die Spalten gezeichnet.
        g.setStroke(new BasicStroke(5));

        g.drawImage(textBuilder.toImage("Type", 12), 50, 40, null);
        g.drawLine(243, 0, 243, 600);

        g.drawImage(textBuilder.toImage("Name", (this.sortNameValue  ? 14 : 12)), 248, 40, null);
        g.drawLine(527, 0, 527, 600);

        g.drawImage(textBuilder.toImage("HP", (this.sortHealthValue  ? 14 : 12)), 532, 40, null);
        g.drawLine(593, 0, 593, 600);

        g.drawImage(textBuilder.toImage("DY", (this.sortDexterity  ? 14 : 12)), 598, 40, null);
        g.drawLine(659, 0, 659, 600);

        g.drawImage(textBuilder.toImage("AT", (this.sortAttackValue  ? 14 : 12)), 664, 40, null);
        g.drawLine(725, 0, 725, 600);

        g.drawImage(textBuilder.toImage("SP", (this.sortSkillPoints  ? 14 : 12)), 730, 40, null);
        g.drawLine(915, 0, 915, 600);


        //die Linie die die Werte von den Überschriften trennt 27|32
        g.drawLine(0, 77, 960, 77);

        //Ab hier werden alle Zeilen gezeichnet
        g.setStroke(new BasicStroke(3));

        int y = 82;
        LivingEntity[] currentHeroes = Enterprise.getGame().getDataManager().getOne("game.heroes");
        for(int i = this.currentRow; i < this.types.length; i++) {

            // Hier werden alle Werte Zeile für Zeile dargestellt.
            LivingEntity entity = this.types[i];

            g.drawImage(textBuilder.toImage(entity.getClass().getSimpleName(), (this.selectedHero == i ? 10 : 9)), 50, y, null);
            g.drawImage(textBuilder.toImage(entity.getName(), (this.selectedHero == i ? 10 : 9)), 248, y, null);
            g.drawImage(textBuilder.toImage(String.format("%.0f", entity.getHealth()), (this.selectedHero == i ? 10 : 9)), 532, y, null);
            g.drawImage(textBuilder.toImage(String.format("%.0f", entity.getDexterity()), (this.selectedHero == i ? 10 : 9)), 598, y, null);
            g.drawImage(textBuilder.toImage(String.format("%.0f", entity.getAttackValue()), (this.selectedHero == i ? 10 : 9)), 664, y, null);
            g.drawImage(textBuilder.toImage(String.format("%d", entity.getSkillPoints()), (this.selectedHero == i ? 10 : 9)), 730, y, null);

            // Hier wird geprüft, ob das aktuelle Entity was geprüft wird eines ist das auch im "Fight" verwendet wird,
            // falls ja dann wird ganz am Ende der Zeile ein "grünes X" gezeichnet, um das zu kennzeichnen.
            if(entity == currentHeroes[0] || entity == currentHeroes[1] || entity == currentHeroes[2]) {
                g.drawImage(Textures.CHECKED_BUTTON.getImage().getScaledInstance(20, 20, 0), 920, y, null);
            }

            //y erhöhen
            y += 27;

            g.drawLine(0, y, 980, y);

            y += 5;

        }

        //border
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

    }

    @Override
    public void update(int tick) {

        if(tick % 4 == 0) {

            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ESCAPE)) { //zurück zum hauptmenü

                // Wenn "ESC" gedrückt wird, dann wird man wieder ins Hauptmenü gebracht.
                Enterprise.getGame().getViewManager().switchTo(HeroesView.class);

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_1)) {

                // Wenn "4" gedrückt wird, dann wird die Tabelle nach dem Namen
                // aufsteigend sortiert.

                this.resetSortValues();
                this.sortNameValue = true;

                this.types = Enterprise.getGame().getDataManager().getOne("game.heroes.types");
                this.sort(this.types, 0, this.types.length - 1, new Comparator<LivingEntity>() {

                    @Override
                    public int compare(LivingEntity o1, LivingEntity o2) {

                        if(o1.getName().toCharArray()[0] > o2.getName().toCharArray()[0]) {
                            return 1;
                        }

                        if(o1.getName().toCharArray()[0] < o2.getName().toCharArray()[0]) {
                            return -1;
                        }

                        return 0;

                    }

                });

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_2)) {

                // Wenn "4" gedrückt wird, dann wird die Tabelle nach dem Lebens-Value
                // absteigend sortiert.

                this.resetSortValues();
                this.sortHealthValue = true;

                this.types = Enterprise.getGame().getDataManager().getOne("game.heroes.types");
                this.sort(this.types, 0, this.types.length - 1, new Comparator<LivingEntity>() {

                    @Override
                    public int compare(LivingEntity o1, LivingEntity o2) {

                        if(o1.getMaxHealth() > o2.getMaxHealth()) {
                            return -1;
                        }

                        if(o1.getMaxHealth() < o2.getMaxHealth()) {
                            return 1;
                        }

                        return 0;
                    }

                });

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_3)) {

                // Wenn "4" gedrückt wird, dann wird die Tabelle nach dem Dexterity-Value
                // absteigend sortiert.

                this.resetSortValues();
                this.sortDexterity = true;

                this.types = Enterprise.getGame().getDataManager().getOne("game.heroes.types");
                this.sort(this.types, 0, this.types.length - 1, new Comparator<LivingEntity>() {

                    @Override
                    public int compare(LivingEntity o1, LivingEntity o2) {

                        if (o1.getDexterity() > o2.getDexterity()) {
                            return -1;
                        }

                        if (o1.getDexterity() < o2.getDexterity()) {
                            return 1;
                        }

                        return 0;
                    }

                });

            }else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_4)) {

                // Wenn "4" gedrückt wird, dann wird die Tabelle nach dem Attack-Value
                // absteigend sortiert.

                this.resetSortValues();
                this.sortAttackValue = true;

                this.types = Enterprise.getGame().getDataManager().getOne("game.heroes.types");
                this.sort(this.types, 0, this.types.length - 1, new Comparator<LivingEntity>() {

                    @Override
                    public int compare(LivingEntity o1, LivingEntity o2) {

                        if(o1.getAttackValue() > o2.getAttackValue()) {
                            return -1;
                        }

                        if(o1.getAttackValue() < o2.getAttackValue()) {
                            return 1;
                        }

                        return 0;
                    }

                });

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_5)) {

                // Wenn "5" gedrückt wird, dann wird die Tabelle nach der Anzahl der Skill-Points
                // absteigend sortiert.

                this.resetSortValues();
                this.sortSkillPoints = true;

                this.types = Enterprise.getGame().getDataManager().getOne("game.heroes.types");
                this.sort(this.types, 0, this.types.length - 1, new Comparator<LivingEntity>() {

                    @Override
                    public int compare(LivingEntity o1, LivingEntity o2) {

                        if(o1.getSkillPoints() > o2.getSkillPoints()) {
                            return -1;
                        }

                        if(o1.getSkillPoints() < o2.getSkillPoints()) {
                            return 1;
                        }

                        return 0;
                    }

                });

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_0)) {

                // Wenn "0" gedrückt wird, dann wird die normale Tabelle ohne eine Art von
                // Sortierung dargestellt.

                this.resetSortValues();
                this.types = Enterprise.getGame().getDataManager().getOne("game.heroes.types");

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_UP)) {

                // Wenn man die Pfeiltaste nach oben drückt, dann soll in der Tabelle
                // nach oben gescrolled werden.

                this.currentRow--;
                this.selectedHero--;

                if(this.currentRow < 0) {
                    this.currentRow = 0;
                }

                if(this.selectedHero < 0) {
                    this.selectedHero = 0;
                }

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_DOWN)) {

                // Wenn man die Pfeiltaste nach unten drückt, dann soll in der Tabelle
                // nach unten gescrolled werden.

                this.currentRow++;
                this.selectedHero++;

                if((this.currentRow + this.maxRows) > this.types.length) {
                    this.currentRow--;
                }
                if(this.selectedHero >= this.types.length) {
                    this.selectedHero--;
                }

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_E)) {

                // Wenn die E-Taste gedrückt wird, dann soll der Hero ausgewählt werden.
                // Es ist aktuell so das man von jedem Typen einen nehmen muss.

                LivingEntity hero = this.types[this.selectedHero];

                if(hero instanceof Archer) {
                    Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes")[0] = hero;
                } else if(hero instanceof Warrior) {
                    Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes")[1] = hero;
                } else if(hero instanceof Rogue) {
                    Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes")[2] = hero;
                }

                Enterprise.getGame().getLogger().info("Hero Selected: " + hero.getName());
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_S)) {

                // Wenn die S-Taste gedrückt wird, dann soll der Hero in die Skill-View gebracht werden, wo man
                // dann seine Skill-Werte setzen kann.

                Enterprise.getGame().getDataManager().add("game.heroes.skilling", this.types[this.selectedHero]);
                Enterprise.getGame().getViewManager().switchTo(SkillView.class);

            }

        }

    }

    /**
     * Diese Methode setzt alle Werte, die bestimmen ob eine bestimme Spalte "gehilighted" wird
     * auf false.
     */
    public void resetSortValues() {
        this.sortNameValue = false;
        this.sortHealthValue = false;
        this.sortAttackValue = false;
        this.sortDexterity = false;
        this.sortSkillPoints = false;
    }

    /**
     * Diese Methode sortiert die angegebenen LivingEntities abhängig vom übergebenen Comparator.
     * @param livingEntities
     * @param start
     * @param end
     * @param comparator
     */
    private void sort(LivingEntity[] livingEntities, int start, int end, Comparator<LivingEntity> comparator) {

        if(start < end) {

            int pivot = start + (end - start) / 2;
            int i = start;
            int j = end;

            while(i <= j) {

                while(comparator.compare(livingEntities[i], livingEntities[pivot]) == -1) {
                    i++;
                }

                while(comparator.compare(livingEntities[j], livingEntities[pivot]) == 1) {
                    j--;
                }

                if(i <= j) {
                    LivingEntity tmp = livingEntities[i];
                    livingEntities[i] = livingEntities[j];
                    livingEntities[j] = tmp;

                    i++;
                    j--;
                }

            }

            if(start < j) {
                sort(livingEntities, start, j, comparator);
            }

            if(end > i) {
                sort(livingEntities, i, end, comparator);
            }

        }

    }

    @Override
    public void render() {
        this.repaint();
    }

}
