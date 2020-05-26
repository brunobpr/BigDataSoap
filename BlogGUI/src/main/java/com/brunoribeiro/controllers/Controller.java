/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoribeiro.controllers;
import com.brunoribeiro.models.BlogWebService_Service;
import com.brunoribeiro.models.BlogWebService;
import com.brunoribeiro.models.*;
import javax.xml.ws.Response;
/**
 *
 * @author brunoribeiro
 */
public class Controller {
    private BlogService blogService;
    
    public Controller(){
        blogService = new BlogService();
        System.out.println(blogService.getResponse("Bruno", "Bruno, This is a text, Greg."));
    }
    
    
    public void callAsyncOperation(){
        
        

        
    }
    
}
