package ru.sbt.alarmSystem;

import org.junit.Test;
import ru.sbt.alarmSystem.AlarmSystem;
import ru.sbt.alarmSystem.AlarmSystemStateEnum;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.sensorEvent.SensorEventType;

import static org.junit.Assert.*;

/**
 * Created by user on 17.11.2017.
 */
public class AlarmSystemWaitForPasswordStateTest {
    private SensorEvent createSensorEvent() {
        return new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    }

    private AlarmSystem generateStateWait() {
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        alarmSystem.onSensor(createSensorEvent());
        return alarmSystem;
    }

    @Test
    public void turnOff() {
        AlarmSystem alarmSystem = generateStateWait();
        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void typeCorrectPassword() {
        AlarmSystem alarmSystem = generateStateWait();
        alarmSystem.typeCorrectPassword();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void typeUncorrectPassword() {
        AlarmSystem alarmSystem = generateStateWait();
        alarmSystem.typeUncorrectPassword();
        assertEquals(AlarmSystemStateEnum.ALERT, alarmSystem.getState());
    }

    @Test
    public void otherMethods() {
        AlarmSystem alarmSystem = generateStateWait();
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
        alarmSystem.onSensor(createSensorEvent());
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

}