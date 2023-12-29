package com.blog.blogger.service.impl;


import com.blog.blogger.entity.Post;
import com.blog.blogger.exception.ResourceNotFoundException;
import com.blog.blogger.payload.PostDto;
import com.blog.blogger.repository.PostRepository;
import com.blog.blogger.service.PostService;
 import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl  implements PostService {
    private PostRepository postRepo ;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepo  = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());

        Post savedPost = postRepo.save(post);

        PostDto dto=new PostDto();
        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());
      //  dto.setMessage("post is created");

        return dto;
    }

    @Override
    public void deletePost(long id) {
         Post post= postRepo.findById(id).orElseThrow(
                  ()->new ResourceNotFoundException("post not found with id:"+id)
          );
         postRepo.deleteById(id);

    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

       Sort sort= (sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()))? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNo,pageSize, sort);

        Page<Post> pagePosts = postRepo.findAll(pageable);
        List<Post> posts=pagePosts.getContent();
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException(("post not found with id:" + postId))
        );

        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(postDto.getDescription());
        Post savedPost = postRepo.save(post);

        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    PostDto mapToDto(Post post ){
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        return dto;

    }

}
