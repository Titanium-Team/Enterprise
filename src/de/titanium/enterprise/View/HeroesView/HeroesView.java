package de.titanium.enterprise.View.HeroesView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Entity.LivingEntity;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.TextBuilder;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Comparator;

/**
 * Created by Yonas on 23.03.2016.
 */
public class HeroesView extends View {

    private LivingEntity[] types = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes.types").clone();

    private int currentRow = 0;
    private final int maxRows = 13;

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


        //die Linie die die Werte von den Überschriften trennt 27|32
        g.drawLine(0, 77, 960, 77);

        //Ab hier werden alle Zeilen gezeichnet
        int y = 82;
        for(int i = this.currentRow; i < this.types.length; i++) {

            LivingEntity entity = this.types[i];

            g.drawImage(textBuilder.toImage(entity.getClass().getSimpleName(), 10), 50, y, null);
            g.drawImage(textBuilder.toImage(entity.getName(), 10), 248, y, null);
            g.drawImage(textBuilder.toImage(String.format("%.0f", entity.getHealth()), 10), 532, y, null);
            g.drawImage(textBuilder.toImage(String.format("%.0f", entity.getDexterity()), 10), 598, y, null);
            g.drawImage(textBuilder.toImage(String.format("%.0f", entity.getAttackValue()), 10), 664, y, null);
            g.drawImage(textBuilder.toImage(String.format("%d", entity.getSkillPoints()), 10), 730, y, null);


            //y erhöhen
            y += 5;

            //line

            y += 27;

        }

        //border
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

    }

    @Override
    public void update(int tick) {

        if(tick % 4 == 0) {

            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ESCAPE)) { //zurück zum hauptmenü
                Enterprise.getGame().getViewManager().switchTo(GameMenuView.class);
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_1)) {
                this.sortNameValue = true;
                this.sortHealthValue = false;
                this.sortAttackValue = false;
                this.sortDexterity = false;
                this.sortSkillPoints = false;
                this.types = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes.types").clone();
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
                this.sortNameValue = false;
                this.sortHealthValue = true;
                this.sortAttackValue = false;
                this.sortDexterity = false;
                this.sortSkillPoints = false;
                this.types = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes.types").clone();
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
                this.sortNameValue = false;
                this.sortHealthValue = false;
                this.sortAttackValue = false;
                this.sortDexterity = true;
                this.sortSkillPoints = false;
                this.types = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes.types").clone();
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
                this.sortNameValue = false;
                this.sortHealthValue = false;
                this.sortAttackValue = true;
                this.sortDexterity = false;
                this.sortSkillPoints = false;
                this.types = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes.types").clone();
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
                this.sortNameValue = false;
                this.sortHealthValue = false;
                this.sortAttackValue = false;
                this.sortDexterity = false;
                this.sortSkillPoints = true;
                this.types = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes.types").clone();
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
                this.sortAttackValue = false;
                this.sortDexterity = false;
                this.types = Enterprise.getGame().getDataManager().<LivingEntity[]>getOne("game.heroes.types").clone();
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_UP)) {
                this.currentRow--;

                if(this.currentRow < 0) {
                    this.currentRow = 0;
                }
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_DOWN)) {
                this.currentRow++;

                if((this.currentRow + this.maxRows) > this.types.length) {
                    this.currentRow--;
                }
            }
        }

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
