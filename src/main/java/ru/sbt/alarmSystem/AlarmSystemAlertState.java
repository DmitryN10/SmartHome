package ru.sbt.alarmSystem;

import ru.sbt.sensorEvent.SensorEvent;

/**
 * Created by user on 17.11.2017.
 */
public class AlarmSystemAlertState implements AlarmSystemState {
    private AlarmSystem alarmSystem;


    public AlarmSystemAlertState(AlarmSystem alarmSystem) {
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
        alarmSystem.setStateStrategy(new AlarmSystemWaitForPasswordState(alarmSystem));
    }

    @Override
    public void typeCorrectPassword() {
        alarmSystem.setStateStrategy(new AlarmSystemOffState(alarmSystem));
    }

    @Override
    public void typeIncorrectPassword() {}

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.ALERT;
    }
}
