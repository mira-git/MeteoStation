package cz.engeto.meteostation;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String inputFilePath = "./resources/B2BTUR01_07_2019.csv";
        String delimiterLine = "--------------------------------------------------------------------------------";

        System.out.println(delimiterLine);
        System.out.println("Welcome to the application for Meteorological Data Analysis.");
        List<MeteoRecord> meteoRecords = MeteoWorker.read(inputFilePath);
        System.out.println(delimiterLine);
        System.out.println(String.format("We have %d meteorological daily records to analyze.",meteoRecords.size()));

        double avgTemp = meteoRecords
                .stream()
                .mapToDouble(r -> r.getTempAvg())
                .average()
                .getAsDouble();
        System.out.println(String.format("Average temperature for the reporting period: %.1f°C",avgTemp));

        MeteoRecord maxTempRec = meteoRecords
                .stream()
                .reduce( (r1,r2) -> r1.getTempMax() > r2.getTempMax() ? r1 : r2  )
                .orElse(null);
        System.out.println(String.format("Maximum temperature for the reporting period: %s was %.1f°C",
                maxTempRec.getDate(),maxTempRec.getTempMax() ));

        MeteoRecord minTempRec = meteoRecords
                .stream()
                .reduce( (r1,r2) -> r1.getTempMin() < r2.getTempMin() ? r1 : r2  )
                .orElse(null);
        System.out.println(String.format("Minimum temperature for the reporting period: %s was %.1f°C",
                minTempRec.getDate(),minTempRec.getTempMin() ));

        Integer countWindyDays = meteoRecords
                .stream()
                .mapToInt( r -> r.isWindy() ? 1 : 0 )
                .sum();
        System.out.println(String.format("Number of windy days: %d", countWindyDays));

        Integer countCalmDays = meteoRecords
                .stream()
                .mapToInt( r -> r.isCalm() ? 1 : 0 )
                .sum();
        System.out.println(String.format("Number of calm days: %d", countCalmDays));

        double precipitation1 = meteoRecords
                .stream()
                .limit(10)
                .mapToDouble(r -> r.getPrecipitation())
                .sum();
        double precipitation2 = meteoRecords
                .stream()
                .skip(10)
                .limit(10)
                .mapToDouble(r -> r.getPrecipitation())
                .sum();
        double precipitation3 = meteoRecords
                .stream()
                .skip(20)
                .mapToDouble(r -> r.getPrecipitation())
                .sum();
        System.out.println(String.format("Precipitation summary in month decades: %.1fmm - %.1fmm - %.1fmm",
                precipitation1, precipitation2, precipitation3));
        System.out.println(delimiterLine);
        MeteoWorker.drawGraph(meteoRecords);
        System.out.println(delimiterLine);

    }
}
