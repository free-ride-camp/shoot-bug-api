package org.freeride.shootbug.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.util.Strings;
import org.freeride.shootbug.dao.db.PostMapper;
import org.freeride.shootbug.dao.db.ReplyMapper;
import org.freeride.shootbug.dao.db.UserMapper;
import org.freeride.shootbug.dto.request.ReplyPostRequest;
import org.freeride.shootbug.dto.response.PostReplyResponse;
import org.freeride.shootbug.po.db.Post;
import org.freeride.shootbug.po.db.type.PostStatusEnum;
import org.freeride.shootbug.service.ForumService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private UserMapper userMapper;

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

    @Override
    public void replyPost(ReplyPostRequest replyPostRequest) {
        Integer postId = replyPostRequest.getPostId();
        String content = replyPostRequest.getContent(),
                email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer userId = userMapper.findUserIdByEmail(email);
        replyMapper.insertReply(userId, postId, content, LocalDateTime.now());
    }
}
