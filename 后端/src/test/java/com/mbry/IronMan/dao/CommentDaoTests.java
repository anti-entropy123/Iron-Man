package com.mbry.IronMan.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.mbry.IronMan.BusinessObject.Comment.*;
import com.mbry.IronMan.Dao.CommentDao;

@SpringBootTest
public class CommentDaoTests {

    @Autowired
    CommentDao commentDao;
    
    @Test
    void queryCommentsByCardId() {
        Comment[] comments = commentDao.queryCommentsByCardId("1", 1);
        for (Comment comment: comments) {
            System.out.println(comment.getCommentId());
        }
    }

    @Test
    void queryRepliesByCommentId() {
        Reply[] replies = commentDao.queryRepliesByCommentId("2");
        for (Reply reply: replies) {
            System.out.println(reply.getCommentId());
        }
    }
    
    @Test
    void createComment() {
        Comment reply = new Comment(
            null,
	        "String cardId",
	        "String userId",
	        "String content",
	        "String date"
        );
        commentDao.createComment(reply);
    }

    @Test
    void deleteComment() {
        commentDao.deleteComment("2");
    }
    
    @Test
    void deleteReply() {
        commentDao.deleteReply("2");
    }

    @Test
    void queryReplyNumberByCommentId() {
        System.out.println(commentDao.queryReplyNumberByCommentId("1"));
    }

    @Test
    void queryCommentByCommentId() {
        Comment comment = commentDao.queryCommentByCommentId("3");
        System.out.println(comment.getContent());
    }

}
