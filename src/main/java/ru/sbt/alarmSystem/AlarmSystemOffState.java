package ru.sbt.alarmSystem;

import ru.sbt.sensorEvent.SensorEvent;

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
        alarmSystem.setStateStrategy(new AlarmSystemOnState(alarmSystem));
    }

    @Override
    public void onSensor(SensorEvent sensorEvent) {
        alarmSystem.setStateStrategy(new AlarmSystemWaitForPasswordState(alarmSystem));
    }

    @Override
    public void turnOff() {}

    @Override
    public void typeCorrectPassword() {}

    @Override
    public void typeIncorrectPassword() {}

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.OFF;
    }


}
