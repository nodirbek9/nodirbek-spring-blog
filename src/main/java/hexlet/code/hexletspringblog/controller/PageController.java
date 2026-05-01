package hexlet.code.hexletspringblog.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/about")
public class PageController {

    @GetMapping("/{id}")
    public String aboutMe() {
        return "Hello World!";
    }
}
