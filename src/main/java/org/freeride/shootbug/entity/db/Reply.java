package org.freeride.shootbug.entity.db;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reply {
    private Integer id;
    private Post post;
    private String content;
    private User replier;
    private LocalDateTime replyTime;
}
