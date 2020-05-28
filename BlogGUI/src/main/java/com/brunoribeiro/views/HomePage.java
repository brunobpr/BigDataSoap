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
        //PANEL WITH A BANNER ON THE TOP OF THE SCREEN
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

    public void buildForm() {
        formPanel = new JPanel();
        add(formPanel, BorderLayout.EAST);
        formPanel.setMaximumSize(new Dimension(200, 600));
        formPanel.setBackground(new Color(0, 128, 128));
        formPanel.setLayout(grid);
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
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(-50, 0, 20, 0);
        formPanel.add(jl = new JLabel(icon = new ImageIcon("./src/main/java/com/brunoribeiro/views/icon.png")), gbc);
        gbc.gridy = 1;
        gbc.ipady = 0;
        gbc.insets = new Insets(0, 0, 0, 0);
        formPanel.add(authorLabel, gbc);
        gbc.gridy = 2;
        formPanel.add(authorTF, gbc);
        gbc.gridy = 3;
        formPanel.add(postLabel, gbc);
        gbc.gridy = 4;
        formPanel.add(postTF, gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        formPanel.add(button, gbc);
        gbc.gridy = 5;
        gbc.gridx = 1;
        formPanel.add(charCounter, gbc);
        gbc.gridy = 6;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        formPanel.add(statusMessage, gbc);
        add(formPanel, BorderLayout.EAST);
        validate();
        repaint();
    }

    public void refreshPage(ArrayList<Post> list) {
        remove(mainPanel);
        remove(jcp);
        add(mainPanel);
        mainPanel.removeAll();
        jcp = new JScrollPane(mainPanel);
        jcp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jcp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      
        add(jcp);
        mainPanel.setLayout(new GridLayout(list.size() + 6, 1));
        for (Post p : list) {
            JPanel panel = new JPanel();
            panel.setLayout(grid);
            panel.setBackground(Color.white);
            JLabel author = new JLabel(p.getAuthor());
            author.setFont(font);
            JLabel date = new JLabel(p.getPostedDate());
            date.setFont(new Font("arial", Font.PLAIN, 10));
            JTextArea text = new JTextArea(p.getText(), (p.getText().length()/40), 40);
            text.setLineWrap(true);
            text.setWrapStyleWord(true);
            text.setEditable(false);
            text.setBackground(Color.white);
           // text.setPreferredSize(new Dimension(550, p.getText().length() + 20));
            gbc.gridy = 0;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.insets = new Insets(20, 0, 0, 0);
            panel.setBackground(Color.white);
            panel.add(jl = new JLabel(icon = new ImageIcon("./src/main/java/com/brunoribeiro/views/user.png")), gbc);
            gbc.gridy = 1;
            gbc.insets = new Insets(0, 0, 0, 0);
            panel.add(author, gbc);
            gbc.gridy = 2;
            panel.add(date, gbc);
            gbc.gridy = 3;
            gbc.insets = new Insets(10, 0, 0, 20);
            panel.add(text, gbc);
            gbc.insets = new Insets(0, 0, 0, 0);
            mainPanel.add(panel);
            gbc.gridy = 4;
          //  panel.setPreferredSize(new Dimension(400, p.getText().length() + 100));
            JPanel line = new JPanel();
            line.setPreferredSize(new Dimension(600, 2));
            line.setBackground(Color.black);
            panel.add(line, gbc);
        }
        validate();
        repaint();
    }

    public String getPost() {
        return postTF.getText().replace("\n", " • ");
    }

    public String getAuthor() {
        return authorTF.getText().toString();
    }

    public int getCharCounter() {
        return postTF.getText().length();
    }

    public void setCharCounterText(int counter) {
        charCounter.setText(String.valueOf(counter) + "/240");
        if (counter > 240) {
            charCounter.setForeground(Color.red);
        } else {
            charCounter.setForeground(Color.white);
        }
    }

    public void setErrorMessage(String message) {
        statusMessage.setForeground(Color.red);
        statusMessage.setText(message);
    }

    public void successMessage() {
        authorTF.setText("");
        postTF.setText("");
        statusMessage.setForeground(Color.white);
        statusMessage.setText("Post was anonymised");
        
    }

}
