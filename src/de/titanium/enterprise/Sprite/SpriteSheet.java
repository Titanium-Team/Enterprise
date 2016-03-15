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
        return this.get(texture.getStartX(), texture.getStartY(), texture.getEndX(), texture.getEndY());
    }

    /**
     * Gibt die angeforderte Texture als BufferedImage zurück.
     * @param startX
     * @param startY
     * @param endX
     * @param endY
     * @return
     */
    public BufferedImage get(int startX, int startY, int endX, int endY) {

        return this.bufferedImage.getSubimage(startX, startY, endX - startX, endY - startY);

    }

}
