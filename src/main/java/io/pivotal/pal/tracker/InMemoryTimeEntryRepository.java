package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntryMap = new HashMap<Long, TimeEntry>();

    private AtomicLong id = new AtomicLong(1);

    @Override
    public TimeEntry create(TimeEntry timeEntry) {

        timeEntry.setId(id.getAndIncrement());

        timeEntryMap.put(timeEntry.getId(), timeEntry);
        return (TimeEntry)timeEntryMap.get(timeEntry.getId());
    }

    @Override
    public TimeEntry find(long id) {

        return timeEntryMap.get(id);
    }


    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
       if(find(id) != null){
           timeEntry.setId(id);
           timeEntryMap.put(id, timeEntry);

           return timeEntry;
       }
        return null;
    }

    @Override
    public void delete(long id) {

        timeEntryMap.remove(id);

    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntryMap.values());
    }
}
