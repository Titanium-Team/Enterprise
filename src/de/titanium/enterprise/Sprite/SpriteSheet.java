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
        this.width = width;
        this.height = height;

    }

    public BufferedImage get(Texture texture) {
        return this.get(texture.getX(), texture.getY());
    }

    public BufferedImage get(int x, int y) {

        return this.bufferedImage.getSubimage(x * this.width, y * this.height, this.width, this.height);

    }

}
