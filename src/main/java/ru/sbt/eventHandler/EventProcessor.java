package ru.sbt.eventHandler;


import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.smartHome.SmartHome;

@FunctionalInterface
public interface EventProcessor {
    void processEvent(SmartHome smartHome, SensorEvent event);
}
