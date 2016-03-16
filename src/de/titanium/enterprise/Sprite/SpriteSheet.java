package de.titanium.enterprise.Sprite;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Yonas on 12.03.2016.
 */
public class SpriteSheet {

    private final BufferedImage bufferedImage;

    public SpriteSheet(String path) throws IOException {

        this.bufferedImage = ImageIO.read(new File(path));
        System.out.println(this.bufferedImage.getHeight());

    }

    /**
     * Gibt die angeforderte Texture als BufferedImage zur�ck.
     * @param texture
     * @return
     */
    public BufferedImage get(Texture texture) {
        return this.get(texture.getX(), texture.getY(), texture.getWidth(), texture.getHeight());
    }

    /**
     * Gibt die angeforderte Texture als BufferedImage zur�ck.
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public BufferedImage get(int x, int y, int width, int height) {

        return this.bufferedImage.getSubimage(x, y, width, height);

    }

}
