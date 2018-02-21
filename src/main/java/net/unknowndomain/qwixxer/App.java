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

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author m.bignami
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final Options OPTIONS;
    
    static 
    {
        OPTIONS = new Options();
        OPTIONS.addOption("t", "text", false, "");
        OPTIONS.addOption("s", "sequencer", true, "");
        OPTIONS.addOption("o", "output", true, "");
        OPTIONS.addOption("p", "print", false, "");
        OPTIONS.addOption("h", "help", false, "");
    }
    
    public static void main(String [] args)
    {
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(OPTIONS, args);
            boolean printHelp = cmd.hasOption("h");
            if (cmd.hasOption("t"))
            {
                printHelp = printHelp  || !cmd.hasOption("s");
                boolean invalidOutput = cmd.hasOption("h");
                printHelp = printHelp  || invalidOutput;
                
            }
            if (printHelp)
            {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp( "qwixxer", OPTIONS, true);
            }
            else
            {
                if (cmd.hasOption("t"))
                {
                    
                }
                else
                {
                    MainGui.start();
                }
            }
        } catch (ParseException ex) {
            LOGGER.error(null, ex);
        }
    }
}
