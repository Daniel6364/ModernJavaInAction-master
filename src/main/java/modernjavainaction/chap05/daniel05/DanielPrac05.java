package modernjavainaction.chap05.daniel05;

import modernjavainaction.chap05.Trader;
import modernjavainaction.chap05.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    private List<String> solution02() {
        return transactions.stream()
                           .map(transaction -> transaction.getTrader().getCity())
                           .distinct()
                           .collect(Collectors.toList());
    }

    private List<Trader> solution03() {
        return transactions.stream()
                           .map(Transaction::getTrader)
                           .filter(trader -> trader.getCity().equalsIgnoreCase("cambridge"))
                           .sorted(Comparator.comparing(Trader::getName))
                           .distinct()
                           .collect(Collectors.toList());
    }

    private String solution04() {
        return transactions.stream()
                           .map(transaction -> transaction.getTrader().getName())
                           .distinct()
                           .sorted()
                           .reduce("", (a, b) -> a + b);

    }

    /*private Optional<Trader> solution05() {
        return transactions.stream()
                           .map(Transaction::getTrader)
                           .filter(trader -> trader.getCity().equals("Milan"))
                           .findAny();

    }*/

    private boolean solution05() {
        return transactions.stream()
                           .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
    }


    private List<Integer> solution06() {

        return transactions.stream()
                           .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                           .map(Transaction::getValue)
                           .collect(Collectors.toList());
    }


    private Integer solution07() {
        return transactions.stream()
                           .map(Transaction::getValue)
                           .reduce(0, Integer::max);
    }

    private Optional<Transaction> solution08() {
        return transactions.stream()
                           .min(Comparator.comparing(Transaction::getValue));
    }


}
