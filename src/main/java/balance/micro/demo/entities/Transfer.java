package balance.micro.demo.entities;

import balance.micro.demo.models.Time;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "transfers")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @JsonIgnore
    private Long id;
    @Column(name = "user_to_id")
    private Long userFromId;
    @Column(name = "user_from_id")
    private Long userToId;

    @Column(name = "user_from_balance")
    private int userFromBalance;

    @Column(name = "user_to_balance")
    private int userToBalance;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "user_to_id", referencedColumnName = "id"),
            @JoinColumn(name = "user_from_id", referencedColumnName = "id")
    })
    @JsonIgnore
    private Collection<User> user;
    private int amount;

    @JsonIgnore
    private Long time;

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

    public Transfer(Long userFromId, Long userToId, int amount, int userFromBalance, int userToBalance) {
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.amount = amount;
        this.userFromBalance = userFromBalance;
        this.userToBalance = userToBalance;
        this.time = Time.timestamp.getTime();
    }

    public Transfer(){};

    public Transfer(Long userFromId, Long userToId, int amount) {
        this.userFromId = userFromId;
        this.userToId = userToId;
        this.amount = amount;
        this.time = Time.timestamp.getTime();
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
