package com.david.sys.entity;

import java.util.Date;

/**
 * Created by David on 2017/9/10.
 */
public class Weather {
    private float temp;
    private float temp_min;
    private float temp_max;
    private int humidity;
    private int pressure;
    private double speed;
    private int all;
    private Date sunrise;
    private Date sunset;
    private Date dt;

    public Date getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = new Date(dt);
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = (float)temp;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = (float)temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = (float)temp_max;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public Date getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = new Date(sunrise);
    }

    public Date getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = new Date(sunset);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "temp=" + temp +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", speed=" + speed +
                ", all=" + all +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", dt=" + dt +
                '}';
    }
}
