/*
 * Copyright 2018 Marco Bignami.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
