package org.freeride.shootbug.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import org.freeride.shootbug.dto.response.PostReplyResponse;
import org.freeride.shootbug.entity.db.Post;

import java.util.List;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/11/2 15:59
 */

public interface ForumService {
    PageInfo<Post> getPosts(Integer pageNum, Integer pageSize, List<String> tags, String searchText) throws JsonProcessingException;

    PageInfo<PostReplyResponse> getPostReplies(Integer pageNum, Integer pageSize, Integer postId);
}
