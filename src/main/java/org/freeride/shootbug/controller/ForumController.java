package org.freeride.shootbug.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import org.freeride.shootbug.dto.response.PostReplyResponse;
import org.freeride.shootbug.entity.db.Post;
import org.freeride.shootbug.entity.db.Reply;
import org.freeride.shootbug.service.ForumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/11/2 15:51
 */

@RestController
@RequestMapping("/forum")
public class ForumController {

    @Resource
    private ForumService forumService;

    @GetMapping("/getPosts")
    public PageInfo<Post> getPosts(@RequestParam(defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "10") Integer pageSize,
                                   @RequestParam(required = false) List<String> tags,
                                   @RequestParam(required = false) String searchText) throws JsonProcessingException {
        return forumService.getPosts(pageNum, pageSize, tags, searchText);
    }

    @GetMapping("/getPostReplies")
    public PageInfo<PostReplyResponse> getPostReplies(@RequestParam(defaultValue = "1") Integer pageNum,
                                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                                      @RequestParam Integer postId) {
        return forumService.getPostReplies(pageNum, pageSize, postId);
    }

}
