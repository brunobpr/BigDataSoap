/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoribeiro.views;

import com.brunoribeiro.controllers.Controller;
import com.brunoribeiro.models.Post;
import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * This is the whole GUI for the Blog APP
 *
 * @author brunoribeiro
 */
public class HomePage extends JFrame {

    private Controller controller;
    private Font font;
    private JPanel topPanel, formPanel;
    private JPanel mainPanel = new JPanel();
    private JTextField authorTF;
    private JTextArea postTF;
    private JLabel charCounter, jl, statusMessage;
    private ImageIcon icon = new ImageIcon();
    private GridBagLayout grid = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();

    private JScrollPane jcp = new JScrollPane();

    public HomePage(Controller controller) {
        this.controller = controller;
        buildFrame();
    }

    private void buildFrame() {
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("FaceBlog");
        //PANEL WITH A LOGO ON THE TOP OF THE SCREEN
        topPanel = new JPanel();
        topPanel.setBackground(new Color(0, 128, 128));
        JLabel banner = new JLabel("FaceBlog");
        font = new Font("arial", Font.BOLD, 32);
        banner.setForeground(Color.white);
        banner.setFont(font);
        topPanel.add(banner);
        add(topPanel, BorderLayout.NORTH);
        buildForm();
        validate();
        repaint();
    }

    //THIS IS THE FORM ON THE RIGHT HAND SIDE OF THE SCREEN
    public void buildForm() {
        formPanel = new JPanel();
        add(formPanel, BorderLayout.EAST);
        formPanel.setMaximumSize(new Dimension(200, 600));
        formPanel.setBackground(new Color(0, 128, 128));
        formPanel.setLayout(grid);
        //labels and buttons
        JLabel authorLabel = new JLabel("Name");
        JButton button = new JButton("SEND");
        JLabel postLabel = new JLabel("Post");
        charCounter = new JLabel("0/240");
        statusMessage = new JLabel("");
        font = new Font("arial", Font.BOLD, 16);
        authorLabel.setForeground(Color.white);
        postLabel.setForeground(Color.white);
        charCounter.setForeground(Color.white);
        button.setForeground(new Color(0, 128, 128));
        authorLabel.setFont(font);
        postLabel.setFont(font);
        button.setFont(font);
        button.addActionListener(controller);
        button.setActionCommand("post");
        authorTF = new JTextField(16);
        postTF = new JTextArea();
        postTF.setLineWrap(true);
        postTF.setWrapStyleWord(true);
        postTF.setPreferredSize(new Dimension(195, 200));
        postTF.addKeyListener(controller);

        //Positioning the elements using GridBag Constraints (gbc) 
        //The position is determined by the value of x and y axes
        //This is the big icon on top of the form
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(-50, 0, 20, 0);
        formPanel.add(jl = new JLabel(icon = new ImageIcon("./src/main/java/com/brunoribeiro/views/icon.png")), gbc);
        //JLabel : Author
        gbc.gridy = 1;
        gbc.ipady = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        formPanel.add(authorLabel, gbc);
        //TextField for Author
        gbc.gridy = 2;
        formPanel.add(authorTF, gbc);
        //JLabel : Post
        gbc.gridy = 3;
        formPanel.add(postLabel, gbc);
        //TextField for Post
        gbc.gridy = 4;
        formPanel.add(postTF, gbc);
        //The button SENT
        gbc.gridy = 5;gbc.gridx = 0; gbc.gridwidth = 1;
        formPanel.add(button, gbc);
        //JLabel : 240/240 for the characters counter (dynamic)
        gbc.gridx = 1;
        formPanel.add(charCounter, gbc);
        //JLabel for Error or Success messages (dynamic)
        gbc.gridy = 6;gbc.gridx = 0; gbc.gridwidth = 3;
        formPanel.add(statusMessage, gbc);

        //Adding the form to the the frame
        add(formPanel, BorderLayout.EAST);
        validate();
        repaint();
    }

    public void refreshPage(ArrayList<Post> list) {
        //Cleaning the previous "screen"
        remove(mainPanel);
        remove(jcp);
        add(mainPanel);
        mainPanel.removeAll();
        //JScrollPane policies
        jcp = new JScrollPane(mainPanel);
        jcp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jcp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(jcp);

        //The mainPanel is a Grid Layout of the number of posts by 1. 
        //The extra 6 was used to avoid the first element fill the whole panel
        mainPanel.setLayout(new GridLayout(list.size() + 6, 1));
        //For each post of the list a new Panel will be created and added to the MainPanel
        for (Post p : list) {
            JPanel panel = new JPanel();
            panel.setLayout(grid);
            panel.setBackground(Color.white);
            JLabel author = new JLabel(p.getAuthor());
            author.setFont(font);
            JLabel date = new JLabel(p.getPostedDate());
            date.setFont(new Font("arial", Font.PLAIN, 10));

            //Using a non-editable JTextArea to display the posts
            //This way the posts will be displayed in multiple lines
            //The size of the area is determined by the number of rows and columns
            JTextArea text = new JTextArea(p.getText(), (p.getText().length() / 40), 40);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
            text.setEditable(false);
            text.setBackground(Color.white);

            //Positioning the elements using GridBag Constraints (gbc) 
            
            //This is the 'user' icon on top of each post
            gbc.gridy = 0;gbc.gridx = 0;gbc.gridwidth = 1;gbc.insets = new Insets(20, 0, 0, 0);
            panel.add(jl = new JLabel(icon = new ImageIcon("./src/main/java/com/brunoribeiro/views/user.png")), gbc);
            //The name of the author
            gbc.gridy = 1;
            gbc.insets = new Insets(0, 0, 0, 0);
            panel.add(author, gbc);
            //The posted date
            gbc.gridy = 2;
            panel.add(date, gbc);
            //The JTextArea with the content of the post
            gbc.gridy = 3;gbc.insets = new Insets(10, 0, 10, 0);
            panel.add(text, gbc);

            //This is just a line to divide each post
            JPanel line = new JPanel();
            line.setPreferredSize(new Dimension(600, 2));
            line.setBackground(new Color(0, 128, 128));
            gbc.insets = new Insets(0, 0, 0, 0);
            gbc.gridy = 4;
            panel.add(line, gbc);

            //Adding the post to the ScrollPane
            mainPanel.add(panel);
        }
        validate();
        repaint();
    }

    //Returns the text inse of the TextField without new lines
    public String getPost() {
        //New lines are converted to dots, this way users won't messed up the GUI
        return postTF.getText().replace("\n", " • ");
    }

    //Returns the name of the author
    public String getAuthor() {
        return authorTF.getText().toString();
    }

    //Returns the number of chars inside of the TextField postTF
    public int getCharCounter() {
        return postTF.getText().length();
    }

    //Receive the number of charactes and update the label
    public void setCharCounterText(int counter) {
        charCounter.setText(String.valueOf(counter) + "/240");
        if (counter > 240) {
            //If the limit of 240 is met, show the label in red
            charCounter.setForeground(Color.red);
        } else {
            //If less than 240, leave the label white
            charCounter.setForeground(Color.white);
        }
    }

    //Receives an error messege from the controller class
    //Erros include empty inputs and problems with the response
    public void setErrorMessage(String message) {
        statusMessage.setForeground(Color.red);
        statusMessage.setText(message);
    }

    //If the post is published fine
    //Clear the input fields, reset the charCounter and display a success message
    public void successMessage() {
        authorTF.setText("");
        postTF.setText("");
        charCounter.setText("0/240");
        statusMessage.setForeground(Color.white);
        statusMessage.setText("Post was anonymised!");
    }
}
