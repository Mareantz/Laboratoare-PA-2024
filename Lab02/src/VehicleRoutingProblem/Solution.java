package VehicleRoutingProblem;

public class Solution
{
    Problem problem;

    public Solution(Problem problem)
    {
        this.problem = problem;
        problem.assignClients();
    }

}