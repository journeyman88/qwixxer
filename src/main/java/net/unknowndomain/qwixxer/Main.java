/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unknowndomain.qwixxer;

import java.io.FileNotFoundException;
import net.unknowndomain.qwixxer.sequencers.FullRandomSequencer;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author journeyman
 */
public class Main {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    
    public static void main(String [] args) throws FileNotFoundException
    {
        LOGGER.info("Inizio {}", new Date());
        OutputStream pdfFile = new FileOutputStream("/home/m.bignami/Desktop/sample1.pdf");
        Generator.generatePdf(new FullRandomSequencer(), true, 10, pdfFile);
        LOGGER.info("Fine {}", new Date());
    }
    
}
