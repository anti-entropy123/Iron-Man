package com.mbry.IronMan.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.mbry.IronMan.RequestBody.CommentRequestBody.AddCommentRequest;
import com.mbry.IronMan.RequestBody.CommentRequestBody.DeleteCommentRequest;
import com.mbry.IronMan.ResponseBody.DefaultResponse;
import com.mbry.IronMan.ResponseBody.CommentResponseBody.GetCommentResponse;
import com.mbry.IronMan.ResponseBody.CommentResponseBody.GetRepliesResponse;
import com.mbry.IronMan.Service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    /**
     * 9.11 测试通过
     * @param cardId
     * @param pageNum
     * @return
     */
    @GetMapping(value="/getComments/")
    @PreAuthorize("hasRole('common')")
    public GetCommentResponse getComments(
            @RequestParam String cardId,
            @RequestParam int pageNum) {
        return commentService.getComments(cardId, pageNum);
    }

    @GetMapping(value="/getReplies/")
    @PreAuthorize("hasRole('common')")
    public GetRepliesResponse getReplies(@RequestParam String commentId) {
        return commentService.getReplies(commentId);
    }
    
    /**
     * 9.11 测试通过
     * @param addCommentRequest
     * @return
     */
    @PostMapping(value="/addComment/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse addComment(@RequestBody AddCommentRequest addCommentRequest) {
        return commentService.addComment(addCommentRequest);
    }
    
    @PostMapping(value="/deleteComment/")
    @PreAuthorize("hasRole('common')")
    public DefaultResponse deleteComment(@RequestBody DeleteCommentRequest deleteCommentRequest) {
        return commentService.deleteComment(deleteCommentRequest);
    }
}