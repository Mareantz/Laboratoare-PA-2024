public class Bonus
{
    static boolean wheelGraph(int n)
    {
        int[][] matrix = new int[n][n];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(i==j)
                {
                    matrix[i][j]=0;
                }
                else if (i==0||j==0)
                {
                    matrix[i][j]=1;
                }
                else if(i==j+1||j==i+1)
                {
                    matrix[i][j]=1;
                }
                else if(i==n-1&&j==1)
                {
                    matrix[i][j]=1;
                }
                else if(j==n-1&&i==1)
                {
                    matrix[i][j]=1;
                }

            }
        }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        int cycleCount=0;
        //print all the cycles

        int length=3;
        while (length<=n)
        {
            for(int i=1;i<=n-1;i++)
            {
                System.out.print("0 -> ");
                for(int j=i-1;j<length-2+i;j++)
                {
                    System.out.print(j % (n-1)+1 + " -> ");
                }
                System.out.println("0");
                cycleCount++;
            }
            length++;
            System.out.println();
        }
        for(int i=1;i<=n;i++)
        {
            System.out.print(i);
            if(i!=n)
            {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        cycleCount++;
        return cycleCount==n*n-3*n+3;

    }

    public static void main(String[] args)
    {
        if(wheelGraph(7))
        {
            System.out.println("You found all the cycles!");
        }
        else
        {
            System.out.println("You didn't find all the cycles!");
        }
    }
}
