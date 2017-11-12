package ru.sbt;


@FunctionalInterface
public interface EventProcessor {
    void processEvent(SmartHome smartHome, SensorEvent event);
}
