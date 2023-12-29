package com.blog.blogger;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainUtil {
    public static void main(String[] args) {

        Post post1=new Post();
        post1.setId(1);
        post1.setTitle("aaa");
        post1.setContent("aaaa");

        Post post2=new Post();
        post2.setId(1);
        post2.setTitle("bbb");
        post2.setContent("bbbb");

        Post post3=new Post();
        post3.setId(1);
        post3.setTitle("ccc");
        post3.setContent("cccc");

        List<Post> posts= Arrays.asList(post1,post2,post3);
        List<PostDto> dtos = posts.stream().map(MainUtil::mapTODto).collect(Collectors.toList());
        System.out.println(dtos);


    }


    static  PostDto mapTODto(Post post){
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());

        return dto;



    }

}
