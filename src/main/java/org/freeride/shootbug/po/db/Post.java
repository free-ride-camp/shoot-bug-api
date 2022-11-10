package org.freeride.shootbug.po.db;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.freeride.shootbug.po.db.type.PostStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post {
    private String id;
    private User creator;
    private String title;
    private String description;
    private List<Tag> tags;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastEditTime;
    private PostStatusEnum status;
}
