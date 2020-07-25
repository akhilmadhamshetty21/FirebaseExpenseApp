package com.example.inclass11;

import java.util.Date;
import java.util.HashMap;

public class Expense {
    String title;
    String cost;
    String category;
    String date;
    HashMap<String,Object> hmap;
    String documentId;

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Expense() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "title='" + title + '\'' +
                ", cost=" + cost +
                ", category='" + category + '\'' +
                ", date=" + date +
                '}';
    }

    public Expense(String title, String cost, String category, String date) {
        this.title = title;
        this.cost = cost;
        this.category = category;
        this.date = date;
    }

    HashMap<String,Object> toHashMap(){
        hmap=new HashMap<>();
        hmap.put("title",title);
        hmap.put("cost",cost);
        hmap.put("category",category);
        hmap.put("date",date);
        return hmap;
    }
}
