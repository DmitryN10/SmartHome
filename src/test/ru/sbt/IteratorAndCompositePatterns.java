package ru.sbt;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.smartHome.Door;
import ru.sbt.smartHome.Light;
import ru.sbt.smartHome.Room;
import ru.sbt.smartHome.SmartHome;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class IteratorAndCompositePatterns {
    private SmartHome smartHome;
    private Collection<Room> rooms;
    private Collection<Door> doors;
    private Collection<Light> lights;

    @Before
    public void setUp() {
        List<Light> lights1 = asList(
                new Light("1", false),
                new Light("2", true)
        );
        List<Light> lights2 = asList(
                new Light("1", false),
                new Light("2", true)
        );
        List<Door> doors1 = asList(
                new Door(false, "1"),
                new Door(true, "2")
        );
        List<Door> doors2 = asList(
                new Door(false, "3"),
                new Door(true, "4")
        );
        Room room1 = new Room(lights1,
                doors1, "kitchen");
        Room room2 = new Room(lights2,
                doors2, "bathroom");

        smartHome = new SmartHome();
        smartHome.addRoom(room1);
        smartHome.addRoom(room2);

        rooms = new HashSet<>();
        rooms.add(room1);
        rooms.add(room2);

        lights = new HashSet<>();
        lights.addAll(lights1);
        lights.addAll(lights2);

        doors = new HashSet<>();
        doors.addAll(doors1);
        doors.addAll(doors2);
    }

    @Test
    public void testForRooms() {
        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                rooms.remove(object);
            }
            if (object instanceof Light) {
                lights.remove(object);
            }
            if (object instanceof Door) {
                doors.remove(object);
            }
        });
        assertEquals(0, rooms.size());
        assertEquals(0, lights.size());
        assertEquals(0, doors.size());
    }

    @Test
    public void testOnlyForRooms() {
        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                rooms.remove(object);
            }
        });
        assertEquals(rooms.size(), 0);
    }

    @Test
    public void testForLightsAndDoors() {
        int countRooms = smartHome.getRooms().size();
        smartHome.executeAction(object -> {
            if (object instanceof Light) {
                lights.remove(object);
            }
            if (object instanceof Door) {
                doors.remove(object);
            }
        });
        assertEquals(0, lights.size());
        assertEquals(0, doors.size());
        assertEquals(countRooms, rooms.size());
    }
}
