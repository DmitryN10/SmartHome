package ru.sbt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by User24 on 17.11.2017.
 */
public class AlarmSystemTest {

    @Test
    public void testNewSystemIsOff() {
        AlarmSystem alarmSystem = new AlarmSystem(new AlarmSystemOffState(new AlarmSystem()));
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void testSmth() {
        AlarmSystem alarmSystem = new AlarmSystem(state);
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void testWaitsForPasswordWhenSensorEvent() {
        AlarmSystem alarmSystem = new AlarmSystem(state);
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystem.onSensor(sensorEvent);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void test2(){
        AlarmSystem alarmSystem = new AlarmSystem(state);
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystem.onSensor(sensorEvent);
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    private SensorEvent createSensorEvent() {
        AlarmSystem alarmSystem = new AlarmSystem(state);
        return new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    }

    @Test
    public void testDiesNitReactionSensor(){
        AlarmSystem alarmSystem = new AlarmSystem(state);
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystem.onSensor(sensorEvent);
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }


}