package org.example.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Collections;

public class Bag
{
    private List<Token> tokens;
    private int numberOfTokens;
    private Random rand;

    public Bag(int numberOfTokens)
    {
        this.numberOfTokens = numberOfTokens;
        this.tokens = new ArrayList<>(numberOfTokens);
        this.rand = new Random();
        List<Integer> values = IntStream.range(1, 1001).boxed().collect(Collectors.toList());
        Collections.shuffle(values, rand);

        // Take the first numberOfTokens values from the shuffled list
        values = values.subList(0, numberOfTokens);
        int value1,value2;
        int firstValue=values.get(0);
        value1=values.remove(0);
        for (int i = 0; i < numberOfTokens; i++)
        {
            if(i==numberOfTokens-1)
            {
                value2=firstValue;
                tokens.add(new Token(value1,value2));
                break;
            }
            value2=values.remove(0);
            tokens.add(new Token(value1,value2));
            value1=value2;
        }
    }

    public synchronized List<Token> extractTokens(int howMany)
    {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++)
        {
            if (tokens.isEmpty())
            {
                break;
            }
            int randomIndex= rand.nextInt(tokens.size());
            Token extractedToken=tokens.remove(randomIndex);
            extracted.add(extractedToken);
        }
        this.numberOfTokens = tokens.size();
        return extracted;
    }

    @Override
    public String toString()
    {
        return "Bag{" +
                "tokens=" + tokens +
                '}';
    }

    public int getNumberOfTokens()
    {
        return numberOfTokens;
    }
}