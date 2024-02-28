public class Homework
{
    private static final long startTimeNano = System.nanoTime();
    private static final long startTimeMillis = System.currentTimeMillis();
    static boolean reductible(int k,int n)
    {
        if(k>9||k<=0)
        {
            System.out.println("k trebuie sa fie mai mic decat 9");
            return false;
        }
        while(n>9)
        {
            int aux=0;
            while(n!=0)
            {
                aux+=(n%10)*(n%10);
                n/=10;
            }
            n=aux;
        }
        return n == k;
    }

    static void reductibleInterval(int k,int a,int b)
    {
        StringBuilder identifiedNumbers= new StringBuilder();
        for(int i=a;i<=b;i++)
        {
            if(reductible(k, i))
            {
                identifiedNumbers.append(i).append(", ");
                System.out.printf(i + ", ");
            }
        }
        System.out.println();
        System.out.println(identifiedNumbers);
    }
    public static void main(String[] args)
    {
        if (args.length < 3)
        {
            System.out.println("Not enough arguments!");
            System.exit(-1);
        }
        String str = args[0];
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);


        reductibleInterval( 1,1,100);
        reductibleInterval( c,a,b);

        long elapsedTimeInNanos = System.nanoTime() - startTimeNano;
        System.out.println("Elapsed time in nanoseconds: " + elapsedTimeInNanos);

        long elapsedTimeInMillis = System.currentTimeMillis() - startTimeMillis;
        System.out.println("Elapsed time in milliseconds: " + elapsedTimeInMillis);
    }
}
