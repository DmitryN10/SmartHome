package ru.sbt.alarmSystem;

import ru.sbt.sensorEvent.SensorEvent;

/**
 * Created by user on 17.11.2017.
 */
public class AlarmSystemWaitForPasswordState implements AlarmSystemState {
    private AlarmSystem alarmSystem;

    public AlarmSystemWaitForPasswordState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {}

    @Override
    public void onSensor(SensorEvent sensorEvent) {}

    @Override
    public void turnOff() {
        alarmSystem.setStateStrategy(new AlarmSystemOffState(alarmSystem));
    }

    @Override
    public void typeCorrectPassword() {
        alarmSystem.setStateStrategy(new AlarmSystemOnState(alarmSystem));
    }

    @Override
    public void typeIncorrectPassword() {
        alarmSystem.setStateStrategy(new AlarmSystemAlertState(alarmSystem));
    }

    @Override
    public AlarmSystemStateEnum getState() {
        return AlarmSystemStateEnum.WAIT_FOR_PASSWORD;
    }
}
