package cz.uhk.secda1.MetawearFrequencyCounter;


import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CSVDataReader {

    public static final String INPUT_DELIMITER = ",";
    public static final String INPUT_DATE_FORMAT = "yyyyMMddHHmmssSSS";


    public static List<Long> processInputFile(String inputFilePath) {
        List<Long> inputList = new ArrayList<>();
        try {
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            System.out.println("Reading File " + inputFilePath);
            inputList = br.lines().map(mapToItem).collect(Collectors.toList());
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputList;
    }

    private static Function<String, Long> mapToItem = (line) -> {
        Long item;
        String[] p = line.split(INPUT_DELIMITER);
        SimpleDateFormat df = new SimpleDateFormat(INPUT_DATE_FORMAT);
        Long g = Long.parseLong(p[0]);

        item = new Long(g);


        return item;
    };
}
