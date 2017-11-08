package ru.sbt.mipt.oop;

import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Дмитрий on 09.11.2017.
 */
public class EventsObserverTest {

    @Test
    public void testOnSensorEvent() throws Exception {
        EventProcessorDecorator doorProcessor = mock(EventProcessorDecorator.class);
        EventProcessorDecorator lightProcessor = mock(EventProcessorDecorator.class);
        EventProcessorDecorator scenarioProcessor = mock(EventProcessorDecorator.class);

        EventsObserver eventsObserver = new EventsObserver();
        eventsObserver.addHandler(doorProcessor);
        eventsObserver.addHandler(lightProcessor);
        eventsObserver.addHandler(scenarioProcessor);

        SmartHome smartHome = mock(SmartHome.class);
        SensorEvent sensorEvent = mock(SensorEvent.class);

        eventsObserver.onSensorEvent(smartHome, sensorEvent);

        verify(doorProcessor).processEvent(smartHome,sensorEvent);
        verify(lightProcessor).processEvent(smartHome,sensorEvent);
        verify(scenarioProcessor).processEvent(smartHome,sensorEvent);

    }
}