package balance.micro.demo.dto;


public class TransferDto {
    //#TODO add time field
    private Long userFromId;
    private Long userToId;
    private int amount;

    private int userFromBalance;

    private int userToBalance;

    public TransferDto(){};

    public TransferDto(Long userFromId, Long userToId, int amount){
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.amount = amount;
    }

    public int getUserFromBalance() {
        return userFromBalance;
    }

    public void setUserFromBalance(int userFromBalance) {
        this.userFromBalance = userFromBalance;
    }

    public int getUserToBalance() {
        return userToBalance;
    }

    public void setUserToBalance(int userToBalance) {
        this.userToBalance = userToBalance;
    }

    public Long getUserFromId() {
        return userFromId;
    }

    public void setUserFromId(Long userFromId) {
        this.userFromId = userFromId;
    }

    public Long getUserToId() {
        return userToId;
    }

    public void setUserToId(Long userToId) {
        this.userToId = userToId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
