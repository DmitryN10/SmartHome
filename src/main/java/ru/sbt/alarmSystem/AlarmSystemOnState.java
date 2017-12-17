package ru.sbt.alarmSystem;

import ru.sbt.sensorEvent.SensorEvent;

/**
 * Created by user on 17.11.2017.
 */
public class AlarmSystemOnState implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemOnState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {}

    @Override
    public void onSensor(SensorEvent sensorEvent) {
        alarmSystem.setStateStrategy(new AlarmSystemWaitForPasswordState(alarmSystem));
    }

    @Override
    public void turnOff() {
        alarmSystem.setStateStrategy(new AlarmSystemOffState(alarmSystem));
    }

    @Override
    public void typeCorrectPassword() {}

    @Override
    public void typeIncorrectPassword() {}

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ON;
    }
}
