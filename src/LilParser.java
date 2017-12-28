import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

/*
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>23.6-jre</version>
    </dependency>
*/

public class LilParser {
    // path to logfile
    private static final String FILENAME = "C:\\Users\\fahas\\Desktop\\Master\\semester1\\Fortgeschrittene Softwaretechnik\\Übung\\SDRaytracer\\log.log";

    public static void main(String[] args){

        Multimap<String, String> multiMap = ArrayListMultimap.create();

        BufferedReader br = null;
        FileReader fr = null;

        try {
            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String line;

            while ((line = br.readLine()) != null) {
                String[] lineContent = line.split(";");
                multiMap.put(lineContent[0], lineContent[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        Set<String> keys = multiMap.keySet();
        for (String key : keys) {
            double avg = 0;
            System.out.println("Method = " + key);

            Collection<String> val = multiMap.get(key);
            for (String v : val){
                avg += Long.parseLong(v);
            }
            System.out.println("absolute calls = " + val.size());
            System.out.println("timeAvg = " + ((avg/val.size()) / 1000000000L) + "s\n");
        }
    }
}
