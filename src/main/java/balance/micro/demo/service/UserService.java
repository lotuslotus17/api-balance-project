package balance.micro.demo.service;

import balance.micro.demo.entities.Balance;
import balance.micro.demo.entities.Transfer;
import balance.micro.demo.entities.User;
import balance.micro.demo.exceptions.NotEnoughMoneyException;
import balance.micro.demo.exceptions.UserNotFoundException;
import balance.micro.demo.models.BalanceManipulation;
import balance.micro.demo.repositories.BalanceRepository;
import balance.micro.demo.repositories.TransferRepository;
import balance.micro.demo.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Transactional
@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private TransferRepository transferRepository;

    public void createUser(User user) {
        repository.save(user);
        if(Objects.isNull(user.getBalance())) {
            Balance balance = new Balance(0, user);
            Balance saved = balanceRepository.save(balance);
            user.setBalance(saved);
        }
    }

    public User findUserById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
    public void transferMoneyBetweenUsers(Long userFromId, Long userToId, int amount){
        User userFrom = repository.getReferenceById(userFromId);
        User userTo = repository.getReferenceById(userToId);
        if(userFrom.checkBalance(amount)){
            Balance userFromBalance = userFrom.getBalance();
            Balance userToBalance = userTo.getBalance();
            userFromBalance.setBalance(userFromBalance.getBalance() - amount);
            userToBalance.setBalance(userToBalance.getBalance() + amount);
            balanceRepository.save(userFromBalance);
            balanceRepository.save(userToBalance);
            Transfer transfer = new Transfer(userFromId, userToId, amount);
            transferRepository.save(transfer);
        }
        else {
            throw new NotEnoughMoneyException();
        }
    }

    public void addOrRemoveBalance(BalanceManipulation balanceManipulation){
        Balance currentBalance = balanceRepository.getReferenceById(balanceManipulation.getId());
        if(balanceManipulation.getAmount() >= 0){
            currentBalance.setBalance(currentBalance.getBalance() + balanceManipulation.getAmount());
            balanceRepository.save(currentBalance);
        } else {
            if(currentBalance.getBalance() >= balanceManipulation.getAmount()){
                currentBalance.setBalance(currentBalance.getBalance() + balanceManipulation.getAmount());
                balanceRepository.save(currentBalance);
            }
            else {
                throw new NotEnoughMoneyException();
            }
        }
    }
}
