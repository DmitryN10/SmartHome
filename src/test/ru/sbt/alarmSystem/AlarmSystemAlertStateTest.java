package ru.sbt.alarmSystem;

import org.junit.Test;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.sensorEvent.SensorEventType;

import static org.junit.Assert.assertEquals;

/**
 * Created by user on 17.11.2017.
 */
public class AlarmSystemAlertStateTest {
    private SensorEvent createSensorEvent() {
        return new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    }

    private AlarmSystem generateStateAlert() {
        AlarmSystem alarmSystem = new AlarmSystem();
        alarmSystem.turnOn();
        alarmSystem.onSensor(createSensorEvent());
        alarmSystem.typeUncorrectPassword();
        return alarmSystem;
    }

    @Test
    public void onSensor() {
        AlarmSystem alarmSystem = generateStateAlert();
        alarmSystem.onSensor(createSensorEvent());
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void turnOff() {
        AlarmSystem alarmSystem = generateStateAlert();
        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    @Test
    public void typeCorrectPassword() {
        AlarmSystem alarmSystem = generateStateAlert();
        alarmSystem.typeCorrectPassword();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }

    @Test
    public void otherMethods() {
        AlarmSystem alarmSystem = generateStateAlert();
        alarmSystem.turnOn();
        assertEquals(AlarmSystemStateEnum.ALERT, alarmSystem.getState());
        alarmSystem.typeUncorrectPassword();
        assertEquals(AlarmSystemStateEnum.ALERT, alarmSystem.getState());
    }
}