package org.freeride.shootbug.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.util.Strings;
import org.freeride.shootbug.dto.response.PostReplyResponse;
import org.freeride.shootbug.entity.db.Post;
import org.freeride.shootbug.entity.db.type.PostStatusEnum;
import org.freeride.shootbug.repository.db.PostMapper;
import org.freeride.shootbug.repository.db.ReplyMapper;
import org.freeride.shootbug.service.ForumService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/11/3 9:44
 */

@Service
public class ForumServiceImpl implements ForumService {

    @Resource
    private PostMapper postMapper;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private ReplyMapper replyMapper;

    @Override
    public PageInfo<Post> getPosts(Integer pageNum, Integer pageSize, List<String> tags, String searchText, PostStatusEnum postStatus, Integer userId) throws JsonProcessingException {
        Page<Post> page = PageHelper.startPage(pageNum, pageSize);
        String tagsJsonArray = CollectionUtils.isEmpty(tags) ? Strings.EMPTY : objectMapper.writeValueAsString(tags);
        return page.doSelectPageInfo(() -> postMapper.findPosts(tagsJsonArray, searchText, postStatus, userId));
    }

    @Override
    public PageInfo<PostReplyResponse> getPostReplies(Integer pageNum, Integer pageSize, Integer postId) {
        Page<PostReplyResponse> page = PageHelper.startPage(pageNum, pageSize);
        return page.doSelectPageInfo(() -> replyMapper.findPostReplies(postId));
    }
}
