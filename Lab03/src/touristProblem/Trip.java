package touristProblem;
import java.time.LocalTime;
import java.time.LocalDate;

public class Trip
{
    private String city;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public Trip(String city)
    {
        this.city = city;
    }
}
