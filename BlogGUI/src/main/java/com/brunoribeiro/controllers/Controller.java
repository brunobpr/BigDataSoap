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
import com.brunoribeiro.views.HomePage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author brunoribeiro
 */
public class Controller implements ActionListener {
    private BlogService blogService;
    private HomePage homePage;
    
    public Controller(){
        blogService = new BlogService();
        homePage = new HomePage(this);
        String response = blogService.getResponse("Bruno", "Bruno, This is a text, Greg.");
        System.out.println(response);
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       
    } 
}
