package cz.engeto.meteostation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MeteoWorker {


    public static List<MeteoRecord> read(String filePath) {

        List<MeteoRecord> result = new ArrayList<MeteoRecord>();
        String line;
        String delimiter=",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ( (line = br.readLine()) != null) {
                //System.out.println(line);
                // starts with year detected as 4 digit number, other lines ignored
                if (line.matches("^[0-9]{4},.*")) {
                    MeteoRecord meteoRecord = new MeteoRecord(line, delimiter);
                    result.add(meteoRecord);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return result;
    }

    public static void drawGraph(List<MeteoRecord> meteoRecords){
        String scale = "   | 00--------10--------20--------30--------40";
        System.out.println(scale);
        for ( MeteoRecord rec: meteoRecords ) {
            StringBuffer s = new StringBuffer(" ");
            for (int i = 1; i < 40; i++) {
                s.append( i>=rec.tempMin && i<=rec.tempMax ? "*" : " " );
            }
            System.out.println(String.format("%02d | %s",rec.day, s));
        }
        System.out.println(scale);
    }

}
