package touristProblem;

import java.util.List;

public class Node
{
    private Attraction attraction;
    private List<Integer> colorList;
    private int color;

    public Node(Attraction attraction, List<Integer> colorList)
    {
        this.attraction = attraction;
        this.colorList = colorList;
    }

    public Attraction getAttraction()
    {
        return attraction;
    }

    public List<Integer> getColorList()
    {
        return colorList;
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }
}