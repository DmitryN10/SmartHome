package ru.sbt;

/**
 * Created by User24 on 17.11.2017.
 */
public class AlarmSystemOffState implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemOffState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {
        alarmSystem.setStateStrategy(new AlarmSystemOffState(alarmSystem));
    }

    @Override
    public void onSensor(SensorEvent sensorEvent) {
        alarmSystem.onSensor(sensorEvent);
    }


}
