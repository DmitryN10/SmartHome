package ru.sbt;

/**
 * Created by User24 on 17.11.2017.
 */
public interface AlarmSystemState {
    void turnOn();
    void onSensor(SensorEvent sensorEvent);
}

