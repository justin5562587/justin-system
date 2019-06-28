package com.justin.system.repository;

import com.justin.system.models.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Integer> {

}
