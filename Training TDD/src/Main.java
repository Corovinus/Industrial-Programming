import java.util.Hashtable;

public class Main {
    public static void main(String[] args) {
    }
}

class Money implements Expression {
    protected int amount;
    protected String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public Money reduce(String to) {
        int rate = (currency.equals("CHF") && to.equals("USD"))
                ? 2
                : 1;
        return new Money(amount / rate, to);
    }
    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }
    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && currency(). equals(money.currency());
    }

    String currency() {
        return currency;
    }

    Expression plus(Money addend) {
        return new Sum(this, addend);
    }

    Money times(int multiplier) {
        return new Money (amount * multiplier, currency);
    }
}

interface Expression {
    Money reduce(String to);
}
class Pair {
    private String from;
    private String to;
    Pair(String from, String to) {
        this.from = from;
        this.to = to;
    }
    public boolean equals(Object object) {
        Pair pair = (Pair) object;
        return from.equals(pair.from) && to.equals(pair.to);
    }
    public int hashCode() {
        return 0;
    }
}
class Bank {
    private Hashtable rates= new Hashtable();
    Money reduce(Expression source, String to) {
        return source.reduce(to);
    }
    void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), Integer.valueOf(rate));
    }
    int rate(String from, String to) {
        if (from.equals(to)) return 1;
        Integer rate = (Integer) rates.get(new Pair(from, to));
        return rate.intValue();
    }
}
class Sum implements Expression {
    Money augend;
    Money addend;
    Sum(Money augend, Money addend) {
        this.augend = augend;
        this.addend = addend;
    }
    public Money reduce(String to) {
        int amount = augend.amount + addend.amount;
        return new Money(amount, to);
    }
}