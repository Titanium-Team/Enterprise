package de.titanium.enterprise.View.StoryView;

import de.titanium.enterprise.Enterprise;
import de.titanium.enterprise.Sprite.Textures;
import de.titanium.enterprise.View.GameMenu.GameMenuView;
import de.titanium.enterprise.View.MenuView;
import de.titanium.enterprise.View.View;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yonas on 22.03.2016.
 */
public class StoryView extends View {

    private final Map<String, List<String>> story = new LinkedHashMap<>();
    private final int maxLines = 20;
    private int currentLine = 0;
    private int currentChapter = 0;

    public StoryView(MenuView viewMenu) {
        super(viewMenu);
    }

    @Override
    public void paintComponent(Graphics graphic) {

        Graphics2D g = (Graphics2D) graphic;
        super.paintComponent(g);

        g.setRenderingHints(Enterprise.getGame().getRenderingHints());

        //draw background and frame
        g.drawImage(Textures.BACKGROUND.getImage(), 0, 0, null, null);
        g.drawImage(Textures.BORDER_UP.getImage(), 0, 0, null, null);

        //draw headline
        String[] chapters = this.story.keySet().toArray(new String[this.story.size()]);
        String chapter = chapters[this.currentChapter];
        Image chapterImage = Enterprise.getGame().getTextBuilder().toImage(chapter, 15);

        g.drawImage(chapterImage, 480 - (chapterImage.getWidth(null) / 2), 50, null);

        //render lines of the story (max.: 16 lines)
        List<String> lines = this.story.get(chapter);

        int amountOfLines = Math.min((this.currentLine + this.maxLines), lines.size());
        int x = 0;
        for(int i = this.currentLine; i < amountOfLines; i++) { //TODO
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(lines.get(i), 7), 45, 95 + x * 20, null);
            x++;
        }

        //rendering chapter overview
        int i = 0;
        for(String headline : chapters) {
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage((i + 1) + " " + headline, (i == this.currentChapter ? 9 : 8)), 1000, 95 + i * 25, null);
            i++;
        }

        // Hier werden die Achievements gezeichnet, falls welche freigeschaltet wurden.
        Enterprise.getGame().getAchievementManager().handle(g);

    }

    @Override
    public void update(int tick) {

        if(tick % 4 == 0) {

            String[] chapters = this.story.keySet().toArray(new String[this.story.size()]);

            if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_ESCAPE)) { //zurueck ins hauptmenue
                Enterprise.getGame().getViewManager().switchTo(GameMenuView.class);
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_DOWN)) { //nach unten scrollen
                this.currentLine++;

                //Naechstes Kapitel
                String chapter = chapters[this.currentChapter];
                if(((this.currentLine + this.maxLines) > this.story.get(chapter).size()) && (this.story.size()-1) > this.currentChapter) { //naechstes kapitel
                    this.currentLine = 0;
                    this.currentChapter++;
                } else if((this.currentLine + this.maxLines) > this.story.get(chapter).size()) { //kein naechstes kapitel
                    this.currentLine--;
                }
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_UP)) { //nach oben scrollen
                this.currentLine--;

                if(this.currentLine < 0 && this.currentChapter > 0) { //vorheriges Kapitel
                    this.currentChapter--;
                    this.currentLine = Math.max(this.story.get(chapters[this.currentChapter]).size() - this.maxLines, 0);
                } else if(this.currentLine < 0) { //vorherige Zeile
                    this.currentLine = 0;
                }

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_RIGHT) && (this.story.size()-1) > this.currentChapter) { //naechstes kapitel
                this.currentChapter++;
                this.currentLine = 0;
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_LEFT) && this.currentChapter > 0) { //vorheriges kapitel
                this.currentChapter--;
                this.currentLine = Math.max(this.story.get(chapters[this.currentChapter]).size() - this.maxLines, 0);
            }

        }

    }

    @Override
    public void render() {
        this.repaint();
    }

    {

        this.story.put("Einleitung", new ArrayList<String>() {{

            //this.set("BlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBla");
            this.add("In medieval times, there was a ring. It was silver and plain. The ring");
            this.add("did not look like much, but it was forged by one of the most skilled");
            this.add("blacksmiths in the world, who also happend to be a magican. Magicans were");
            this.add("uncommon, and the blacksmith whose name was Elric Darke, had no way to");
            this.add("to pass on his legacy. Wanting his abilities to live on, he forged a ring");
            this.add("that took on all the qualities of his powers.");

        }});

        this.story.put("Short Chapter", new ArrayList<String>() {{

            this.add("BlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBla");
            this.add("Bla1");
            this.add("Bla2");
            this.add("Bla3");
            this.add("Bla4");
            this.add("Bla5");
            this.add("Bla6");
            this.add("Bla7");
            this.add("Bla8");
            this.add("Bla9");
            this.add("Bla10");

        }});

        this.story.put("Spannend", new ArrayList<String>() {{

            this.add("BlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBlaBla");
            this.add("Bla1");
            this.add("Bla2");
            this.add("Bla3");
            this.add("Bla4");
            this.add("Bla5");
            this.add("Bla6");
            this.add("Bla7");
            this.add("Bla8");
            this.add("Bla9");
            this.add("Bla10");
            this.add("Bla11");
            this.add("Bla12");
            this.add("Bla13");
            this.add("Bla14");
            this.add("Bla15");
            this.add("Bla16");
            this.add("Bla17");
            this.add("Bla18");
            this.add("Bla19");
            this.add("Bla20");
            this.add("Bla21");
            this.add("Bla22");
            this.add("Bla23");
            this.add("Bla24");
            this.add("Bla25");
            this.add("Bla26");
            this.add("Bla27");
            this.add("Bla28");
            this.add("Bla29");
            this.add("Bla30");
            this.add("Bla31");
            this.add("Bla32");
            this.add("Bla33");
            this.add("Bla34");
            this.add("Bla35");

        }});

    }

}
