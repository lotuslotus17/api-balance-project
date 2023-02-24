package balance.micro.demo.controllers;

import balance.micro.demo.dto.TransferDto;
import balance.micro.demo.dto.UserDto;
import balance.micro.demo.entities.Transfer;
import balance.micro.demo.entities.User;
import balance.micro.demo.models.BalanceManipulation;
import balance.micro.demo.service.TransferService;
import balance.micro.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    @PostMapping("/balance")
    public UserDto addBalance(@RequestBody BalanceManipulation balanceManipulation){
        userService.addOrRemoveBalance(balanceManipulation);
        User foundUser = userService.findUserById(balanceManipulation.getId());
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(foundUser, UserDto.class);
        return userDto;
    }

    @PostMapping("/transfer")
    public TransferDto transferMoney(@RequestBody Transfer transfer){
        Transfer transfer1 = userService.transferMoneyBetweenUsers(transfer.getUserFromId(), transfer.getUserToId(), transfer.getAmount());
        ModelMapper modelMapper = new ModelMapper();
        TransferDto transferDto = modelMapper.map(transfer1, TransferDto.class);
        return transferDto;
    }

    @GetMapping("api/transfers")
    public List<TransferDto> getAllTransfers(){
        List<Transfer> listOfAllTransfers = transferService.findAllTransfers();
        ModelMapper modelMapper = new ModelMapper();
        List<TransferDto> transferDto =
                listOfAllTransfers
                        .stream()
                        .map(transfer -> modelMapper.map(transfer, TransferDto.class))
                        .collect(Collectors.toList());
        return transferDto;
    }
}
