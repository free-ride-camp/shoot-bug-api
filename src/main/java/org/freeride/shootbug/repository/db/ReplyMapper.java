package org.freeride.shootbug.repository.db;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.freeride.shootbug.dto.response.PostReplyResponse;

import java.util.List;

/**
 * @author ZhaoHe(hezhao @ dianhun.cn)
 * @date 2022/11/7 17:28
 */

@Mapper
public interface ReplyMapper {

    @Select("select r.id, r.content, " +
            "u.nickname as replierName, " +
            "u.avatar_url as replierAvatar, " +
            "r.reply_time as replyTime from reply r " +
            "join user u on r.post_id = #{postId} and u.id = r.replier_id")
    List<PostReplyResponse> findPostReplies(Integer postId);
}
