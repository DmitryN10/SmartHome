package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Дмитрий on 08.11.2017.
 */
public class EventsObserver {
    private Collection<EventProcessor> processors = new ArrayList<>();

    public void addHandler(EventProcessorDecorator eventProcessorDecorator){
        processors.add(eventProcessorDecorator);
    }

    public void onSensorEvent(SmartHome smartHome, SensorEvent sensorEvent){
        for (EventProcessor processor: processors){
            processor.processEvent(smartHome, sensorEvent);
        }
    }

//    public abstract void addHandler(EventProcessor eventProcessor);
}
