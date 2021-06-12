package guru.springframework.dollor;

public class Money {
    protected int amount;

    public boolean equals(Object object){
        Money money = (Money) object;
        return amount == money.amount;
    }
}
