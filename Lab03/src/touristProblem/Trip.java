package touristProblem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Trip
{
    private String city;
    private LocalDate startDate, endDate;

    public LocalDate getStartDate()
    {
        return startDate;
    }

    public void setStartDate(LocalDate startDate)
    {
        this.startDate = startDate;
    }

    public LocalDate getEndDate()
    {
        return endDate;
    }

    public void setEndDate(LocalDate endDate)
    {
        this.endDate = endDate;
    }

    private List<Attraction> attractions = new ArrayList<>();

    public Trip(String city)
    {
        this.city = city;
    }

    public Trip(String city, List<Attraction> attractions)
    {
        this.city = city;
        this.attractions = new ArrayList<>(attractions);
    }

    public void displayVisitableNotPayable(LocalDate date)
    {
        List<Visitable> visitableAttractions = attractions.stream()
                .filter(attraction -> attraction instanceof Visitable && !(attraction instanceof Payable))
                .map(attraction -> (Visitable) attraction)
                .sorted(Comparator.comparing(attraction -> attraction.getOpeningHour(date)))
                .collect(Collectors.toList());

        for (Visitable visitable : visitableAttractions)
        {
            System.out.println(((Attraction) visitable).getName());
        }
    }
}