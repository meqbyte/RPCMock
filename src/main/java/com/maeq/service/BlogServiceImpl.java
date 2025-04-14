package com.maeq.service;

import com.maeq.entity.Blog;

public class BlogServiceImpl implements BlogService{

    @Override
    public Blog getBlogById(Integer id) {
        return Blog.builder()
                .title("this is title")
                .id(id)
                .userId(999)
                .build();
    }
}
