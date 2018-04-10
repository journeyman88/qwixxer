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
package net.unknowndomain.qwixxer.sequencers;

import net.unknowndomain.qwixxer.common.Colore;
import net.unknowndomain.qwixxer.common.Casella;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author journeyman
 */
public abstract class Sequencer {
    
    public enum Available {
        FULL_RANDOM("interface.sequencers.fullRandom"),
        RANDOM_COLOR("interface.sequencers.randomColor"),
        RANDOM_ORDER("interface.sequencers.randomOrder");
        
        Available(String bundleKey)
        {
            this.bundleKey = bundleKey;
        }
        
        private final String bundleKey;
        
        public String getBundleKey()
        {
            return bundleKey;
        }
    }
    
    public static final Sequencer getInstance(Available type)
    {
        Sequencer retVal = null;
        switch (type)
        {
            case RANDOM_COLOR:
                retVal = new ColorRandomSequencer();
                break;
            case RANDOM_ORDER:
                retVal = new OrderRandomSequencer();
                break;
            case FULL_RANDOM:
                retVal = new FullRandomSequencer();
                break;
        }
        return retVal;
    }
    
    protected static final ThreadLocal<Random> RANDOMIZER = new ThreadLocal<Random> () {
        @Override
        public Random initialValue() {
            return new Random();
        }
    };
    
    public abstract List<List<Casella>> creaSequenze(boolean terminatoreRandom);
    
    protected List<List<Casella>> creaParziali()
    {
        List<List<Casella>> retVal = new ArrayList<>(4);
        for (Colore c : Colore.values())
        {
            retVal.add(new ArrayList<>(10));
        }
        int [] numeri = new int [] {3,4,5,6,7,8,9,10,11};
        for (int numero : numeri)
        {
            int idx = 0;
            List<Colore> coloriDisp = new ArrayList<>();
            coloriDisp.addAll(Arrays.asList(Colore.values()));
            while(!coloriDisp.isEmpty())
            {
                Double dc = RANDOMIZER.get().nextDouble() * coloriDisp.size();
                int idc = dc.intValue();
                retVal.get(idx).add(creaCasella(numero, coloriDisp.get(idc)));
                coloriDisp.remove(idc);
                idx++;
            }
        }
        return retVal;
    }
    
    protected Casella creaCasella(int numero, Colore colore)
    {
        return creaCasella(numero, colore, false);
    }
    
    protected Casella creaCasella(int numero, Colore colore, boolean terminatore)
    {
        return new Casella(numero, colore, RANDOMIZER.get(), terminatore);
    }
    
    protected static int getLineValue(List<Casella> line)
    {
        int value = 0;
        for (Casella c : line)
        {
            value += c.getValore();
        }
        return value;
    }
}
