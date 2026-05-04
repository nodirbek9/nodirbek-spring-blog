package hexlet.code.hexletspringblog.service;

import hexlet.code.hexletspringblog.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private List<Post> posts = new ArrayList<>();


    }
}
