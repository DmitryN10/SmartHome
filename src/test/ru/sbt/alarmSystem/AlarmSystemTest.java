package ru.sbt.alarmSystem;

import org.junit.Test;
import ru.sbt.alarmSystem.AlarmSystem;
import ru.sbt.alarmSystem.AlarmSystemStateEnum;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.sensorEvent.SensorEventType;

import static org.junit.Assert.*;

/**
 * Created by User24 on 17.11.2017.
 */
public class AlarmSystemTest {
    @Test
    public void testNewSystemIsOff() {
        AlarmSystem alarmSystem = new AlarmSystem();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void testSmth() {
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void testWaitsForPasswordWhenSensorEvent() {
        AlarmSystem alarmSystem = new AlarmSystem();
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystem.onSensor(sensorEvent);
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void test2(){
        AlarmSystem alarmSystem = new AlarmSystem();
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystem.onSensor(sensorEvent);
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    private SensorEvent createSensorEvent() {
        return new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    }

    @Test
    public void testDiesNitReactionSensor(){
        AlarmSystem alarmSystem = new AlarmSystem();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void testTurnOffAlert(){
        AlarmSystem alarmSystem = new AlarmSystem();
        SensorEvent sensorEvent = createSensorEvent();
        alarmSystem.onSensor(sensorEvent);
        alarmSystem.turnOn();
        alarmSystem.typeUncorrectPassword();
        assertEquals(AlarmSystemStateEnum.ALERT, alarmSystem.getState());
    }
}