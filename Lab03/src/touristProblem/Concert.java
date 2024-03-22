package touristProblem;

import java.time.LocalDate;
import java.util.Map;


public class Concert extends Attraction implements Visitable, Payable
{
    private Map<LocalDate, TimeInterval> timetable;
    private double ticketPrice;

    public Concert(String name, Map<LocalDate, TimeInterval> timetable, double ticketPrice)
    {
        super(name);
        this.timetable = timetable;
        this.ticketPrice = ticketPrice;
    }

    public Concert(String name)
    {
        super(name);
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable()
    {
        return timetable;
    }

    public void setTimetable(Map<LocalDate, TimeInterval> timetable)
    {
        this.timetable = timetable;
    }

    @Override
    public double getTicketPrice()
    {
        return ticketPrice;
    }

    @Override
    public String getType() {
        return "Concert";
    }

}
