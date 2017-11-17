package ru.sbt;

/**
 * Created by user on 17.11.2017.
 */
public class AlarmSystemWaitForPasswordState implements AlarmSystemState {
    private AlarmSystem alarmSystem;

    public AlarmSystemWaitForPasswordState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {
    }

    @Override
    public void onSensor(SensorEvent sensorEvent) {

    }
}
