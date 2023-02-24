package balance.micro.demo.controllers;

import balance.micro.demo.dto.TransferDto;
import balance.micro.demo.dto.UserDto;
import balance.micro.demo.entities.User;
import balance.micro.demo.exceptions.UserNotFoundException;
import balance.micro.demo.models.BalanceManipulation;
import balance.micro.demo.entities.Transfer;
import balance.micro.demo.repositories.BalanceRepository;
import balance.micro.demo.repositories.UserRepository;
import balance.micro.demo.service.TransferService;
import balance.micro.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserRepository repository;
    private final BalanceRepository balanceRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private TransferService transferService;

    public UserController(UserRepository repository, BalanceRepository balanceRepository) {
        this.repository = repository;
        this.balanceRepository = balanceRepository;
    }

    @PostMapping("/user")
    public String addUser(@ModelAttribute("user") User newUser, Model model){
        User newUserCreated = userService.createUser(newUser);
        ModelMapper modelMapper = new ModelMapper();
        UserDto newUserDto = modelMapper.map(newUserCreated, UserDto.class);
        model.addAttribute("userDto", newUserDto);
        return "usercreated";
    }

    @GetMapping("/user/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        User foundUser = userService.findUserById(id);
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(foundUser, UserDto.class);
        return userDto;
    }

    //#TODO move to another controller
    @GetMapping("/transfers")
    public String findAllTransfers(Model model) {
        List<Transfer> listOfAllTransfers = transferService.findAllTransfers();
        ModelMapper modelMapper = new ModelMapper();
        List<TransferDto> transferDtos =
                listOfAllTransfers
                        .stream()
                        .map(transfer -> modelMapper.map(transfer, TransferDto.class))
                        .collect(Collectors.toList());
        model.addAttribute("transfers", transferDtos);
        return "transfers";
    }


    @GetMapping("/users")
    public String findAllUsers(Model model) {
        List<User> foundUsers = repository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        List<UserDto> userDtos = foundUsers.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        model.addAttribute("users", userDtos);
        return "users1";
    }
}
