package org.freeride.shootbug.po.db;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reply {
    private String id;
    private Post post;
    private String content;
    private User replier;
    private LocalDateTime replyTime;
    private Boolean isDeleted;
}
