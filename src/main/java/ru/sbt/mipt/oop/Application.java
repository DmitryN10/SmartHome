package ru.sbt.mipt.oop;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        // instantiate our spring dao object from the application context

        TimeMeasuringObservable observer = ctx.getBean(TimeMeasuringObservable.class);
        SensorEvent sensorEvent = ctx.getBean(SensorEvent.class);
        SmartHome smartHome = ctx.getBean(SmartHome.class);
        observer.runEvent(sensorEvent, smartHome);
//        FileEventDao fileEventDao = (FileEventDao)ctx.getBean("fileEventDao");
//
//        // create a FileEventType object from the application context
//        FileEventType fileEventType = (FileEventType)ctx.getBean("fileEventType");
//
//        // insert the file event with the spring dao
//        fileEventDao.doInsert(fileEventType);
//
//
//        SmartHome smartHome = SmartHomeFileReader.read();
//        // начинаем цикл обработки событий
//        SensorEvent event = getNextSensorEvent();
////        TimeMeasuringObservable observer = new TimeMeasuringObservable();
////        configureObserver(observer);
////        runEvent(observer, event, smartHome);
//        observer.runEvent(event, smartHome);
    }

//    private static void configureObserver(EventsObservable eventsObserver) {
////        eventsObserver.addHandler(new EventProcessorDecorator(new DoorEventProcessor()));
////        eventsObserver.addHandler(new EventProcessorDecorator (new LightEventProcessor()));
////        eventsObserver.addHandler(new EventProcessorDecorator (new ScenarioRunner()));
//        eventsObserver.addHandler(new DoorEventProcessor());
//        eventsObserver.addHandler(new LightEventProcessor());
//        eventsObserver.addHandler(new ScenarioRunner());
//    }

//    private static void runEvent(TimeMeasuringObservable observer, SensorEvent event, SmartHome smartHome) {
//        while (event != null) {
//            System.out.println("Got event: " + event);
//            observer.onSensorEvent(smartHome, event);
//            event = getNextSensorEvent();
//        }
//    }

    private static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }

    static SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}
