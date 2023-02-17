package balance.micro.demo.dto;

import balance.micro.demo.entities.Balance;

public class UserDto {
   private boolean found = true;
   private Long id;
   private String name;
   private Balance balance;

    public UserDto(){};

    public UserDto(boolean status, Long id, String name, Balance balance) {
        this.found = status;
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }
}
