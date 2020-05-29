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
public class Controller implements ActionListener, KeyListener {

    private BlogService blogService; //The class where the requests are sent to the server
    private HomePage homePage; //The GUI class
    private ArrayList<Post> listOfPosts = new ArrayList<Post>(); //An arraylist to hold all the posts

    public Controller() {
        blogService = new BlogService();
        homePage = new HomePage(this);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (event.getActionCommand()) {
            //When the button SENT is pressed
            case "post":
                //Get the author and post from the text fields
                String author = homePage.getAuthor();
                String post = homePage.getPost();
                //Check if the fields aren't empty
                if (author.isEmpty()) {
                    homePage.setErrorMessage("Please, enter your name!");
                } else if (post.isEmpty()) {
                    homePage.setErrorMessage("Please, write your post!");
                } //Limit the number of characters for each post
                else if (post.length() > 240) {
                    homePage.setErrorMessage("Sorry, text is too long!");
                } else {
                    //If everything is fine, clear the label for errors
                    homePage.setErrorMessage("");
                    //Send a request to the BlogWebService passing the author and post as parameters
                    String response = blogService.getResponse(author, post);
                    //Check if the connection was refused
                    if (response.contains("Connection refused (Connection refused).")) {
                        homePage.setErrorMessage("SoapWebService is not running!");
                    } //Check if other errors occured
                    else if (response.contains(":(") || response.contains("?WSD")) {
                        homePage.setErrorMessage(response);
                    } else {
                        //If the response is ok add the anonymised post to the list
                        listOfPosts.add(new Post(author, response));
                        //Display a success message and clear the input fields
                        homePage.successMessage();
                        //Refresh the page with the newest list of Posts.
                        homePage.refreshPage(listOfPosts);
                    }
                }
        }
    }

    //Those methods will count the number of characteres the user's typed
    //and update the label with the chars counter
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
