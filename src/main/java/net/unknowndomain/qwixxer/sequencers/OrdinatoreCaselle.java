/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unknowndomain.qwixxer.sequencers;

import net.unknowndomain.qwixxer.common.Casella;
import java.util.Comparator;

/**
 *
 * @author journeyman
 */
public class OrdinatoreCaselle implements Comparator<Casella> {

    @Override
    public int compare(Casella o1, Casella o2) {
        Integer v = o1.getValore();
        return v.compareTo(o2.getValore());
    }
    
}
