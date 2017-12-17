package ru.sbt.eventProcessor;

import ru.sbt.Application;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.smartHome.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Дмитрий on 08.11.2017.
 */
public abstract class EventsObservable {
    private Collection<EventProcessor> processors = new ArrayList<>();

    public EventsObservable(Collection<EventProcessor> processors) {
        this.processors = processors;
    }

    public void addProcessor(EventProcessor eventProcessor){
        processors.add(eventProcessor);
    }

    public void onSensorEvent(SmartHome smartHome, SensorEvent sensorEvent){
        for (EventProcessor processor: processors){
            processor.processEvent(smartHome, sensorEvent);
        }
    }


    public void runEvent(SensorEvent event, SmartHome smartHome) {
        while (event != null) {
            System.out.println("Got event: " + event);
            this.onSensorEvent(smartHome, event);
            event = Application.getNextSensorEvent();
        }
    }
}
