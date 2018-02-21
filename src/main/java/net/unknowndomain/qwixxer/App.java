/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
