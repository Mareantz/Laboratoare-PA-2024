package touristProblem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Statue extends Attraction implements Visitable
{
    private Map<LocalDate, TimeInterval> timetable;

    public Statue(String name, Map<LocalDate, TimeInterval> timetable)
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

    @Override
    public String getType() {
        return "Church";
    }

}