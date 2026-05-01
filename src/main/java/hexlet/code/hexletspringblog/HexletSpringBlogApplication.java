package hexlet.code.hexletspringblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class HexletSpringBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HexletSpringBlogApplication.class, args);
    }

    @GetMapping("/about")
    public String about() {
        return "This is a Simple Spring Blog!";
    }
}
