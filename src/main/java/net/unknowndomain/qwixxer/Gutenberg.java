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
