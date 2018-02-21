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
public class OrderRandomSequencer extends Sequencer {
    
    private static final Comparator<Casella> DISORDINATORE = new DisordinatoreCaselle();
    
    
    @Override
    public List<List<Casella>> creaSequenze(boolean terminatoreRandom)
    {
        List<List<Casella>> parziali = creaParziali();
        List<List<Casella>> sequences = new ArrayList<>(4);
        if (terminatoreRandom)
        {
            List<Casella> terminatori = new ArrayList<>(4);
            List<Integer> numPool = new ArrayList<>();
            numPool.addAll(Arrays.asList(new Integer [] {2, 2, 12, 12}));
            for (List<Casella> tempSeq : parziali)
            {
                Colore cv = tempSeq.get(0).getColore();
                Double dn = RANDOMIZER.get().nextDouble() * numPool.size();
                int idxn = dn.intValue();
                int nv = numPool.get(idxn);
                numPool.remove(idxn);
                tempSeq.add(creaCasella(nv, cv));
                if (nv<10)
                {
                    tempSeq.add(creaCasella(12, cv, true));
                }
                else
                {
                    tempSeq.add(creaCasella(2, cv, true));
                }
                sequences.add(tempSeq);
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
        }
        for (List<Casella> line : sequences)
        {
            line.sort(DISORDINATORE);
        }
        return sequences;
    }
    
    @Override
    protected List<List<Casella>> creaParziali()
    {
        List<List<Casella>> retVal = new ArrayList<>(4);
        int [] numeri = new int [] {3,4,5,6,7,8,9,10,11};
        for (Colore c : Colore.values())
        {
            List<Casella> temp = new ArrayList<>(10);
            for (int numero : numeri)
            {
                temp.add(creaCasella(numero, c));
            }
            retVal.add(temp);
        }
        return retVal;
    }
}
