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

    static Money dollar(int amount) {
        return new Money(amount, "USD");
    }
    static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public boolean equals(Object object) {
        Money money = (Money) object;
        return amount == money.amount
                && currency(). equals(money.currency());    }

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

}

class Bank {
    Money reduce(Expression source, String to) {
        if (source instanceof Money) return (Money) source;
        Sum sum= (Sum) source;
        return sum.reduce(to);
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