package cz.engeto.meteostation;

public class MeteoRecord {
    int year;
    int month;
    int day;
    float tempAvg;
    float tempMax;
    float tempMin;
    float pressure;
    float windSpeed;
    float humidity;
    float precipitation;

    final float windyThreshold = 4.2f;  // greater or equal
    final float calmThreshold = 1.8f;   // less or equal

    public MeteoRecord(int year, int month, int day, float tempAvg, float tempMax, float tempMin,
                       float pressure, float windSpeed, float humidity, float precipitation) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.tempAvg = tempAvg;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.precipitation = precipitation;
    }

    public MeteoRecord (String fromString, String delimiter){
        String[] fields = fromString.split(delimiter);
        try {
            this.year = Integer.parseInt(fields[0]);
            this.month = Integer.parseInt(fields[1]);
            this.day = Integer.parseInt(fields[2]);
            this.tempAvg = Float.parseFloat(fields[3]);
            this.tempMax = Float.parseFloat(fields[4]);
            this.tempMin = Float.parseFloat(fields[5]);
            this.pressure = Float.parseFloat(fields[6]);
            this.windSpeed = Float.parseFloat(fields[7]);
            this.humidity = Float.parseFloat(fields[8]);
            this.precipitation = Float.parseFloat(fields[9]);
        } catch (NumberFormatException e) {
            System.out.println("MeteoRecord parse error: "+fromString);
        }
    }

    public String getDate() {
        return String.format("%04d-%02d-%02d",year,month,day);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public float getTempAvg() { return tempAvg; }

    public float getTempMax() {
        return tempMax;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getPressure() {
        return pressure;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPrecipitation() {
        return precipitation;
    }

    public Boolean isWindy(){
        return this.windSpeed>= windyThreshold;
    }

    public Boolean isCalm(){
        return this.windSpeed<= calmThreshold;
    }

}
