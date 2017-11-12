package ru.sbt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static java.util.Collections.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ScenarioRunnerTest {

    @Test
    public void testSce() {
        SmartHome smartHome = SmartHomeTestUtils.creatSmartHome(true, true);
        Light light = new Light("1", true);
        String hallDoorId = "Door #1";
        Room hall = new Room(
                singletonList(new Light("Light #1", true)),
                singletonList(new Door(false, hallDoorId)),
                "hall");
        smartHome.addRoom(hall);

        for (Room room : smartHome.getRooms()) {
            if (!room.equals(hall)) {
                for (Door door : room.getDoors()) {
                    assertTrue(door.isOpen());
                }
            }
            if (room.getName().equals("hall")) {
                for (Door door : room.getDoors()) {
                    assertFalse(door.isOpen());
                }
            }
        }

        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, hallDoorId);
        ScenarioRunner scenarioRunner = new ScenarioRunner();
        scenarioRunner.processEvent(smartHome, event);

        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            for (Light light1 : room.getLights()) {
                assertFalse(light1.isOn());
            }
        }
    }

}
