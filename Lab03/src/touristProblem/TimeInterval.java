package touristProblem;

import java.time.LocalTime;

public class TimeInterval extends Pair<LocalTime, LocalTime>
{
    private LocalTime openingHour;
    private LocalTime closingHour;

    public TimeInterval(LocalTime openingHour, LocalTime closingHour)
    {
        super(openingHour, closingHour);
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public LocalTime getOpeningHour()
    {
        return this.openingHour;
    }

    public void setOpeningHour(LocalTime openingHour)
    {
        this.openingHour = openingHour;
    }

    public LocalTime getClosingHour()
    {
        return this.closingHour;
    }

    public void setClosingHour(LocalTime closingHour)
    {
        this.closingHour = closingHour;
    }
}