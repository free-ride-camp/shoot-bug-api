package org.freeride.shootbug.dto.request;

import lombok.Data;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/11/9 17:17
 */

@Data
public class ReplyPostRequest {
    private String content;
    private Integer postId;
}
