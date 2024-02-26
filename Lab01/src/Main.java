public class Main
{
    private static final long startTimeNano = System.nanoTime();
    private static final long startTimeMillis = System.currentTimeMillis();
    static boolean reductible(int k,int n)
    {
        //System.out.printf("Numarul "+n);
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
        if(n==k)
        {
            //System.out.println(" este " +k+ "-reductibil");
            return true;
        }
        else
        {
            //System.out.println(" nu este " +k+ "-reductibil");
            return false;
        }
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

    static void wheelGraph(int n)
    {

    }

    public static void main(String[] args)
    {
        if (args.length < 3) {
            System.out.println("Not enough arguments!");
            System.exit(-1);
        }
        System.out.println("Hello world!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n *= 3;
        n += 0b10101;
        n += 0xFF;
        n *= 6;
        System.out.println("n = " + n);
        while (n > 9)
        {
            int sum = 0;
            while (n != 0)
            {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);

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