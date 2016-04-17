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
                Enterprise.getGame().getViewManager().switchView(GameMenuView.class);
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

        this.story.put("The Departure", new ArrayList<String>() {{

            this.add("It was a cold, stormy day when Sylva, Bran and Varlin set foot outside the large,");
            this.add("half-timbered house - but they had a deadline, so Varlin had declared the");
            this.add("preparations for the trip to be over. \"Preparations\", he said, \"can take forever.");
            this.add("If you want to get something done, you can always find a reason not to do it.\"");
            this.add("\"It makes sense you would say so\", Sylva retorted, \"little so because of your");
            this.add("discipline, I daresay. If I had but a hammer, I wouldn't have to worry much, either.");
            this.add("I personally prefer to not die on a trip and be a bit late.\" Nonetheless she realised");
            this.add("the truthfulness of his words and started packing her bags. Sylva travelled lightly;");
            this.add("she did so to instantly be able to fight, any time.  Besides, her physical strength");
            this.add("was not that of a man. She was used to funny looks when her whole body was clad in");
            this.add("leather armor. Now, it proved to be more useful than Varlin's, though - the coat");
            this.add("of mail was heavy, and it required special treatment in the rain. Indeed, they");
            this.add("did not have much in common, Sylva noticed. Their ways of combat were very");
            this.add("different, their looks showed little similarity and usually, Varlin would");
            this.add("disagree with her. Bran liked to stay out of discussions - he preferred to");
            this.add("observe his surroundings carefully -but when he did say something, he would");
            this.add("usually get his way. The only thing they had in common was their ability to kill;");
            this.add("and their goal. All three hoped they would prove worthy in front of the Centrum -");
            this.add("the only organisation permitted to use military on the whole continent, to provide");
            this.add("for peace and order. Here they hoped to receive training and join its forces -");
            this.add("although this was every child's dream, few ever managed. Sylva's thoughts were");
            this.add("abruptly ended when a bright flash blinded her momentarily.");
            this.add("\"This storm is getting creepy!\", she shouted over the rain pouring. \"Yes\", Varlin");
            this.add("shouted back, \"we should head for some cover.\" and pointed to a near grove.");
            this.add("Of course Sylva knew woods should usually be avoided - it was dark and unclear");
            this.add("and the trees made it almost impossible to keep an overview. In spite of all");
            this.add("the stories of witches, necromancers, goblins and woses they had all heard");
            this.add("of when they were little, Varlin headed over to the woods, Sylva and Bran");
            this.add("following him closely. Upon entering the motte, everything became");
            this.add("intimidatingly quiet. The leaves blocked much of the light, and it smelled of");
            this.add("moisture, of mushrooms and also a bit rotten, Sylva noticed. The sound of the");
            this.add("rain became faint, and the trees seemed to spread the silence. From a near trunk,");
            this.add("a dead corpse hung on a cord. None of them paid much attention to it; self-justice");
            this.add("was a common occurance in rural areas. Slowly sneaking past it, they started");
            this.add("exploring the area. It was Bran who turned around in alarm first, when a loud");
            this.add("thump scared them. Already having her dagger at hand, Sylva turned around and");
            this.add("saw the corpse had fallen to the ground. \"Odd\", Varlin commented, his voice");
            this.add("trembling slightly. \"No one of us touched it. The rope must've been old.\"");
            this.add("Bran, who had taken a closer look at the pile of flesh and bones on the ground,");
            this.add("shook his head. \"This was no coincidence\", he mumbled and started heading back");
            this.add("towards the open light. Why did he always have to be such a coward, Sylva wondered,");
            this.add("examining the remnants of the corpse. Then she noticed the corpse was moving! Back");
            this.add("and forth a hand went, without any impulse to do so. She jumped back. \"Varlin\",");
            this.add("she cried, \"that thing is alive! Quick, come!\" Varlin wasted no time and together,");
            this.add("they hurried behind Bran. He must have set a very quick pace, Sylva thought, so");
            this.add("she quickened hers, too. But still, he was nowhere in sight. \"I don't like this at");
            this.add("all\", she mumbled. The trees changed, seemed even taller, even darker now. Were they");
            this.add("heading even deeper into the forest? Did they not follow Bran's trail? No, they");
            this.add("couldn't have. Bran didn't leave trails, Sylva knew. It assured her to know Varlin in");
            this.add("her back; the sturdy warrior was very reliable. \"Hey, Sylva\", he grunted, gasping for");
            this.add("air, \"Stop. It's useless, you know we're lost.\" They came to a halt. \"Well, hello\",");
            this.add("a voice behind them said. Quickly, they turned. It was Bran! \"Bran!\", Sylva exclaimed");
            this.add("in relief, \"I'm so glad to see you here. Where have you been? You should stop these");
            this.add("pranks, they really freak me out. \"She wanted to rush towards him, but Varlin held");
            this.add("her back. \"Who are you?\", he demanded. \"And why do you pretend to be Bran?\" To Sylva,");
            this.add("he added, \"You need to act less impulsively. That was not Bran's voice. Look closely,");
            this.add("he doesn't even carry Bran's bow.\" \"Smart\", the resemblance of Bran chuckled. \"Not");
            this.add("many notice this quickly. But it won't help you either way. You all will join my army");
            this.add("eventually, just like your friend Bran already has... \"He coughed, started to laugh in");
            this.add("such a high pitch Sylva thought it was coming from somewhere else, laughing and");
            this.add("coughing and slowly coming towards Sylva, she didn't know what to do, her friend");
            this.add("coming closer and closer, she walked backwards, but suddenly everywhere seemed to be");
            this.add("bushes and trees, Varlin next to her seemed to know just as little what to do now,");
            this.add("until suddenly - Bran came to a halt. He stumbled, fell and behind him appeared Bran,");
            this.add("lowering his bow and smiling. The other Bran on the ground started to wither, his hair");
            this.add("turning a pale green, his face wrinkling and his body becoming thin and tall. Encircled");
            this.add("by the three, he slowly started to stand up. How did he do this, with an arrow through");
            this.add("his neck, Sylva wondered. A painfully long moment later, he was at his full height,");
            this.add("turning and looking at all of them, suddenly holding a scythe in his hands, and");
            this.add("Sylva's breath froze when he started talking: \"So, shall we...\"");

        }});

        this.story.put("Coming Soon TM", new ArrayList<String>() {{

            this.add("15 down vote accepted \t  I think the phrase itself is older than World of Warcraft;");
            this.add("certainly older than the Burning Crusade expansion. I remember seeing the phrase");
            this.add("on Bungie fan sites during the Marathon and Myth heyday. For example, here's a post");
            this.add("from 2001 on myth.bungie.org where Soon(tm) appears, and here is a page on the");
            this.add("Marathon's Story website that contains the phrase all the way back in 2000 (you'll");
            this.add("have to search the page for \"soon(tm)\" as it's a big page). Both of these predate");
            this.add("WoW's announcement in September of 2001.");
            this.add("");
            this.add("I would venture to guess that the phrase has been around almost as long as releas");
            this.add("dates have been left unannounced for popular titles, which is a very, very long time");
            this.add("and may even predate the web (which is to say, you may need to dig through AOL");
            this.add("discussion board or Usenet archives to find the real. origin).");
            this.add("");
            this.add("Given that it's been around so long, I'd be surprise if Blizzard were the first to");
            this.add("ever \"officially\" use the term as a way of saying \"we aren't telling you the");
            this.add("release date yet.\"");

        }});

    }

}
