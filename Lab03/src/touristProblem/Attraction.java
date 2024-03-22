package touristProblem;

import java.time.LocalDate;
import java.util.Map;

public abstract class Attraction implements Comparable<Attraction>
{
    private String name;
    private String description;
    private String image;

    private Map<LocalDate, TimeInterval> timetable;

    public String getName()
    {
        return name;
    }

    public Attraction(String name, String description, String image)
    {
        this.name = name;
        this.description = description;
        this.image = image;
    }

    public Attraction(String name, Map<LocalDate, TimeInterval> timetable)
    {
        this.name = name;
        this.timetable = timetable;
    }

    public Attraction(String name)
    {
        this.name = name;
    }

    @Override
    public int compareTo(Attraction other)
    {
        return this.name.compareTo(other.name);
        //not safe, check if name is null before
    }

}
