package balance.micro.demo.service;

import balance.micro.demo.entities.Transfer;
import balance.micro.demo.repositories.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class TransferService {
    @Autowired
    private TransferRepository transferRepository;

    public List<Transfer> findAllTransfers(){
        return transferRepository.findAll();
    }
}
