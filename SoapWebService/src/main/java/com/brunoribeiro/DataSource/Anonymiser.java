/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoribeiro.DataSource;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class converts names into *
 * It's called from the Post class 
 * @author brunoribeiro
 */
public class Anonymiser {

    private static Anonymiser instance = null;

    private Anonymiser() {
    }

    public String anonymiseText(String text, String author) {
        String[] words = text.split(" "); // Divide the whole text into array of words 
        String anonymousText = ""; // Empty string
        ArrayList<String> namesList = new ArrayList<String>(); //ArrayList that holds the names in the file
        try {
            //This is a trick to get the correct path from the names.txt file no matter where the project is stored
            URL path = getClass().getResource("names.txt");
            File file = new File(path.getPath()); //Accessing the names.txt file
            Scanner scanner = new Scanner(file);//Reading the file that holds the names
            while (scanner.hasNextLine()) {
                //For each line, convert to uppercase and add it to the list
                namesList.add(scanner.nextLine().toUpperCase());
            }
            scanner.close();
            //If the author's name isn't on the list, it must be added.
            if(!namesList.contains(author.toUpperCase())){ 
              BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
              //Create a new line and append the name to the names.txt file
              writer.newLine();
              writer.append(author);
              writer.close();
              //Also, add it to the array list of names
              namesList.add(author.toUpperCase());
            }
        } catch (FileNotFoundException e) {
            //If the file is not found... the trick didn't work. Im so sorry.
            //Return a error message
            return "404 - Names.txt not found :(";
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
        //For each word in the text
        for(String word : words){
            //Go through the whole list of names
            for(String name : namesList){
                //The replace allows names followed by sign to be validate
                //When the text is splitted between white spaces things such as "Bruno," may happen
                if(name.equals(word.replaceAll("[,.!?]+", "").toUpperCase())){
                     //If finds a perfect match, convert it to ****
                     word = word.replaceAll("[A-Za-z]", "*");
                }
            }
           //Concatinate the words to build a text with anonymised names
           anonymousText += " " + word;
        }
        //Remove the first white space created when the words were concatenated
        //And return it
        return anonymousText.replaceFirst(" ", "");
    }
    
    public static Anonymiser getInstance() {
        //This is a singleton class
        if (instance == null) {
            instance = new Anonymiser();
        }
        return instance;
    }

}
