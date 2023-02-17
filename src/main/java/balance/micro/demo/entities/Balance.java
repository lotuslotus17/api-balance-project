package balance.micro.demo.entities;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "balance")
public class Balance {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private Long id;
        private int balance;
        @OneToOne(cascade = CascadeType.ALL)
        @JsonIgnore
        @JoinColumn(name = "user_id", referencedColumnName = "id")
        private User user;

        public Balance(){};
        public Balance(int balance, User user) {
                this.balance = balance;
                this.user = user;
        }

        public Balance(int balance) {
                this.balance = balance;
        }

        public int getBalance() {
                return balance;
        }

        public void setBalance(int balance) {
                this.balance = balance;
        }

        public User getUser() {
                return user;
        }

        public void setUser(User user) {
                this.user = user;
        }
}
