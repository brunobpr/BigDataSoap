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
                System.out.println(response);
        }
    }
}
