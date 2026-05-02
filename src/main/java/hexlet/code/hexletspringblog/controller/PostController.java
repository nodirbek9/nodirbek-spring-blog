package hexlet.code.hexletspringblog.controller;

import hexlet.code.hexletspringblog.model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    private List<Post> posts = new ArrayList<>();

    @GetMapping
    public List<Post> getAll(@RequestParam(defaultValue = "10") Integer limit) {
        return posts.stream().limit(limit).toList();
    }

    @GetMapping("/{id}")
    public Optional<Post> getOne(@PathVariable Long id) {
        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @PostMapping
    public Post create(@RequestBody Post post) {
        posts.add(post);
        return post;
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable String id, @RequestBody Post data) {
        var maybePost = posts.stream()
                .filter(p -> p.getContent().equals(id))
                .findFirst();
        if (maybePost.isPresent()) {
            var post  = maybePost.get();
            post.setAuthor(data.getAuthor());
            post.setContent(data.getContent());
            post.setTitle(data.getTitle());
            post.setCreatedAt(data.getCreatedAt());
        }
        return data;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
