package ru.sbt;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static ru.sbt.SmartHomeTestUtils.creatSmartHome;

public class DoorEventProcessorTest extends TestCase {

    @Test
    public void testOpenSomeDoors() {
        SmartHome smartHome = creatSmartHome(true, false);
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            for (Door door : room.getDoors()){
                assertFalse(door.isOpen());
            }
        }

        String[] closedDoors = new String[]{"2", "3", "4", "5"};
        DoorEventProcessor processor = new DoorEventProcessor();
        for (String doorId : closedDoors) {
            SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
            processor.processEvent(smartHome, event);
        }

        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(closedDoors)) {
                    assertTrue(door.isOpen());
                } else {
                    assertFalse(door.isOpen());
                }
            }
        }
    }

    @Test
    public void testCloseSomeDoors() {
        SmartHome smartHome = creatSmartHome();
        Collection<Room> rooms = smartHome.getRooms();
        for (Room room : rooms) {
            for (Door door : room.getDoors()){
                assertTrue(door.isOpen());
            }
        }

        String[] closedDoors = new String[]{"2", "3", "4", "5"};
        DoorEventProcessor processor = new DoorEventProcessor();
        for (String doorId : closedDoors) {
            SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, doorId);
            processor.processEvent(smartHome, event);
        }

        for (Room room : rooms) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(closedDoors)) {
                    assertFalse(door.isOpen());
                } else {
                    assertTrue(door.isOpen());
                }
            }
        }
    }

}