/**
 * Company
 * Copyright (C) 2014-2017 All Rights Reserved.
 */
package com.lasse.web;

import com.lasse.Dao.ArticleSearchRepository;
import com.lasse.entity.Article;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author cwenao
 * @version $Id ESController.java, v 0.1 2017-02-06 10:44 cwenao Exp $$
 */
@RestController
@RequestMapping(value = "/es")
public class ESController {

    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @RequestMapping("/add")
    public void testSaveArticleIndex() {

        List<Article> articles = new ArrayList<Article>();
        Article article = new Article();
        article.setId("2");
        article.setTitle("极限挑战第一季");
        article.setPinyin("jxtzdyj");
        article.setTranslate("1212313");
        articles.add(article);
        Article article1 = new Article();
        article1.setId("3");
        article1.setTitle("极限挑战第二季");
        article1.setPinyin("jxtzdej");
        article1.setTranslate("1212313");
        articles.add(article1);
        Article article2 = new Article();
        article2.setId("4");
        article2.setTitle("极限挑战第三季");
        article2.setPinyin("jxtzdsj");
        article2.setTranslate("1212313");
        articles.add(article2);

        for (Article article3: articles) {
            articleSearchRepository.save(article3);
        }

    }

    @RequestMapping(value = "/delete")
    public void delete() {
        articleSearchRepository.deleteAll();
    }

    @RequestMapping("/query1")
    public void testSearch1(
            @RequestParam(value = "url", required = false) String url
    ) {
        String queryString = (url!=null?url:"j");//搜索关键字
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
//        Iterable<Article> searchResult = articleSearchRepository.search(builder);
//        Iterator<Article> iterator = searchResult.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        List<Article> articles = articleSearchRepository.findArticlesByTranslateStartsWithOrTitleStartsWithOrPinyinStartsWith(queryString, queryString, queryString);
        articles.forEach(article -> {
            System.out.println(article.toString());
        });
    }

    @RequestMapping("/query2")
    public void testSearch2(
            @RequestParam(value = "url", required = false) String url
    ) {
        String queryString = (url!=null?url:"j");//搜索关键字
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
//        Iterable<Article> searchResult = articleSearchRepository.search(builder);
//        Iterator<Article> iterator = searchResult.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        List<Article> articles = articleSearchRepository.findArticlesByTranslateStartingWith(queryString);
        articles.stream().sorted(new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o1.getId().compareToIgnoreCase(o2.getId());
            }
        }).collect(Collectors.toList());
        articles.forEach(article -> {
            System.out.println(article.toString());
        });
    }

    @RequestMapping("/query3")
    public void testSearch3(
            @RequestParam(value = "url", required = false) String url
    ) {
        String queryString = (url!=null?url:"j");//搜索关键字
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
//        Iterable<Article> searchResult = articleSearchRepository.search(builder);
//        Iterator<Article> iterator = searchResult.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
        List<Article> articles = articleSearchRepository.findArticlesByTranslateIsStartingWith(queryString);
        articles.forEach(article -> {
            System.out.println(article.toString());
        });
    }
}


