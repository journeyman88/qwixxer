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
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author journeyman
 */
public class ColorRandomSequencer extends Sequencer {
    
    private static final Comparator<Casella> ORDINATORE = new OrdinatoreCaselle();
    
    @Override
    public List<List<Casella>> creaSequenze(boolean terminatoreRandom)
    {
        List<List<Casella>> parziali = creaParziali();
        List<List<Casella>> sequences = new ArrayList<>(4);
        if (terminatoreRandom)
        {
            List<Casella> terminatori = new ArrayList<>(4);
            List<Integer> numPool = new ArrayList<>();
            List<Colore>  colPool = new ArrayList<>();
            numPool.addAll(Arrays.asList(new Integer [] {2, 2, 12, 12}));
            colPool.addAll(Arrays.asList(Colore.values()));
            for (int i=0; i<4; i++)
            {
                Double dn = RANDOMIZER.get().nextDouble() * numPool.size();
                int idxn = dn.intValue();
                int nv = numPool.get(idxn);
                numPool.remove(idxn);
                Double dc = RANDOMIZER.get().nextDouble() * colPool.size();
                int idxc = dc.intValue();
                Colore cv = colPool.get(idxc);
                colPool.remove(idxc);
                parziali.get(i).add(creaCasella(nv, cv));
                if (nv<10)
                {
                    terminatori.add(creaCasella(12, cv, true));
                }
                else
                {
                    terminatori.add(creaCasella(2, cv, true));
                }
            }
            while(!terminatori.isEmpty())
            {
                Double dp = RANDOMIZER.get().nextDouble() * parziali.size();
                int idp = dp.intValue();
                Double dt = RANDOMIZER.get().nextDouble() * terminatori.size();
                int idt = dt.intValue();
                int termVal = terminatori.get(idt).getValore();
                int check = getLineValue(parziali.get(idp)) + termVal;
                if (check == 77)
                {
                    List<Casella> tempSeq = parziali.get(idp);
                    tempSeq.add(terminatori.get(idt));
                    parziali.remove(idp);
                    terminatori.remove(idt);
                    if (termVal > 7)
                    {
                        tempSeq.sort(ORDINATORE);
                    }
                    else
                    {
                        tempSeq.sort(ORDINATORE.reversed());
                    }
                    sequences.add(tempSeq);
                }
            }
        }
        else
        {
            parziali.get(0).add(creaCasella(2, Colore.ROSSO));
            parziali.get(1).add(creaCasella(2, Colore.GIALLO));
            parziali.get(2).add(creaCasella(12, Colore.VERDE));
            parziali.get(3).add(creaCasella(12, Colore.BLU));
            sequences.addAll(parziali);
            sequences.get(0).add(creaCasella(12, Colore.ROSSO, true));
            sequences.get(1).add(creaCasella(12, Colore.GIALLO, true));
            sequences.get(2).add(creaCasella(2, Colore.VERDE, true));
            sequences.get(3).add(creaCasella(2, Colore.BLU, true));
            sequences.get(0).sort(ORDINATORE);
            sequences.get(1).sort(ORDINATORE);
            sequences.get(2).sort(ORDINATORE.reversed());
            sequences.get(3).sort(ORDINATORE.reversed());
        }
        return sequences;
    }
    
    @Override
    protected Casella creaCasella(int numero, Colore colore, boolean terminatore)
    {
        return new Casella(numero, colore, null, terminatore);
    }
}
