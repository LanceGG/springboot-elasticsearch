package com.lasse.Dao;

import com.lasse.entity.Article;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ArticleSearchRepository extends ElasticsearchRepository<Article, String> {

     public List<Article> findArticlesById(String str);

     public List<Article> findArticlesByTranslateStartsWithOrTitleStartsWithOrPinyinStartsWith(String translate, String title, String pinyin);

     public List<Article> findArticlesByTranslateStartingWith(String str);

     public List<Article> findArticlesByTranslateIsStartingWith(String str);

     @Query("{\"match_phrase_prefix\" : {\"translate\" : \"?1\"}}")
     public List<Article> findArticles(String str, String str1);
}
