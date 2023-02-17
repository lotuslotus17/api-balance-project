package balance.micro.demo.repositories;

import balance.micro.demo.entities.Balance;
import balance.micro.demo.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
