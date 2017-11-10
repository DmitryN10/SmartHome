package ru.sbt.mipt.oop;

import java.util.Collection;

/**
 * Created by Дмитрий on 08.11.2017.
 */
public class TimeMeasuringObservable extends EventsObservable {
    public TimeMeasuringObservable(Collection<EventProcessor> processors) {
        super(processors);
    }

    @Override
    public void addHandler(EventProcessor eventProcessor) {
        super.addHandler(new EventProcessorDecorator(eventProcessor));
    }
}
