package org.freeride.shootbug.entity;

import lombok.Data;
import org.freeride.shootbug.entity.type.PostStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Post {
    private Integer id;
    private User creator;
    private String title;
    private String description;
    private List<Tag> tags;
    private LocalDateTime lastEditTime;
    private PostStatusEnum status;
}
