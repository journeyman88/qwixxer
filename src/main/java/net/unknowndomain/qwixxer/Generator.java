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

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Phaser;
import java.util.logging.Level;
import net.unknowndomain.qwixxer.common.Casella;
import net.unknowndomain.qwixxer.imaging.Scoresheet;
import net.unknowndomain.qwixxer.sequencers.Sequencer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author journeyman
 */
public class Generator {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Generator.class);
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();
    
    public static final byte [] generatePdfData(Sequencer sequencer, boolean randomEnd, int sheetNumber)
    {
        ByteArrayOutputStream pdf = new ByteArrayOutputStream();
        generatePdf(sequencer, randomEnd, sheetNumber, pdf);
        return pdf.toByteArray();
    }
    
    public static final void generatePdfFile(Sequencer sequencer, boolean randomEnd, int sheetNumber, File outputFile)
    {
        try (FileOutputStream pdf = new FileOutputStream(outputFile)) {
            generatePdf(sequencer, randomEnd, sheetNumber, pdf);
        } catch (IOException ex) {
            LOGGER.error("Eccezione in scrittura pdf",ex);
        }
    }
    
    public static final void generatePdf(Sequencer sequencer, boolean randomEnd, int sheetNumber, OutputStream outputStream)
    {
        Phaser phaser = new Phaser();
        phaser.register();
        try {
            List<Future<BufferedImage>> results = new ArrayList<>(sheetNumber);
            for (int i = 0; i<sheetNumber; i++)
            {
                results.add(EXECUTOR.submit(new Scoresheet(sequencer, randomEnd, phaser)));
            }
            Document document = new Document(PageSize.A4);
            document.setMargins(30, 30, 30, 30);
            PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(90f);
            phaser.arriveAndAwaitAdvance();
            for (Future<BufferedImage> score : results)
            {
                Image pdfImg = Image.getInstance(score.get(), null);
                table.addCell(pdfImg);
            }
            if ((sheetNumber % 2) > 0)
            {
                table.addCell("");
            }
            document.add(table);
            document.close();
        } catch (DocumentException | IOException | InterruptedException | ExecutionException ex) {
            LOGGER.error("Eccezione in generazione pdf",ex);
        }
        phaser.arriveAndDeregister();
    }
    
}
