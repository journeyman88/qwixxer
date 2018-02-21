/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.unknowndomain.qwixxer;

import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author journeyman
 */
public class Gutenberg {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Gutenberg.class);
    
    public static void printPdfFileDialog(File toPrint)
    {
        try (PDDocument document = PDDocument.load(toPrint)) 
        {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            if (job.printDialog()) {
                job.print();
            }
        } catch (IOException | PrinterException ex) {
            LOGGER.error("Errore in stampa",ex);
        }
    }
    
    public static void printPdfFileSilent(File toPrint)
    {
        try (PDDocument document = PDDocument.load(toPrint))
        {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPageable(new PDFPageable(document));
            job.print();
        } catch (IOException | PrinterException ex) {
            LOGGER.error("Errore in stampa",ex);
        }
    }

}
