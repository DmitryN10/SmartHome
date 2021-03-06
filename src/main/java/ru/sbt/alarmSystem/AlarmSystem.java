package ru.sbt.alarmSystem;

import ru.sbt.sensorEvent.SensorEvent;

/**
 * Created by User24 on 17.11.2017.
 */
public class AlarmSystem {
    private AlarmSystemState stateStrategy;

    public AlarmSystem() {
        AlarmSystemState alarmSystemState = new AlarmSystemOffState(this);
        this.stateStrategy = alarmSystemState;
    }

    public void setStateStrategy(AlarmSystemState stateStrategy) {
        this.stateStrategy = stateStrategy;
    }

    public void turnOn() {
        stateStrategy.turnOn();
    }
    public void turnOff() {
        stateStrategy.turnOff();
    }
    public void onSensor(SensorEvent sensorEvent) {
        stateStrategy.onSensor(sensorEvent);
    }
    public void typeCorrectPassword() {
        stateStrategy.typeCorrectPassword();
    }
    public void typeUncorrectPassword() {
        stateStrategy.typeIncorrectPassword();
    }

    public AlarmSystemStateEnum getState(){
        return stateStrategy.getState();
    }
}
