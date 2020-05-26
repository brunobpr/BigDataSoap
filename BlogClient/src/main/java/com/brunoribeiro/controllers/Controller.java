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
/**
 *
 * @author brunoribeiro
 */
public class Controller {
  
    public Controller(){
        callAsyncOperation();
    }
    
    
    public void callAsyncOperation(){
        
        try { // Call Web Service Operation(async. callback)
            
            BlogWebService_Service service = new BlogWebService_Service();
            BlogWebService port = service.getBlogWebServicePort();
            // TODO initialize WS operation arguments here
            java.lang.String text = "Hi John, this is Bruno. Do you have news from Greg?";
            java.lang.String author = "Bruno";
            javax.xml.ws.AsyncHandler<com.brunoribeiro.models.AnonymiseResponse> asyncHandler = new javax.xml.ws.AsyncHandler<com.brunoribeiro.models.AnonymiseResponse>() {
                public void handleResponse(javax.xml.ws.Response<com.brunoribeiro.models.AnonymiseResponse> response) {
                    try {
                        // TODO process asynchronous response here
                        System.out.println("Result = "+ response.get().getReturn());
                    } catch(Exception ex) {
                        // TODO handle exception
                    }
                }
            };
            java.util.concurrent.Future<? extends java.lang.Object> result = port.anonymiseAsync(text, author, asyncHandler);
            while(!result.isDone()) {
                // do something
                Thread.sleep(100);
            }
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }

        
    }
    
}
