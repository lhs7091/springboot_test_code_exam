package guru.springframework.dollor;

public abstract class Money {
    protected int amount;

    public abstract Money times(int multiplier);

    public static Money dollar(int amount){
        return new Dollar(amount);
    }

    public static Money franc(int amount){
        return new Franc(amount);
    }

    public boolean equals(Object object){
        Money money = (Money) object;
        // it means Dollar == Franc
        // return amount == money.amount;
        return amount == money.amount && this.getClass().equals(object.getClass());
    }
}
