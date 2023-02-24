package balance.micro.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class SimpleController {

    @GetMapping("/")
    public String frontPage(){
        return "main";
    }
}
