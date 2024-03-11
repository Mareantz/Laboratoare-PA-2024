import touristProblem.Trip;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.Map;
import touristProblem.Statue;
import touristProblem.Church;
import touristProblem.Visitable;
import touristProblem.Concert;


public class Main
{
    public static void main(String[] args)
    {
        Trip tripPlanner = new Trip("Iasi");
        Statue eastStatue = new Statue();
        Statue northenStatue = new Statue();
        Church northernChurch = new Church();
        Concert middleConcert = new Concert();
        Concert beachPleaseConcert = new Concert();
    }
}