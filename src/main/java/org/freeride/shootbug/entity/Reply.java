package org.freeride.shootbug.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reply {
    private Integer id;
    private Post post;
    private String content;
    private String replier;
    private LocalDateTime replyTime;
}
