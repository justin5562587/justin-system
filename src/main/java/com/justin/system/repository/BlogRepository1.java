package com.justin.system.repository;

import com.justin.system.models.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository1 extends PagingAndSortingRepository<Blog, Long> {
}
