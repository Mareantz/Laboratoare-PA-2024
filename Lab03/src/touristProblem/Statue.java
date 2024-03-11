package touristProblem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Statue extends Attraction implements Visitable
{
    @Override
    public Map<LocalDate, TimeInterval> getTimetable()
    {
        return null;
    }

    @Override
    public LocalTime getOpeningHour(LocalDate date)
    {
        return Visitable.super.getOpeningHour(date);
    }
}
