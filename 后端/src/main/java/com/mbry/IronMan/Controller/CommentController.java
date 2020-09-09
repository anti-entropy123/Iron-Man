package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.RequestBody.CommentRequestBody.AddCommentRequest;
import com.mbry.IronMan.RequestBody.CommentRequestBody.DeleteCommentRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.CommentResponseBody.GetCommentResponse;
import com.mbry.IronMan.ResponseBody.CommentResponseBody.GetRepliesResponse;
import com.mbry.IronMan.Service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value="/getComments/")
    public GetCommentResponse getComments(
            @RequestParam String cardId,
            @RequestParam int pageNum) {
        return commentService.getComments(cardId, pageNum);
    }

    @GetMapping(value="/getReplies/")
    public GetRepliesResponse getReplies(@RequestParam String commentId) {
        return commentService.getReplies(commentId);
    }
    
    @PostMapping(value="/addComment/")
    public DefaultResponse addComment(@RequestBody AddCommentRequest addCommentRequest) {
        return commentService.addComment(addCommentRequest);
    }
    
    @PostMapping(value="/deleteComment/")
    public DefaultResponse deleteComment(@RequestBody DeleteCommentRequest deleteCommentRequest) {
        return commentService.deleteComment(deleteCommentRequest);
    }
}