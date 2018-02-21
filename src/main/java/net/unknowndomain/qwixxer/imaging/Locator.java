/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unknowndomain.qwixxer.imaging;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author journeyman
 */
public class Locator {
    
    public static final String BASE_PATH = "/net/unknowndomain/qwixxer/";
    public static final String TOP_BAR = BASE_PATH + "TOP.png";
    public static final String SCORE_MASK = BASE_PATH + "scoresheet.png";
    public static final String NUMBERS_PATH = BASE_PATH + "numbers/";
    public static final String COLORS_PATH = BASE_PATH + "colors/";
    
    public static InputStream loadStream(String resourcePath)
    {
        return Locator.class.getResourceAsStream(resourcePath);
    }
    
    public static BufferedImage loadImage(String resourcePath) throws IOException
    {
        return ImageIO.read(loadStream(resourcePath));
    }
    
    public static BufferedImage loadImageNumber(int number) throws IOException
    {
        return loadImage(NUMBERS_PATH + number + ".png");
    }

}
