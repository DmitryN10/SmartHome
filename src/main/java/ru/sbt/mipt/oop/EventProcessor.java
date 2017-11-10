package ru.sbt.mipt.oop;


@FunctionalInterface
public interface EventProcessor {
    void processEvent(SmartHome smartHome, SensorEvent event);
}
