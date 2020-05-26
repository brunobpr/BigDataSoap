/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoribeiro.DataSource;

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
    
    public Post(String author, String text){
        this.author = author;
        this.text = text;
        this.postedDate = new SimpleDateFormat("E, dd MMM yyyy").format(new Date());
    } 

    public String anonymiseText() {
        return Anonymiser.getInstance().anonymiseText(this.text);
    }

    public String getAuthor(){
    return this.author;
    }
    
    public String getText(){
    return this.text;
    }
    
    public String getPostedDate(){
    return this.postedDate;
    }
    
}
