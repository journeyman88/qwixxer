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

import net.unknowndomain.qwixxer.sequencers.Sequencer;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionGroup;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author journeyman
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final Options OPTIONS;
    
    static 
    {
        OPTIONS = new Options();
        OPTIONS.addOption("t", "text", false, "Activate text-mode (if not set every other oprtion is discarded)");
        OptionGroup og = new OptionGroup();
        og.addOption(new Option("f", "full-random",  false, "Select Full Random Scoresheet generator"));
        og.addOption(new Option("c", "random-color", false, "Select Random Color Scoresheet generator"));
        og.addOption(new Option("r", "random-order", false, "Select Random Order Scoresheet generator"));
        OPTIONS.addOptionGroup(og);
        og = new OptionGroup();
        Option tmp = new Option("o", "output", true, "Set the output file");
        tmp.setArgName("outfile");
        og.addOption(tmp);
        og.addOption(new Option("p", "print", false, "Set the software to send the output directly to printer"));
        OPTIONS.addOptionGroup(og);
        tmp = new Option("n", "number", true, "Set the number of scoresheet to generate");
        tmp.setArgName("num");
        OPTIONS.addOption(tmp);
        OPTIONS.addOption("e", "defined-ends", false, "Set if scoresheet must have the right end predefined");
        OPTIONS.addOption("h", "help", false, "Print this help");
    }
    
    public static void main(String [] args)
    {
        HelpFormatter formatter = new HelpFormatter();
        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(OPTIONS, args);
            boolean printHelp = cmd.hasOption("h");
            if (cmd.hasOption("t"))
            {
                boolean invalidSequencer = !(cmd.hasOption("f") ^ cmd.hasOption("c") ^ cmd.hasOption("r"));
                boolean invalidOutput = !((cmd.hasOption("o") && cmd.getOptionValue("o") != null) ^ cmd.hasOption("p"));
                printHelp = printHelp  || invalidOutput || invalidSequencer;
            }
            if (printHelp)
            {
                formatter.printHelp( "qwixxer", OPTIONS, true);
            }
            else
            {
                if (cmd.hasOption("t"))
                {
                    Sequencer.Available seq = null;
                    if (cmd.hasOption("f"))
                    {
                        seq = Sequencer.Available.FULL_RANDOM;
                    }
                    if (cmd.hasOption("c"))
                    {
                        seq = Sequencer.Available.RANDOM_COLOR;
                    }
                    if (cmd.hasOption("r"))
                    {
                        seq = Sequencer.Available.RANDOM_ORDER;
                    }
                    Integer number = null;
                    if (cmd.hasOption("n"))
                    {
                        number = Integer.parseInt(cmd.getOptionValue("n", "10"));
                    }
                    Boolean defined = cmd.hasOption("e");
                    Main main = new Main(seq, defined, number);
                    if (cmd.hasOption("p"))
                    {
                        main.printPdf();
                    }
                    if (cmd.hasOption("o"))
                    {
                        main.savePdf(cmd.getOptionValue("o"));
                    }
                }
                else
                {
                    MainGui.start();
                }
            }
        } catch (ParseException ex) {
            LOGGER.error(null, ex);
            formatter.printHelp( "qwixxer", OPTIONS, true);
        }
    }
}
