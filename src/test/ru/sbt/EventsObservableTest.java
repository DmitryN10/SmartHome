package ru.sbt;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import ru.sbt.eventHandler.EventProcessorDecorator;
import ru.sbt.eventHandler.SmartHomeTestUtils;
import ru.sbt.eventHandler.TimeMeasuringObservable;
import ru.sbt.sensorEvent.SensorEvent;
import ru.sbt.smartHome.Door;
import ru.sbt.smartHome.Light;
import ru.sbt.smartHome.Room;
import ru.sbt.smartHome.SmartHome;

import java.util.*;

import static java.util.Arrays.*;
import static org.junit.Assert.*;
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
