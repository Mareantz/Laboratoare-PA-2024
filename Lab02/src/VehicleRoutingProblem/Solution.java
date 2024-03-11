package VehicleRoutingProblem;

/**
 * The type Solution.
 */
public class Solution
{
    /**
     * The Problem.
     */
    Problem problem;

    /**
     * Instantiates a new Solution.
     *
     * @param problem the problem
     */
    public Solution(Problem problem)
    {
        this.problem = problem;
        problem.assignClients();
    }

}