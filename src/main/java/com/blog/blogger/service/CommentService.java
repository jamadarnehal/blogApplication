package com.blog.blogger.service;

import com.blog.blogger.payload.CommentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId, CommentDto commentDto );

    void deleteComment(long commentId);

    List<CommentDto> getCommentsByPostId(long postId);

    List<CommentDto> getAllComments();
}
