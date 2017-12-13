package ru.sbt.eventHandler;

import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.sensorEvent.SensorEventType;
import ru.sbt.smartHome.*;

public class ScenarioRunner implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isDoorClosedEvent(event)) {
            return;
        }
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (room.getName().equals("hall")) {
                        for (Room homeRoom : smartHome.getRooms()) {
                            for (Light light : homeRoom.getLights()) {
                                SmartHomeManager.turnOffLight(light);
                            }
                        }
                    }
                    System.out.println("All lights were turned off.");
                }
            }
        }
    }

    private boolean isDoorClosedEvent(SensorEvent event) {
        return event.getType() == SensorEventType.DOOR_CLOSED;
    }
}
