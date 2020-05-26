/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoribeiro.DataSource;

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
            File file = new File("/Users/brunoribeiro/git/BigDataSoap/SoapWebService/src/main/java/com/brunoribeiro/DataSource/names.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                namesList.add(scanner.nextLine().toUpperCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(String word : words){
            if(namesList.contains(word.replaceAll("[,.!?]+", "").toUpperCase())){    
               word = word.replaceAll("[A-Za-z]", "*");
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
