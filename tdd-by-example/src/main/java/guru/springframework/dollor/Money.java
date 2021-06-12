package guru.springframework.dollor;

public class Money {
    protected int amount;

    public boolean equals(Object object){
        Money money = (Money) object;
        // it means Dollar == Franc
        // return amount == money.amount;
        return amount == money.amount && this.getClass().equals(object.getClass());
    }
}
