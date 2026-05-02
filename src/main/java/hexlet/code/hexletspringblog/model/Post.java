package hexlet.code.hexletspringblog.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Post {
    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDate createdAt;
}
