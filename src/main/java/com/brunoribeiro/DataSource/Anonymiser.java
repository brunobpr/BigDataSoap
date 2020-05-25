/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoribeiro.DataSource;

import static com.sun.tools.javac.tree.TreeInfo.name;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author brunoribeiro
 */
public class Anonymiser {

    private static Anonymiser instance = null;

    private Anonymiser() {
    }

    public String anonymiseText(String text) {
        String[] words = text.split(" ");
        String anonymousText = "";
        ArrayList<String> namesList = new ArrayList<String>();
        try {
            File file = new File("/Users/brunoribeiro/git/BigDataSoap/src/main/java/com/brunoribeiro/DataSource/names.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                namesList.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(String word : words){
            if(namesList.contains(word.replace(",", ""))){
               word = word.replaceAll(".", "*");
            }
            anonymousText += " " + word;
        }
        return anonymousText.replaceFirst(" ", "");
    }
    
    public static Anonymiser getInstance() {
        if (instance == null) {
            instance = new Anonymiser();
        }
        return instance;
    }

}
