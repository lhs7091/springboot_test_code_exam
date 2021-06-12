package guru.springframework.dollor;

public class Dollar {

    int amount;

    public Dollar(int amount){
        this.amount = amount;
    }

    int times(int multiplier){
        return amount * multiplier;
    }
}
