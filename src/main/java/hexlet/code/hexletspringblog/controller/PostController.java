package hexlet.code.hexletspringblog.controller;

import hexlet.code.hexletspringblog.model.Post;
import hexlet.code.hexletspringblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private List<Post> posts = new ArrayList<>();
    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAll(@RequestParam(defaultValue = "10") Integer limit) {
        var res = posts.stream().limit(limit).toList();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(posts.size()))
                .body(res);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getOne(@PathVariable Long id) {
        var page =  posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return ResponseEntity.of(page);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?>  update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
                .filter(p -> p.getTitle().equals(id))
                .findFirst();
        if (maybePost.isPresent()) {
            var post  = maybePost.get();
            post.setAuthor(data.getAuthor());
            post.setContent(data.getContent());
            post.setTitle(data.getTitle());
            post.setCreatedAt(data.getCreatedAt());
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        posts.removeIf(p -> p.getId().equals(id));
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
