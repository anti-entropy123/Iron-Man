package com.mbry.IronMan.Service;

import com.mbry.IronMan.Dao.CommentDao;
import com.mbry.IronMan.Dao.LogDao;
import com.mbry.IronMan.Dao.UserDao;
import com.mbry.IronMan.RequestBody.CommentRequestBody.AddCommentRequest;
import com.mbry.IronMan.RequestBody.CommentRequestBody.DeleteCommentRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.CommentResponseBody.GetCommentResponse;
import com.mbry.IronMan.ResponseBody.CommentResponseBody.GetRepliesResponse;
import com.mbry.IronMan.Utils.DateUtil;
import com.mbry.IronMan.Utils.WxMessageUtil;
import com.mbry.IronMan.BusinessObject.Log;
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

    @Autowired
    private WxMessageUtil wxMessageUtil;

    @Autowired
    private LogDao logDao;

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
        int type = -1;
        if(replyToId != null && replyToId.length() > 0){
            // 这是一个Reply
            type = 6;
            Comment replyToComment = commentDao.queryCommentByCommentId(replyToId);
            String belongToId;
            if(replyToComment instanceof Reply){
                // 这是回复某条回复的
                belongToId = ((Reply)replyToComment).getBelongToId();
            }else{
                // 这是回复主楼评论的
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
            // 这是一个主楼评论(评论的是帖子)
            type = 7;
            comment = new Comment(
                                null,
                                addCommentRequest.getCardId(),
                                addCommentRequest.getUserId(),
                                addCommentRequest.getContent(),
                                date);
        }
        commentDao.createComment(comment);
        // todo 微信消息提醒
        wxMessageUtil.sendMessage();
        String targetUserId = commentDao.queryCommentByCommentId(replyToId).getUserId();
        logDao.addLog(new Log(-1, type, addCommentRequest.getCardId(), "", addCommentRequest.getUserId(), targetUserId, false));
        return new DefaultResponse(1, "");
    }

    public DefaultResponse deleteComment(DeleteCommentRequest deleteCommentRequest){
        commentDao.deleteComment(deleteCommentRequest.getCommentId());
        return new DefaultResponse(1, "");
    }

}