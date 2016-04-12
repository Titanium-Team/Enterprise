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
            g.drawImage(Enterprise.getGame().getTextBuilder().toImage(lines.get(i), 6), 45, 95 + x * 20, null);
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
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_S)) { //nach unten scrollen
                this.currentLine++;

                //Naechstes Kapitel
                String chapter = chapters[this.currentChapter];
                if(((this.currentLine + this.maxLines) > this.story.get(chapter).size()) && (this.story.size()-1) > this.currentChapter) { //naechstes kapitel
                    this.currentLine = 0;
                    this.currentChapter++;
                } else if((this.currentLine + this.maxLines) > this.story.get(chapter).size()) { //kein naechstes kapitel
                    this.currentLine--;
                }
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_W)) { //nach oben scrollen
                this.currentLine--;

                if(this.currentLine < 0 && this.currentChapter > 0) { //vorheriges Kapitel
                    this.currentChapter--;
                    this.currentLine = Math.max(this.story.get(chapters[this.currentChapter]).size() - this.maxLines, 0);
                } else if(this.currentLine < 0) { //vorherige Zeile
                    this.currentLine = 0;
                }

            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_A) && (this.story.size()-1) > this.currentChapter) { //naechstes kapitel
                this.currentChapter++;
                this.currentLine = 0;
            } else if(Enterprise.getGame().getKeyManager().isPressed(KeyEvent.VK_D) && this.currentChapter > 0) { //vorheriges kapitel
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

        this.story.put("Short Chapter", new ArrayList<String>() {{

            this.add("It was a cold, stormy day when Sylva, Bran and Varlin set foot outside the large,");
            this.add("half-timbered house - but they had a deadline, so Varlin had declared the");
            this.add("preparations for the trip to be over. \"Preparations\", he said,");
            this.add("\"can take forever. If you want to get something done, you can always find a reason not");
            this.add("to do it.\"");
            this.add("\"It makes sense you would say so\", Sylva retorted, \"little so because of your");
            this.add("discipline, I daresay. If I had but a hammer, I wouldn't have to worry much, either. I");
            this.add("personally prefer to not die on a trip and be a bit late.\"");
            this.add("Nonetheless she realised the truthfulness of his words and started packing her bags.");
            this.add("Sylva travelled lightly; she did so to be able to instantly be able to fight, any");
            this.add("time.  Besides, her physical strength was not that of a man. She was used to funny");
            this.add("looks when her whole body was clad in leather armor. Now, it proved to be more useful");
            this.add("than Varlin's, though - the heavy coat of mail he was heavy, and it required special");
            this.add("treatment in the rain.");
            this.add("Indeed, they did not have much in common, Sylva noticed. Their ways of combat were");
            this.add("very different, their looks showed little similarity and usually, Varlin would");
            this.add("disagree with her. Bran liked to stay out of discussions - he preferred to observe his");
            this.add("surroundings carefully -but when he did say something, he would usually get his way.");
            this.add("The only thing they had in common was their ability to kill; and their goal. All three");
            this.add("hoped they would prove worthy in front of the Centrum - the only organisation");
            this.add("permitted to use military on the whole continent, to provide for peace and order. Here");
            this.add("they hoped to receive training and join its forces - although this was every child's");
            this.add("dream, few ever managed.");
            this.add("Sylva's thoughts were abruptly ended when a bright flash blinded her momentarily.");
            this.add("\"This storm is getting creepy!\", she shouted over the rain pouring. \"Yes\", Varlin");
            this.add("shouted back, \"we should head for some cover.\" and pointed to a near grove. Of course");
            this.add("Sylva knew woods should usually be avoided - it was dark and unclear and the trees");
            this.add("made it almost impossible to keep an overview. In spite of all the stories of witches,");
            this.add("necromancers, goblins and woses they had all heard of when they were little, Varlin");
            this.add("headed over to the woods, Sylva and Bran following him closely.");
            this.add("Upon entering the motte, everything became intimidatingly quiet. The leaves blocked");
            this.add("much of the light, and it smelled of moisture, of mushrooms and also a bit rotten,");
            this.add("Sylva noticed. The sound of the rain became faint, and the trees seemed to spread the");
            this.add("silence. From a near trunk, a dead corpse hung on a cord. None of them paid much");
            this.add("attention to it; self-justice was a common occurance in rural areas.");
            this.add("Slowly sneaking past it, they started exploring the area. It was Bran who turned");
            this.add("around in alarm first, when a loud thump scared them. Already having her dagger at");
            this.add("hand, Sylva turned around and saw the corpse had fallen to the ground. \"Odd\", Varlin");
            this.add("commented, his voice trembling slightly. \"No one of us touched it. The rope must've");
            this.add("been old.\"");
            this.add("Bran, who had taken a closer look at the pile of flesh and bones on the ground, shook");
            this.add("his head. \"This was no coincidence\", he mumbled and started heading back towards the");
            this.add("open light.");
            this.add("Why did he always have to be such a coward, Sylva wondered, examining the remnants of");
            this.add("the corpse. Then she noticed the corpse was moving! Back and forth a hand went,");
            this.add("without any impulse to do so. She jumped back.");
            this.add("\"Varlin\", she cried, \"that thing is alive! Quick, come!\"");
            this.add("Varlin wasted no time and together, they hurried behind Bran. He must have set a very");
            this.add("quick pace, Sylva thought, so she quickened hers, too. But still, he was nowhere in");
            this.add("sight.");
            this.add("\"I don't like this at all\", she mumbled. The trees changed, seemed even taller, even");
            this.add("darker now. Were they heading even deeper into the forest? Did they not follow Bran's");
            this.add("trail? No, they couldn't have. Bran didn't leave trails, Sylva knew.");
            this.add("It assured her to know Varlin in her back; the sturdy warrior was very reliable.");
            this.add("\"Hey, Sylva\", he grunted, gasping for air, \"Stop. It's useless, you know we're lost.\"");
            this.add("They came to a halt.");
            this.add("\"Well, hello\", a voice behind them said. Quickly, they turned. It was Bran!");
            this.add("\"Bran!\", Sylva exclaimed in relief, \"I'm so glad to see you here. Where have you been?");
            this.add("You should stop these pranks, they really freak me out.\"");
            this.add("She wanted to rush towards him, but Varlin held her back.");
            this.add("\"Who are you?\", he demanded. \"And why do you pretend to be Bran?\"");
            this.add("To Sylva, he added, \"You need to act less impulsively. That was not Bran's voice. Look");
            this.add("closely, he doesn't even carry Bran's bow.\"");
            this.add("\"Smart\", the resemblance of Bran chuckled. \"Not many notice this quickly. But it won't");
            this.add("help you either way. You all will join my army eventually, just like your friend Bran");
            this.add("already has...\"");

        }});

    }

}
