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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;


/**
 *
 * @author brunoribeiro
 */
public class Controller implements ActionListener, KeyListener{

    private BlogService blogService;
    private HomePage homePage;
    private ArrayList<Post> listOfPosts = new ArrayList<Post>();
    
    public Controller() {
        blogService = new BlogService();
        homePage = new HomePage(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            // Menu Selection Buttons-----------------------------------------
            // mainView.cardLayout.show is the 'command' to display the corresponding panel
            case "post":
                String author = homePage.getAuthor();
                String post = homePage.getPost();
                String response = blogService.getResponse(author, post);
               
                listOfPosts.add(new Post(author, response));
                homePage.refreshPage(listOfPosts);
                
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    @Override
    public void keyPressed(KeyEvent e) {
      int counter = homePage.getCharCounter();
        homePage.setCharCounterText(counter);
    }

    @Override
    public void keyReleased(KeyEvent e) {
         int counter = homePage.getCharCounter();
        homePage.setCharCounterText(counter);
    }
}
