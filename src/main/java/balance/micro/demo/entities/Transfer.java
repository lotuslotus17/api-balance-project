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
