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
public class AlarmSystemOnStateTest {

    private AlarmSystem generateStateOn(){
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
        AlarmSystem alarmSystem = generateStateOn();
        alarmSystem.onSensor(createSensorEvent());
        assertEquals(AlarmSystemStateEnum.WAIT_FOR_PASSWORD, alarmSystem.getState());
    }

    private SensorEvent createSensorEvent() {
        return new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    }

    @Test
    public void turnOff() throws Exception {
        AlarmSystem alarmSystem = generateStateOn();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }

    @Test
    public void otherMethods() throws Exception {
        AlarmSystem alarmSystem = generateStateOn();
        alarmSystem.typeCorrectPassword();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
        alarmSystem.typeUncorrectPassword();
        assertEquals(AlarmSystemStateEnum.ON, alarmSystem.getState());
    }
}