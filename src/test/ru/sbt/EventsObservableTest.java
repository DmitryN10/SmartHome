package ru.sbt;

import org.junit.Test;
import ru.sbt.eventHandler.EventProcessorDecorator;
import ru.sbt.eventHandler.TimeMeasuringObservable;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.smartHome.SmartHome;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Created by Дмитрий on 09.11.2017.
 */
public class EventsObservableTest {

    @Test
    public void testOnSensorEvent() throws Exception {
        EventProcessorDecorator doorProcessor = mock(EventProcessorDecorator.class);
        EventProcessorDecorator lightProcessor = mock(EventProcessorDecorator.class);
        EventProcessorDecorator scenarioProcessor = mock(EventProcessorDecorator.class);

        TimeMeasuringObservable timeMeasuringObserver = new TimeMeasuringObservable(
                Arrays.asList(doorProcessor, lightProcessor,scenarioProcessor));

        SmartHome smartHome = mock(SmartHome.class);
        SensorEvent sensorEvent = mock(SensorEvent.class);

        timeMeasuringObserver.onSensorEvent(smartHome, sensorEvent);

        verify(doorProcessor).processEvent(smartHome,sensorEvent);
        verify(lightProcessor).processEvent(smartHome,sensorEvent);
        verify(scenarioProcessor).processEvent(smartHome,sensorEvent);

    }
}