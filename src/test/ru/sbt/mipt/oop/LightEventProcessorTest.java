package ru.sbt.mipt.oop;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LightEventProcessorTest {
    @Test

    public void testTurnOffLight() {
        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 10; i++) lights.add(new Light(String.valueOf(i + 1), true));

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 5; i++) doors.add(new Door(true, String.valueOf(i + 1)));


        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            rooms.add(new Room(lights, doors, "Room #" + String.valueOf(i + 1)));
        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertTrue(light.isOn());
            }
        }

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        String turnedOffLightId = "2";

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, turnedOffLightId);
        LightEventProcessor processor = new LightEventProcessor();
        processor.processEvent(smartHome, event);

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

    //Turn on a light.
    //Initial state: all lights are off
    //Desired ultimate state: all lights are off except one
    @Test
    public void testTurnOnLight() {
        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lights.add(new Light(String.valueOf(i + 1), false));
        }

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            doors.add(new Door(true, String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            rooms.add(new Room(lights, doors, "Room #" + String.valueOf(i + 1)));
        }

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                assertFalse(light.isOn());
            }
        }

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }

        String turnedOnLightId = "2";

        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, turnedOnLightId);
        LightEventProcessor processor = new LightEventProcessor();
        processor.processEvent(smartHome, event);

        for (Room room : rooms) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(turnedOnLightId)) {
                    assertTrue(light.isOn());
                } else {
                    assertFalse(light.isOn());
                }
            }
        }
    }
}
