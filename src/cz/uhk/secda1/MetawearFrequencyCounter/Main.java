package cz.uhk.secda1.MetawearFrequencyCounter;

import java.io.File;
import java.util.*;

public class Main {

    public static final String PATH = "ADD PATH HERE ";
    public static void main(String[] args) {
        File[] files = new File(PATH).listFiles();
        List<File> validatedFiles = filterFiles(files);

        Map<File, List<Long>> map = new HashMap<>();


        //files = new File[]{files[2]};
        for (File file : validatedFiles) {
            List<Long> data = CSVDataReader.processInputFile(file.getAbsolutePath());
            List<Long> frequencies = countFreqencies(data);
            map.put(file, frequencies);
        }
        CSVDataWriter.saveData(map,PATH + "/out.csv");
        // write your code here
    }

    private static List<Long> countFreqencies(List<Long> data) {
        int offset = 0;
        long first;
        long current;
        List<Long> frequencies = new ArrayList<>();

        for (int i = 1; i < data.size(); i++) {
            first = data.get(offset);
            //System.out.println(first);
            current = data.get(i);
            long delta = (current - first);
            //System.out.println("i: " + i + " first "+ first + " ;current "+ current + " delta " + delta);
            if (delta > 1000){
                long freq = (i-1) - offset;
                System.out.println("offset changed from " + offset + "to " + (i-1) + " freq: "+ freq);
                offset = (i - 1);
                frequencies.add(freq);
            }
        }
        return frequencies;
    }

    private static List<File> filterFiles(File[] files) {
        List<File> validatedFiles = new ArrayList<>();
        for (File f : files) {
            String extension = f.getName().substring(f.getName().lastIndexOf('.')+1);
            if (extension.equalsIgnoreCase("CSV")){
                validatedFiles.add(f);
            }
            else{
                System.out.println(f.getName() + " removed from processing.");
            }
        }
        Collections.sort(validatedFiles);
        return validatedFiles;
    }
}

