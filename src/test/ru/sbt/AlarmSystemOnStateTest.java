package ru.sbt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 17.11.2017.
 */
public class AlarmSystemOnStateTest {
    AlarmSystem alarmSystem = new AlarmSystem();
    public AlarmSystem generateStateOn(){
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        return alarmSystem;
    }

    @Test
    public void turnOn() throws Exception {
        AlarmSystem alarmSystem = generateStateOn();
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void onSensor() throws Exception {
    }

    @Test
    public void turnOff() throws Exception {
    }

    @Test
    public void typeCorrectPassword() throws Exception {
    }

    @Test
    public void typeUncorrectPassword() throws Exception {
    }

}