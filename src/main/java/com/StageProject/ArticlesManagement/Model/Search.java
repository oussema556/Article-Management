package com.StageProject.ArticlesManagement.Model;

import org.hibernate.type.StringType;

public class Search {

    private String keyword;
    private int category;

    public Search(String keyword, int category) {
        this.keyword = keyword;
        this.category = category;
    }

    public Search() {

    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}
