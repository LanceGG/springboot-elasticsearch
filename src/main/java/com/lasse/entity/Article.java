package com.lasse.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName="projectname",type="article",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class Article implements Serializable {
    @Id
    private String id;
    /**标题*/
    @Field(type = FieldType.Text, fielddata = true)
    private String title;
    /**摘要*/
    @Field(type = FieldType.Text, fielddata = true)
    private String pinyin;
    /**内容*/
    @Field(type = FieldType.Text, fielddata = true)
    private String translate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", pinyin='" + pinyin + '\'' +
                ", translate='" + translate + '\'' +
                '}';
    }
}

