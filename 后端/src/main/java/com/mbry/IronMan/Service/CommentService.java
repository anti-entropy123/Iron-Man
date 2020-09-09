package com.mbry.IronMan.Service;

import com.mbry.IronMan.Dao.CommentDao;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.RequestBody.CommentRequestBody.AddCommentRequest;
import com.mbry.IronMan.RequestBody.CommentRequestBody.DeleteCommentRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.CommentResponseBody.GetCommentResponse;
import com.mbry.IronMan.ResponseBody.CommentResponseBody.GetRepliesResponse;
import com.mbry.IronMan.Utils.DateUtil;


import com.mbry.IronMan.BusinessObject.User;
import com.mbry.IronMan.BusinessObject.Comment.Comment;
import com.mbry.IronMan.BusinessObject.Comment.Reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    @Autowired 
    private UserDao userDao;

    @Autowired
    private DateUtil dateUtil;

    public GetCommentResponse getComments(String cardId, int pageNum){
        Comment[] comments = commentDao.queryCommentsByCardId(cardId, pageNum);
        User[] users = new User[comments.length];
        int[] replyNums = new int[comments.length];
        
        for(int i=0; i < comments.length; ++i){
            users[i] = userDao.queryUserByOpenId(comments[i].getUserId());
            replyNums[i] = commentDao.queryReplyNumberByCommentId(comments[i].getCommentId());
        }
        GetCommentResponse response = new GetCommentResponse();
        GetCommentResponse.Data data = response.new Data(comments, users, replyNums);
        response.setData(data);
        response.setResult(1);
        response.setMessage("");
        return response;
    }

    public GetRepliesResponse getReplies(String commentId){
        Reply[] replies = commentDao.queryRepliesByCommentId(commentId);
        User[] users = new User[replies.length];
        User[] replyToUsers = new User[replies.length];
        GetRepliesResponse getRepliesResponse = new GetRepliesResponse();
        GetRepliesResponse.Data data = getRepliesResponse.new Data(
            replies, 
            users, 
            replyToUsers);
        getRepliesResponse.setData(data);
        getRepliesResponse.setMessage("");
        getRepliesResponse.setResult(1);
        return getRepliesResponse;
    }

    public DefaultResponse addComment(AddCommentRequest addCommentRequest){
        String replyToId = addCommentRequest.getReplyToId();
        Comment comment;
		String date = dateUtil.getDate();
        if(replyToId != null && replyToId.length() > 0){
            // 这是一个Reply
            Comment replyToComment = commentDao.queryCommentByCommentId(replyToId);
            String belongToId;
            if(replyToComment instanceof Reply){
                belongToId = ((Reply)replyToComment).getBelongToId();
            }else{
                belongToId = replyToComment.getCommentId();
            }
            comment = new Reply(
                null, 
                addCommentRequest.getCardId(), 
                addCommentRequest.getUserId(), 
                addCommentRequest.getContent(), 
                date,
                replyToId,
                belongToId
                );
        }else{
            // 这是一个主楼评论
            comment = new Comment(
                                null,
                                addCommentRequest.getCardId(),
                                addCommentRequest.getUserId(),
                                addCommentRequest.getContent(),
                                date);            
        }
        commentDao.createComment(comment);
        return new DefaultResponse(1, "");
    }

    public DefaultResponse deleteComment(DeleteCommentRequest deleteCommentRequest){
        commentDao.deleteComment(deleteCommentRequest.getCommentId());
        return new DefaultResponse(1, "");
    }

}