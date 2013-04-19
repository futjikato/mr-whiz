package de.futjikato.mrwhiz.rendering;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageStorage extends AbstractStorage {

    protected BufferedImage imgBuffer;

    public ImageStorage(File image) throws StorageException {

        if(!image.exists()) {
            throw new StorageException("Image file not found at " + image.getAbsolutePath());
        }

        try {
            imgBuffer = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Structure loadStructure(int x, int y) {
        if(imgBuffer == null) {
            return null;
        }

        try {
            /*int[] rgb = imgBuffer.getRaster().getPixel(x, y, new int[3]);
            System.out.println(rgb[0] + "|" + rgb[1] + "|" + rgb[2]);*/

            int rgb = imgBuffer.getRGB(x, y);
            int id = (rgb) & 0xFFFFFF;

            System.out.println(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }


        return null;
    }
}
