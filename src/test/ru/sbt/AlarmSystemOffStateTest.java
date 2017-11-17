package ru.sbt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by user on 17.11.2017.
 */
public class AlarmSystemOffStateTest {
    @Test
    public void turnOn() throws Exception {
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void onSensor() throws Exception {
        AlarmSystem alarmSystem = new AlarmSystem();
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystem.onSensor(sensorEvent);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }
    private SensorEvent createSensorEvent() {
        return new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    }


    @Test
    public void otherMethods() throws Exception {
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOf();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
        alarmSystem.typeCorrectPassword();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
        alarmSystem.typeUncorrectPassword();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }
}