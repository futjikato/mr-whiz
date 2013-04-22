package de.futjikato.mrwhiz.rendering;

import de.futjikato.mrwhiz.game.BlockDefinitions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageStorage extends AbstractStorage {

    protected Structure[][] mapStorage;

    public ImageStorage(File image, BlockDefinitions definition) throws IOException {
        BufferedImage imgBuffer = ImageIO.read(image);

        mapStorage = new Structure[imgBuffer.getWidth()][imgBuffer.getHeight()];

        for(int x = 0 ; x < imgBuffer.getWidth() ; x++ ) {
            for(int y = 0 ; y < imgBuffer.getHeight() ; y++ ) {
                int rgb = imgBuffer.getRGB(x, y);
                int id = (rgb) & 0xFFFFFF;

                mapStorage[x][y] = definition.getStructure(id);
            }
        }
    }

    @Override
    protected Structure loadStructure(int x, int y) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
