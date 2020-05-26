/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoribeiro.views;

import com.brunoribeiro.controllers.Controller;
import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
    private JTextField authorTF;
    private JTextArea postTF;
    private JLabel lettersCounter;
    private GridBagLayout grid = new GridBagLayout();
    private GridBagConstraints gbc = new GridBagConstraints();

    public HomePage(Controller controller) {
        this.controller = controller;
        buildFrame();
    }

    private void buildFrame() {
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
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
        lettersCounter = new JLabel("240/240");
        font = new Font("arial", Font.BOLD, 16);
        authorLabel.setForeground(Color.white);
        postLabel.setForeground(Color.white);
        lettersCounter.setForeground(Color.white);
        button.setForeground(new Color(0, 128, 128));
        authorLabel.setFont(font);
        postLabel.setFont(font);
        button.setFont(font);
        button.addActionListener(controller);
        
        authorTF = new JTextField(16);
        postTF = new JTextArea();
        postTF.setLineWrap(true);
        postTF.setPreferredSize(new Dimension(195, 200));
        gbc.gridy = 0;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        formPanel.add(authorLabel, gbc);
        gbc.gridy = 1;

        formPanel.add(authorTF, gbc);
        gbc.gridy = 2;
        formPanel.add(postLabel, gbc);
        gbc.gridy = 3;
        formPanel.add(postTF, gbc);
        gbc.gridy = 4;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        formPanel.add(button, gbc);
        gbc.gridy = 4;
        gbc.gridx = 2;
        formPanel.add(lettersCounter, gbc);
        add(formPanel, BorderLayout.EAST);
        validate();
        repaint();
    }
}
