package in.yaxley.gtodo.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hello")
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
