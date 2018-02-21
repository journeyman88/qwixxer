/*
 * Copyright 2018 m.bignami.
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
