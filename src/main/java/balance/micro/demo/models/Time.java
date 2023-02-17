package balance.micro.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public class Time {
    @JsonIgnore
    public static Timestamp timestamp = new Timestamp(System.currentTimeMillis());
}
