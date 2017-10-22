package ru.sbt.mipt.oop;

/**
 * Created by Дмитрий on 22.10.2017.
 */
public class EventProcessorDecorator implements EventProcessor {
    EventProcessor eventProcessor;

    public EventProcessorDecorator(EventProcessor eventProcessor) {
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        System.out.println(System.currentTimeMillis());
        eventProcessor.processEvent(smartHome, event);
        System.out.println(System.currentTimeMillis() + " after ");
    }
}
