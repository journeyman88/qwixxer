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

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import net.unknowndomain.qwixxer.common.Casella;

/**
 *
 * @author journeyman
 */
public class Scoresheet {
    
    public static BufferedImage make(List<List<Casella>> sequences) throws IOException
    {
        BufferedImage scoresheet = new BufferedImage(2850, 1800, BufferedImage.TYPE_INT_ARGB);
        Graphics2D magicPen = scoresheet.createGraphics();
        int offsetY = 0;
        BufferedImage top = Locator.loadImage(Locator.TOP_BAR);
        magicPen.drawImage(top, 0, 0, null);
        offsetY += top.getHeight();
        for (List<Casella> seq : sequences)
        {
            BufferedImage line = new BufferedImage(2850, 286, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = line.createGraphics();
            g.drawImage(line, 0, 0, null);
            int offsetX = 0;
            for (Casella c : seq)
            {
                if (offsetX == 0)
                {
                    BufferedImage init = Locator.loadImage(c.getColore().getInitImage());
                    g.drawImage(init, 0, 0, null);
                    offsetX += init.getWidth();
                }
                BufferedImage cas = c.getImage();
                g.drawImage(cas, offsetX, 0, null);
                offsetX += cas.getWidth();
            }
            g.dispose();
            magicPen.drawImage(line, 0, offsetY, null);
            offsetY += line.getHeight();
        }
        BufferedImage score = Locator.loadImage(Locator.SCORE_MASK);
        magicPen.drawImage(score, 0, 0, null);
        magicPen.dispose();
        return scoresheet;
    }
    
}
