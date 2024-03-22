package touristProblem;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class TravelPlan
{
    private Map<LocalDate, List<Attraction>> plan;

    public TravelPlan(Map<LocalDate, List<Attraction>> plan)
    {
        this.plan = plan;
    }

    public Map<LocalDate, List<Attraction>> getPlan()
    {
        return plan;
    }

    public void setPlan(Map<LocalDate, List<Attraction>> plan)
    {
        this.plan = plan;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<LocalDate, List<Attraction>> entry : plan.entrySet())
        {
            sb.append("Date: ").append(entry.getKey()).append("\n");
            sb.append("Attractions: ").append("\n");
            for (Attraction attraction : entry.getValue())
            {
                sb.append(attraction.getName());
                if (attraction instanceof Visitable)
                {
                    TimeInterval interval = ((Visitable) attraction).getTimetable().get(entry.getKey());
                    if (interval != null)
                    {
                        sb.append(" (").append(interval.getOpeningHour()).append(" - ").append(interval.getClosingHour()).append(")");
                    }
                }
                sb.append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}