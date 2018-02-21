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
package net.unknowndomain.qwixxer.common;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import net.unknowndomain.qwixxer.imaging.Locator;

/**
 *
 * @author journeyman
 */
public class Casella {
    
    public static final long SCRAMBLE_LIMIT = 100l;
    
    private final int valore;
    private final Colore colore;
    private final Double scrambler;
    
    public Casella(int valore, Colore colore, Random randomizer)
    {
        this(valore, colore, randomizer, false);
    }
    
    public Casella(int valore, Colore colore, Random randomizer, boolean terminatore)
    {
        this.valore = valore;
        this.colore = colore;
        if (terminatore)
        {
            this.scrambler = (Math.E * SCRAMBLE_LIMIT);
        }
        else
        {
            if (randomizer != null)
            {
                this.scrambler = (randomizer.nextDouble() * SCRAMBLE_LIMIT);
            }
            else
            {
                this.scrambler = this.valore * 0.9;
            }
        }
    }

    public Double getScrambler() {
        return scrambler;
    }
    
    @Override
    public String toString()
    {
        return printValue();
    }
    
    public String printValue()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(getValore());
        switch(getColore())
        {
            case ROSSO:
                sb.append("R");
                break;
            case VERDE:
                sb.append("V");
                break;
            case GIALLO:
                sb.append("G");
                break;
            case BLU:
                sb.append("B");
                break;
        }
        return sb.toString();
    }
    
    public BufferedImage getImage() throws IOException
    {
        BufferedImage color = Locator.loadImage(getColore().getBgImage());
        BufferedImage number = Locator.loadImageNumber(valore);
        int w = color.getWidth();
        if (scrambler > SCRAMBLE_LIMIT)
        {
            w += color.getWidth();
        }
        BufferedImage finalImage = new BufferedImage(w, color.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = finalImage.createGraphics();
        g.drawImage(color, 0, 0, null);
        g.drawImage(number, 0, 0, null);
        if (scrambler > SCRAMBLE_LIMIT)
        {
            BufferedImage lock = Locator.loadImage(getColore().getLockedImage());
            g.drawImage(lock, color.getWidth(), 0, null);
        }
        g.dispose();
        return finalImage;
    }

    public int getValore() {
        return valore;
    }

    public Colore getColore() {
        return colore;
    }
}
