package com.example.a15828.ipad;

import java.util.List;

public class News {
    private String title;
    private String content;
    private List<Integer> images;

    public News(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public News() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "News{" +
                "images=" + images + '\'' +
                "title='" + title + '\'' +
                ", content='" + content +
                '}';
    }

}
