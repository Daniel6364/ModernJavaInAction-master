package modernjavainaction.chap05.daniel05;

import junit.framework.TestCase;
import modernjavainaction.chap05.Trader;
import modernjavainaction.chap05.Transaction;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DanielPrac05Test {


    private static Trader raoul = new Trader("Raoul", "Cambridge");
    private static Trader mario = new Trader("Mario", "Milan");
    private static Trader alan = new Trader("Alan", "Cambridge");
    private static Trader brian = new Trader("Brian", "Cambridge");

    private static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


    private List<Transaction> solution01() {

        return transactions.stream()
                           .filter(transaction -> transaction.getYear() == 2011)
                           .sorted(Comparator.comparing(Transaction::getValue))
                           .collect(Collectors.toList());

    }


    @Test
    public void solution01Test() {

        List<Trader> expectResult = Arrays.asList(brian, raoul);
        List<Transaction> expectResult2 = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2011, 400)
        );

        List<Transaction> result = solution01();

        Assert.assertEquals(expectResult2, result);

        System.out.println("expectResult2 : " + expectResult2);
        System.out.println("result : " + result);

    }
}