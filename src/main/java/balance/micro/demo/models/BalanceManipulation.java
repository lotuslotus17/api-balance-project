package balance.micro.demo.models;

public class BalanceManipulation {

    private Long id;
    private int amount;

    public BalanceManipulation(){};

    public BalanceManipulation(Long id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
