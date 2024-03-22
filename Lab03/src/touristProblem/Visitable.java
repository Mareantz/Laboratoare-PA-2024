package touristProblem;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public interface Visitable
{
    Map<LocalDate, TimeInterval> getTimetable();

    default LocalTime getOpeningHour(LocalDate date)
    {
        if (getTimetable().containsKey(date))
        {
            return getTimetable().get(date).getOpeningHour();
        }
        else
        {
            return LocalTime.of(9, 0);
        }
    }
}
