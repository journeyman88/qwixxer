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

import java.io.File;
import java.io.IOException;
import net.unknowndomain.qwixxer.sequencers.Sequencer;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author journeyman
 */
public class Main {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
    
    private final Sequencer sequencer;
    private final boolean randomEnd;
    private final int sheetNumber;
    
    public Main(Sequencer.Available sequencer, Boolean randomEnd, Integer sheetNumber)
    {
        this.sequencer = Sequencer.getInstance(sequencer);
        if (randomEnd == null)
        {
            randomEnd = true;
        }
        this.randomEnd = randomEnd;
        if (sheetNumber == null)
        {
            sheetNumber = 10;
        }
        this.sheetNumber = sheetNumber;
    }
    
    public void savePdf(String fileOutput)
    {
        try {
            File file = new File(fileOutput);
            if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("pdf")) {
                file = new File(file.toString() + ".pdf");
            }
            file.createNewFile();
            Generator.generatePdfFile(sequencer, randomEnd, sheetNumber, file);
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        }
    }
    
    public void printPdf()
    {
        try {
            File tmp = File.createTempFile("qwixxer", "pdf");
            Generator.generatePdfFile(sequencer, randomEnd, sheetNumber, tmp);
            Gutenberg.printPdfFileSilent(tmp);
            tmp.deleteOnExit();
        } catch (IOException ex) {
            LOGGER.error(null, ex);
        }
    }
    
}
