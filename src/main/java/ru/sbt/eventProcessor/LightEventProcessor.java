package ru.sbt.eventProcessor;

import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.sensorEvent.SensorEventType;
import ru.sbt.smartHome.Light;
import ru.sbt.smartHome.Room;
import ru.sbt.smartHome.SmartHome;

public class LightEventProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (!isLightEvent(event)) {
            return;
        }
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    if (event.getType() == SensorEventType.LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == SensorEventType.LIGHT_ON || event.getType() == SensorEventType.LIGHT_OFF;
    }
}
