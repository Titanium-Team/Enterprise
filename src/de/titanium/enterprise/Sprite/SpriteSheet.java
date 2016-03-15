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

    private final int width;
    private final int height;

    public SpriteSheet(String path, int width, int height) throws IOException {

        this.bufferedImage = ImageIO.read(new File(path));
        System.out.println("Hey: " + new File(path).exists());
        this.width = width;
        this.height = height;

    }

    /**
     * Gibt die angeforderte Texture als BufferedImage zurück.
     * @param texture
     * @return
     */
    public BufferedImage get(Texture texture) {
        return this.get(texture.getX(), texture.getY(), texture.getWidth(), texture.getHeight());
    }

    /**
     * Gibt die angeforderte Texture als BufferedImage zurück.
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
