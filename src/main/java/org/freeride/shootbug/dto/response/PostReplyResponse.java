package org.freeride.shootbug.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/11/7 17:34
 */

@Data
public class PostReplyResponse {
    private String id;
    private String content;
    private String replierName;
    private String replierAvatar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime replyTime;
}
