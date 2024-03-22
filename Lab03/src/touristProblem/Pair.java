package touristProblem;

import java.util.Objects;

public class Pair<T, U>
{
    private T first;
    private U second;

    public Pair(T first, U second)
    {
        this.first = first;
        this.second = second;
    }

    public T getFirst()
    {
        return first;
    }

    public void setFirst(T first)
    {
        this.first = first;
    }

    public U getSecond()
    {
        return second;
    }

    public void setSecond(U second)
    {
        this.second = second;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (!(o instanceof Pair<?, ?> pair))
        {
            return false;
        }
        return Objects.equals(getFirst(), pair.getFirst()) && Objects.equals(getSecond(), pair.getSecond());
    }

    @Override
    public String toString()
    {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}