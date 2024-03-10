package VehicleRoutingProblem;
import java.time.LocalTime;
public class Client
{
    private enum type
    {
        regular,premium
    }
    private int id;
    private final String name;
    private LocalTime visitStartTime;
    private LocalTime visitEndTime;

    public Client(String name,int id, LocalTime visitStartTime, LocalTime visitEndTime)
    {
        this.name = name;
        this.id=id;
        this.visitStartTime = visitStartTime;
        this.visitEndTime = visitEndTime;
    }

    public String getName()
    {
        return name;
    }

    public LocalTime getVisitStartTime()
    {
        return visitStartTime;
    }

    public void setVisitStartTime(LocalTime visitStartTime)
    {
        this.visitStartTime = visitStartTime;
    }

    public LocalTime getVisitEndTime()
    {
        return visitEndTime;
    }

    public void setVisitEndTime(LocalTime visitEndTime)
    {
        this.visitEndTime = visitEndTime;
    }

    public int getId()
    {
        return id;
    }

    @Override
    public String toString()
    {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", visitStartTime=" + visitStartTime +
                ", visitEndTime=" + visitEndTime +
                '}';
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass())  // if both objects are null and not of the same class
        {
            return false;
        }
        Client guest = (Client) obj;
        return id==guest.id; // id is unique so it's enough
    }
}
