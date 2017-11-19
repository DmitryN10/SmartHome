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
        alarmSystem.turnOff();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
        alarmSystem.typeCorrectPassword();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
        alarmSystem.typeUncorrectPassword();
        assertEquals(AlarmSystemStateEnum.OFF, alarmSystem.getState());
    }
}