package com.adg.object;


public class Weather {
    // in common
    String percip;
    String weatherCode;
    String value;
    String windPoint;
    String windDegree;
    String kmph;
    String mph;
    // 5-day
    String date;
    String maxF;
    String minF;
    String maxC;
    String minC;
    // current
    String cloudcover;
    String humidity;
    String time;
    String pressure;
    String C;
    String F;
    String visibility;
	
    public Weather() {}

    public Weather(String percip, String weatherCode, String value,
            String windPoint, String windDegree, String kmph, String mph,
            String cloudcover, String humidity, String time, String pressure,
            String c, String f, String visibility) {
        this.percip = percip;
        this.weatherCode = weatherCode;
        this.value = value;
        this.windPoint = windPoint;
        this.windDegree = windDegree;
        this.kmph = kmph;
        this.mph = mph;
        this.cloudcover = cloudcover;
        this.humidity = humidity;
        this.time = time;
        this.pressure = pressure;
        this.C = c;
        this.F = f;
        this.visibility = visibility;
    }

    public Weather(String date, String percip, String weatherCode, String value,
            String windPoint, String windDegree, String kmph, String mph,
            String maxF, String minF, String maxC, String minC) {
        this.percip = percip;
        this.weatherCode = weatherCode;
        this.value = value;
        this.windPoint = windPoint;
        this.windDegree = windDegree;
        this.kmph = kmph;
        this.mph = mph;
        this.date = date;
        this.maxF = maxF;
        this.minF = minF;
        this.maxC = maxC;
        this.minC = minC;
    }

    public String getPercip() {
        return percip;
    }

    public void setPercip(String percip) {
        this.percip = percip;
    }

    public String getWeatherCode() {
        return weatherCode;
    }

    public void setWeatherCode(String weatherCode) {
        this.weatherCode = weatherCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getWindPoint() {
        return windPoint;
    }

    public void setWindPoint(String windPoint) {
        this.windPoint = windPoint;
    }

    public String getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(String windDegree) {
        this.windDegree = windDegree;
    }

    public String getKmph() {
        return kmph;
    }

    public void setKmph(String kmph) {
        this.kmph = kmph;
    }

    public String getMph() {
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaxF() {
        return maxF;
    }

    public void setMaxF(String maxF) {
        this.maxF = maxF;
    }

    public String getMinF() {
        return minF;
    }

    public void setMinF(String minF) {
        this.minF = minF;
    }

    public String getMaxC() {
        return maxC;
    }

    public void setMaxC(String maxC) {
        this.maxC = maxC;
    }

    public String getMinC() {
        return minC;
    }

    public void setMinC(String minC) {
        this.minC = minC;
    }

    public String getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(String cloudcover) {
        this.cloudcover = cloudcover;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getF() {
        return F;
    }

    public void setF(String f) {
        F = f;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
