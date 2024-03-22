package touristProblem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Church extends Attraction implements Visitable
{
    private Map<LocalDate, TimeInterval> timetable;

    public Church(String name, Map<LocalDate, TimeInterval> timetable)
    {
        super(name);
        this.timetable = timetable;
    }

    public void setTimetable(Map<LocalDate, TimeInterval> timetable)
    {
        this.timetable = timetable;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable()
    {
        return this.timetable;
    }


}