package modernjavainaction.chap05.daniel05;

import junit.framework.TestCase;
import modernjavainaction.chap05.Trader;
import modernjavainaction.chap05.Transaction;
import modernjavainaction.chap10.dsl.model.Trade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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


    private List<String> solution02() {
        return transactions.stream()
                           .map(transaction -> transaction.getTrader().getCity())
                           .distinct()
                           .collect(Collectors.toList());
    }

    @Test
    public void solution01Test() {

        List<Transaction> expectResult = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2011, 400)
        );

        List<Transaction> result = transactions.stream()
                                               .filter(transaction -> transaction.getYear() == 2011)
                                               .sorted(Comparator.comparing(Transaction::getValue))
                                               .collect(Collectors.toList());

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);
    }

    @Test
    public void solution02Test() {

        List<String> expectResult = Arrays.asList("Cambridge", "Milan");

        List<String> result = transactions.stream()
                                          .map(transaction -> transaction.getTrader().getCity())
                                          .distinct()
                                          .collect(Collectors.toList());

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);
    }

    @Test
    public void solution03Test() {

        List<Trader> expectResult = Arrays.asList(alan, brian, raoul);

        List<Trader> result = transactions.stream()
                                          .map(Transaction::getTrader)
                                          .filter(trader -> trader.getCity().equalsIgnoreCase("cambridge"))
                                          .sorted(Comparator.comparing(Trader::getName))
                                          .distinct()
                                          .collect(Collectors.toList());

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);

    }

    @Test
    public void solution04Test() {

        String expectResult = "AlanBrianMarioRaoul";

        String result = transactions.stream()
                                    .map(transaction -> transaction.getTrader().getName())
                                    .distinct()
                                    .sorted()
                                    .reduce("", (a, b) -> a + b);

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);

    }


    /*@Test
    public void solution05() {

        Optional<Trader> result = transactions.stream()
                                              .map(Transaction::getTrader)
                                              .filter(trader -> trader.getCity().equals("Milan"))
                                              .findAny();
        System.out.println("result : " + result);
    }*/

    @Test
    public void solution05Test() {

        boolean expectResult = true;

        boolean result = transactions.stream()
                                     .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);

    }

    @Test
    public void solution06Test() {

        List<Integer> expectResult = Arrays.asList(300, 1000, 400, 950);

        List<Integer> result = transactions.stream()
                                           .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                                           .map(Transaction::getValue)
                                           .collect(Collectors.toList());

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);

    }

    @Test
    public void solution07Test() {

        int expectResult = 1000;

        int result = transactions.stream()
                                 .map(Transaction::getValue)
                                 .reduce(0, Integer::max);

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);
    }

    @Test
    public void solution08Test() {

        Optional<Transaction> expectResult = Optional.of(new Transaction(brian, 2011, 300));

        Optional<Transaction> result = transactions.stream()
                                                   .min(Comparator.comparing(Transaction::getValue));

        Assert.assertEquals(expectResult, result);

        System.out.println("expectResult : " + expectResult);
        System.out.println("result : " + result);
    }

}