package ru.sbt.eventProcessor;

import java.util.Collection;

/**
 * Created by Дмитрий on 08.11.2017.
 */
public class TimeMeasuringObservable extends EventsObservable {
    public TimeMeasuringObservable(Collection<EventProcessor> processors) {
        super(processors);
    }

    @Override
    public void addProcessor(EventProcessor eventProcessor) {
        super.addProcessor(new EventProcessorDecorator(eventProcessor));
    }
}
