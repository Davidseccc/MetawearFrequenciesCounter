package cz.uhk.secda1.MetawearFrequencyCounter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CSVDataWriter {
    public static final String COMMA_DELIMITER = ";";
    public static final String NEW_LINE_SEPARATOR = "\n";

    public static void saveData(Map<File, List<Long>> map, String outputFile) {

        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(outputFile);

            for (File file : map.keySet()) {
                fileWriter.append(file.getName());
                fileWriter.append(COMMA_DELIMITER);
                List<Long> frequencies = map.get(file);
                for (int i=0; i < frequencies.size()-1; i++) {
                    fileWriter.append(frequencies.get(i).toString());
                    fileWriter.append(COMMA_DELIMITER);
                }
                fileWriter.append(frequencies.get(frequencies.size()-1).toString());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
