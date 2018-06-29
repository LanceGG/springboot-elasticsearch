/**
 * Company
 * Copyright (C) 2014-2017 All Rights Reserved.
 */
package com.lasse.web;

import com.lasse.Dao.ArticleSearchRepository;
import com.lasse.entity.Article;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Iterator;

/**
 * @author cwenao
 * @version $Id ESController.java, v 0.1 2017-02-06 10:44 cwenao Exp $$
 */
@RestController
@RequestMapping(value = "/es")
public class ESController {

    @Autowired
    private ArticleSearchRepository articleSearchRepository;

    @RequestMapping("/add")
    public void testSaveArticleIndex() {

        Article article = new Article();
        article.setId("2");
        article.setTitle("springboot integreate elasticsearch minecraft");
        article.setAbstracts("springboot integreate elasticsearch is very easy");
        article.setContent("elasticsearch based on lucene,"
                + "spring-data-elastichsearch based on elaticsearch"
                + ",this tutorial tell you how to integrete springboot with spring-data-elasticsearch");
        article.setPostTime(new Date());
        article.setClickCount(1L);

        articleSearchRepository.save(article);
    }

    @RequestMapping("/query")
    public void testSearch(
            @RequestParam(value = "url", required = false) String url
    ) {
        String queryString = (url!=null?url:"minecraft");//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<Article> searchResult = articleSearchRepository.search(builder);
        Iterator<Article> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}


