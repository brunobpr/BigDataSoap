/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoribeiro.models;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author brunoribeiro
 */
public class Post {
    private String author;
    private String text;
    private String postedDate;
    
    //Author is the name of the author who published the post
    //Text is the message which is being posted
    public Post(String author, String text){
        this.author = author;
        this.text = text;
         //Current date in format Mon, 20 May 2020.
        this.postedDate = new SimpleDateFormat("E, dd MMM yyyy").format(new Date());
    } 

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getPostedDate() {
        return postedDate;
    }
}
