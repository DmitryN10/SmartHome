package ru.sbt;

import org.junit.Assert;
import org.junit.Test;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import ru.sbt.eventHandler.EventProcessorDecorator;
import ru.sbt.eventHandler.SmartHomeTestUtils;
import ru.sbt.eventHandler.TimeMeasuringObservable;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.smartHome.Door;
import ru.sbt.smartHome.Light;
import ru.sbt.smartHome.Room;
import ru.sbt.smartHome.SmartHome;

import java.util.*;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Дмитрий on 09.11.2017.
 */
public class EventsObservableTest {

    @Test
    public void testOnSensorEvent() throws Exception {
        EventProcessorDecorator doorProcessor = mock(EventProcessorDecorator.class);
        EventProcessorDecorator lightProcessor = mock(EventProcessorDecorator.class);
        EventProcessorDecorator scenarioProcessor = mock(EventProcessorDecorator.class);

        TimeMeasuringObservable timeMeasuringObserver = new TimeMeasuringObservable(
                asList(doorProcessor, lightProcessor, scenarioProcessor));

        SmartHome smartHome = mock(SmartHome.class);
        SensorEvent sensorEvent = mock(SensorEvent.class);

        timeMeasuringObserver.onSensorEvent(smartHome, sensorEvent);

        verify(doorProcessor).processEvent(smartHome, sensorEvent);
        verify(lightProcessor).processEvent(smartHome, sensorEvent);
        verify(scenarioProcessor).processEvent(smartHome, sensorEvent);

    }

    @Test
    public void test() {
        List<Light> lights1 = asList(
                new Light("1", false),
                new Light("2", true)
        );
        List<Light> lights2 = asList(
                new Light("1", false),
                new Light("2", true)
        );
        Room room1 = new Room(lights1,
                asList(new Door(false, "1")), "kitchen");
        Room room2 = new Room(lights2,
                asList(new Door(false, "1")), "bathroom");

        SmartHome smartHome = new SmartHome();
        smartHome.addRoom(room1);
        smartHome.addRoom(room2);

        Set<Room> roomSet = new HashSet<>();
        roomSet.add(room1);
        roomSet.add(room2);
        Set<Light> lightsSet = new HashSet<>();
        lightsSet.addAll(lights1);
        lightsSet.addAll(lights2);

        smartHome.executeAction(object -> {
            if (object instanceof Room) {
                roomSet.remove((Room) object);
            }
            if (object instanceof Light) {
                lightsSet.remove((Light) object);
            }
        });
        assertEquals(0, roomSet.size());
        assertEquals(0, lightsSet.size());

    }
}