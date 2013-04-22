package de.futjikato.mrwhiz.rendering;

import de.futjikato.mrwhiz.game.BlockDefinitions;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageStorage extends AbstractStorage {

    protected Structure[][] mapStorage;

    public ImageStorage(File image, BlockDefinitions definition) throws IOException {

        System.out.println("Start parsing map file");

        BufferedImage imgBuffer = ImageIO.read(image);

        mapStorage = new Structure[imgBuffer.getWidth()][imgBuffer.getHeight()];

        for(int x = 0 ; x < imgBuffer.getWidth() ; x++ ) {
            for(int y = 0 ; y < imgBuffer.getHeight() ; y++ ) {
                int rgb = imgBuffer.getRGB(x, y);
                int id = (rgb) & 0xFFFFFF;

                mapStorage[x][y] = definition.getStructure(id, x, y);
            }
        }

        System.out.println("Done parsing map file");
    }

    @Override
    protected Structure loadStructure(int x, int y) {
        if(mapStorage.length < x)
            throw new IndexOutOfBoundsException("Out of X bound.");

        Structure[] col = mapStorage[x];

        if(col.length < y)
            throw new IndexOutOfBoundsException("Out of Y bound.");

        if(col[y] == null)
            throw new IllegalArgumentException("No Structure at given coords");

        return col[y];
    }
}
