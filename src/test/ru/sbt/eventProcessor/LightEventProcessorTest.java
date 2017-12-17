package ru.sbt.eventProcessor;
import org.junit.Test;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.sensorEvent.SensorEventType;
import ru.sbt.smartHome.Light;
import ru.sbt.smartHome.Room;
import ru.sbt.smartHome.SmartHome;

import java.util.Collection;

import static org.junit.Assert.*;
import static ru.sbt.eventProcessor.SmartHomeTestUtils.*;

public class LightEventProcessorTest {

    @Test
    public void testTurnOffSomeLights() {
        SmartHome smartHome = creatSmartHome();
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        String[] turnedOffLightId = new String[]{"2", "3", "4", "5"};
        LightEventProcessor processor = new LightEventProcessor();
        for (String lightId : turnedOffLightId) {
            SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, lightId);
            processor.processEvent(smartHome, event);
        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(turnedOffLightId)) {
                    assertFalse(light.isOn());
                } else {
                    assertTrue(light.isOn());
                }
            }
        }
    }

    @Test
    public void testTurnOnSomeLights() {
        SmartHome smartHome = creatSmartHome(false, true);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }

        String[] turnedOffLightId = new String[]{"2", "3", "4", "5"};
        LightEventProcessor processor = new LightEventProcessor();
        for (String lightId : turnedOffLightId) {
            SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, lightId);
            processor.processEvent(smartHome, event);
        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(turnedOffLightId)) {
                    assertTrue(light.isOn());
                } else {
                    assertFalse(light.isOn());
                }
            }
        }
    }
}
