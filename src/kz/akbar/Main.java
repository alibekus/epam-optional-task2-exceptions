package kz.akbar;

import kz.akbar.doubleNumberReader.ReadNumberFile;
import kz.akbar.exception.DoubleNumberFileException;
import kz.akbar.exception.DoubleNumberRangeException;
import kz.akbar.exception.NumberSymbolException;
import kz.akbar.util.DoubleNumberHandler;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        LOG.log(Level.INFO,Main.class.getSimpleName());
        ReadNumberFile readNumberFile = null;
        readNumberFile = new ReadNumberFile();
        LOG.info("Reading numbers file...");
        try {
            readNumberFile.readLines("rsrc/float_numbers.txt");
        } catch (NumberSymbolException e) {
            e.printStackTrace();
        } catch (DoubleNumberFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DoubleNumberRangeException e) {
            e.printStackTrace();
        }
        LOG.info("Double numbers: ");
        LOG.info(readNumberFile.getDoubleNumbers().toString());
        DoubleNumberHandler handler = new DoubleNumberHandler();
        LOG.info("Sum of doubles: " + handler.sumOfDoubles(readNumberFile.getDoubleNumbers()));
        LOG.info("Average of doubles: " + handler.averageOfDoubles(readNumberFile.getDoubleNumbers()));
    }
}
