import java.util.*;

class Card {
    private String symbol;
    private int number;

    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return symbol + " " + number;
    }
}

public class CardManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, List<Card>> cardMap = new TreeMap<>();

        System.out.print("Enter Number of Cards: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter card " + i + ":");
            String symbol = scanner.next();
            int number = scanner.nextInt();
            scanner.nextLine();
            
            cardMap.putIfAbsent(symbol, new ArrayList<>());
            cardMap.get(symbol).add(new Card(symbol, number));
        }

        System.out.println("Distinct Symbols are:");
        for (String symbol : cardMap.keySet()) {
            System.out.print(symbol + " ");
        }
        System.out.println();

        for (Map.Entry<String, List<Card>> entry : cardMap.entrySet()) {
            String symbol = entry.getKey();
            List<Card> cards = entry.getValue();
            int sum = 0;

            System.out.println("Cards in " + symbol + " Symbol");
            for (Card card : cards) {
                System.out.println(card);
                sum += card.getNumber();
            }

            System.out.println("Number of cards: " + cards.size());
            System.out.println("Sum of Numbers: " + sum);
        }
    }
}
