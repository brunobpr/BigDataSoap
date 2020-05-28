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
 * This class converts names into *
 * @author brunoribeiro
 */
public class Anonymiser {

    private static Anonymiser instance = null;

    private Anonymiser() {
    }

    public String anonymiseText(String text) {
        String[] words = text.split(" "); // Divide the whole text into array of words 
        String anonymousText = ""; // Empty string
        ArrayList<String> namesList = new ArrayList<String>(); //ArrayList that holds the names in the file
        try {
            //This is a trick to get the correct path from the names.txt file no matter where the project is stored
            //Because it's stored inside of the same folder of the Anonymiser.class, it is possible to get the path
            String path = String.valueOf(this.getClass().getProtectionDomain().getCodeSource().getLocation());
            //And replace the file name and type
            path = path.replace("/Anonymiser.class", "/names.txt");
            //And define the absolute file path
            path = path.replace("file:", "");
            File file = new File(path); //Accessing the /something/../../names.txt file
            Scanner scanner = new Scanner(file);//Reading the file that holds the names
            while (scanner.hasNextLine()) {
                //For each line, convert it to uppercase and add it to the list
                namesList.add(scanner.nextLine().toUpperCase());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            //If the file is not found... the trick didn't work. Im so sorry.
            return "404 - Names.txt not found :(";
        }
        //For each word in the text
        for(String word : words){
            //Go through the whole list of names
            for(String name : namesList){
                //If finds a perct match, convert it to ****
                if(name.equals(word.replaceAll("[,.!?]+", "").toUpperCase())){ //The replace all will allow  
                     word = word.replaceAll("[A-Za-z]", "*");
                }
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
