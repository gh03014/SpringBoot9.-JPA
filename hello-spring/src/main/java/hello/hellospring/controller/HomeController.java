package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //서버를 실행할 때 첫 화면
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
