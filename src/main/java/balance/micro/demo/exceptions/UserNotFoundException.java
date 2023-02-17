package balance.micro.demo.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long id) {
        super("Could not find user by his Id" + id);
    }
}
