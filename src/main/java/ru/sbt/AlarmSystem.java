package ru.sbt;

/**
 * Created by User24 on 17.11.2017.
 */
public class AlarmSystem {

    private AlarmSystemState stateStrategy;
    private AlarmSystemStateEnum state;

    public AlarmSystem(AlarmSystemState stateStrategy) {
        this.stateStrategy = stateStrategy;
        this.state = AlarmSystemStateEnum.OFF;
    }

    public void setStateStrategy(AlarmSystemState stateStrategy) {
        this.stateStrategy = stateStrategy;
    }

    public AlarmSystemStateEnum getState() {
        return state;
    }

    public void turnOn() {
        stateStrategy.turnOn();
    }

    public void onSensor(SensorEvent sensorEvent) {
        stateStrategy.onSensor(sensorEvent);
    }
}
