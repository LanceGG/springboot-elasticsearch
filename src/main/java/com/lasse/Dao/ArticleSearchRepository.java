package com.lasse.Dao;

import com.lasse.entity.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleSearchRepository extends ElasticsearchRepository<Article, String> {
}
