package modernjavainaction.chap05.daniel05;

import modernjavainaction.chap05.Trader;
import modernjavainaction.chap05.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DanielPrac05 {


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



}
