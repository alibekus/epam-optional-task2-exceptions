package kz.akbar.doubleNumberReader;

import kz.akbar.exception.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ReadNumberFile {
    private static Logger LOG = Logger.getLogger(ReadNumberFile.class.getName());
    private List<Double> doubleNumbers;
    private static final int MAX_VALUE_LENGTH = 308;
    private static final long MAX_VALUE_SIGNIFICANT_NUMBER = 17976931348623157L;
    private static final int SIGNIFICANT_NUMBER_LENGTH = 17;
    private int[] significantNumbers = new int[SIGNIFICANT_NUMBER_LENGTH];
//    private static final int[] SIGNIFICANT_NUMBERS = new int[SIGNIFICANT_NUMBER_LENGTH];

    public ReadNumberFile() {
        doubleNumbers = new ArrayList<>();
    }

    public List<Double> getDoubleNumbers() {
        return doubleNumbers;
    }

    public void readLines(String numbersFilePath) throws NumberSymbolException, DoubleNumberFileException, IOException, DoubleNumberRangeException {
        StringBuilder numberLineBuilder = new StringBuilder();
        int c;
        File numbersFile = new File(numbersFilePath);
        if (!numbersFile.exists()) {
            throw new NumberFileExistException("File not exists");
        }
        if (numbersFile.length() == 0) {
            throw new DoubleNumberFileException("File is empty");
        }
        if (!numbersFile.canRead()) {
            throw new DoubleNumberFileException("File cannot to be read");
        }
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(numbersFilePath))) {
            int position = 1;
            String numberString = "";
            long significantNumber = 0;
            int moreThanZeroCounter = 0;
            while ((c = bufferedReader.read()) != -1) {
                if (!Character.isDigit((char) c)) {
                    if ((char) c == ',') {
                        c = (int) '.';
                    }
                } else if (position <= MAX_VALUE_LENGTH & (char) c != '.') {
                    if (position <= SIGNIFICANT_NUMBER_LENGTH) {
                        significantNumbers[position - 1] = (char) c;
                        if (position == SIGNIFICANT_NUMBER_LENGTH) {
                            significantNumber = Arrays.stream(significantNumbers).reduce(0, (left, right) -> left * 10 + right);
                        }
                    } else if ((char) c > 0) {
                        moreThanZeroCounter++;
                    } else if (position == MAX_VALUE_LENGTH && significantNumber > MAX_VALUE_SIGNIFICANT_NUMBER && moreThanZeroCounter > 0) {
                        throw new DoubleNumberRangeException("The number is bigger than Java Double.MAX_VALUE");
                    }
                } else {
                    throw new DoubleNumberRangeException("The amount of digits in the number is exceed of positive double range");
                }
                if (c == 10 || c == 32 || c == 13) {
                    numberString = numberLineBuilder.toString();
                    try {
                        doubleNumbers.add(Double.parseDouble(numberString));
                        LOG.info(numberString);
                    } catch (NumberFormatException exc) {
                        new NumberFormatException("Wrong double format number");
                    }
                    numberLineBuilder = new StringBuilder();
                    position = 1;
                }
                numberLineBuilder.append((char) c);
                position++;
            }
            doubleNumbers.add(Double.parseDouble(numberString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
