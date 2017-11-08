package ru.sbt.mipt.oop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String... args) throws IOException {
        SmartHome smartHome = SmartHomeFileReader.read();
        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        TimeMeasuringObserver observer = new TimeMeasuringObserver();
        configureObserver(observer);
        runEvent(observer, event, smartHome);
    }

    private static void configureObserver(EventsObserver eventsObserver) {
        eventsObserver.addHandler(new EventProcessorDecorator(new DoorEventProcessor()));
        eventsObserver.addHandler(new EventProcessorDecorator (new LightEventProcessor()));
        eventsObserver.addHandler(new EventProcessorDecorator (new ScenarioRunner()));
    }

    private static void runEvent(TimeMeasuringObserver observer, SensorEvent event, SmartHome smartHome) {
        while (event != null) {
            System.out.println("Got event: " + event);
            observer.onSensorEvent(smartHome, event);
            event = getNextSensorEvent();
        }
    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    private static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
