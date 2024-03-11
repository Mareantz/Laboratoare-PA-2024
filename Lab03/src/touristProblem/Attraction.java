package touristProblem;

public abstract class Attraction implements Comparable<Attraction>
{
    private String name;
    private String description;
    private String image;

    @Override
    public int compareTo(Attraction other)
    {
        return this.name.compareTo(other.name);
        //not safe, check if name is null before
    }

}
