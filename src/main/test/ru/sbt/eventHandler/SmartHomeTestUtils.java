package ru.sbt.eventHandler;

import ru.sbt.smartHome.Door;
import ru.sbt.smartHome.Light;
import ru.sbt.smartHome.Room;
import ru.sbt.smartHome.SmartHome;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

/**
 * Created by Дмитрий on 12.11.2017.
 */
public class SmartHomeTestUtils {
    public static SmartHome creatSmartHome(){
        return creatSmartHome(true,true);
    }

    public static SmartHome creatSmartHome(boolean allLightsIsOn, boolean allDoorsIsOpen){

        SmartHome smartHome = new SmartHome();
        ArrayList<Light> lights = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            lights.add(new Light("Light #" + String.valueOf(i + 1), allLightsIsOn));
        }

        ArrayList<Door> doors = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            doors.add(new Door(allDoorsIsOpen, "Door #" + String.valueOf(i + 1)));
        }

        ArrayList<Room> rooms = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            rooms.add(new Room(lights, doors, "Room #" + String.valueOf(i + 1)));
        }

        for (Room room : rooms) {
            smartHome.addRoom(room);
        }
        return smartHome;
    }
}
