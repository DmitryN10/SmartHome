package ru.sbt;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sbt.eventProcessor.TimeMeasuringObservable;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.sensorEvent.SensorEventType;
import ru.sbt.smartHome.SmartHome;

public class Application {

    public static void main(String... args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");

        TimeMeasuringObservable observer = ctx.getBean(TimeMeasuringObservable.class);
        SensorEvent sensorEvent = ctx.getBean(SensorEvent.class);
        SmartHome smartHome = ctx.getBean(SmartHome.class);

        observer.runEvent(sensorEvent, smartHome);
    }

    public static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
