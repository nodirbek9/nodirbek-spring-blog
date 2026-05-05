package hexlet.code.hexletspringblog.controller;

import hexlet.code.hexletspringblog.model.Post;
import hexlet.code.hexletspringblog.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = new ArrayList<>();
    @GetMapping
    public ResponseEntity<List<User>> getAll(@RequestParam(defaultValue = "10") Integer limit) {
        var res = users.stream().limit(limit).toList();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(users.size()))
                .body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable Long id) {
        var user =  users.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(user);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        users.add(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  update(@PathVariable Long id, @RequestBody User user) {
        var maybeUser = users.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        if (maybeUser.isPresent()) {
            var post  = maybeUser.get();
            post.setName(user.getName());
            post.setEmail(user.getEmail());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        users.removeIf(p -> p.getId().equals(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
