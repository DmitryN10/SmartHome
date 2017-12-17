package ru.sbt;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ru.sbt.eventProcessor.EventProcessorDecorator;
import ru.sbt.eventProcessor.TimeMeasuringObservable;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.smartHome.SmartHome;

import static java.util.Arrays.*;
import static org.mockito.Mockito.*;

/**
 * Created by Дмитрий on 09.11.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EventsObservableTest {
    @Mock
    EventProcessorDecorator doorProcessor;
    @Mock
    EventProcessorDecorator lightProcessor;
    @Mock
    EventProcessorDecorator scenarioProcessor;
    @Mock
    SmartHome smartHome;
    @Mock
    SensorEvent sensorEvent;

    @Test
    public void testOnSensorEvent() throws Exception {
        TimeMeasuringObservable timeMeasuringObserver = new TimeMeasuringObservable(
                asList(doorProcessor, lightProcessor, scenarioProcessor));
        timeMeasuringObserver.onSensorEvent(smartHome, sensorEvent);

        verify(doorProcessor).processEvent(smartHome, sensorEvent);
        verify(lightProcessor).processEvent(smartHome, sensorEvent);
        verify(scenarioProcessor).processEvent(smartHome, sensorEvent);
    }
}
