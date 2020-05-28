/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brunoribeiro.models;

import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brunoribeiro
 */
public class BlogService {
    private String responseMessage;
    
    
    public BlogService() {
    }

    public String getResponse(String a, String t) {

         try { // Call Web Service Operation(async. callback)
            
            BlogWebService_Service service = new BlogWebService_Service();
            BlogWebService port = service.getBlogWebServicePort();
            // TODO initialize WS operation arguments here
            java.lang.String text = t;
            java.lang.String author = a;
            javax.xml.ws.AsyncHandler<com.brunoribeiro.models.AnonymiseResponse> asyncHandler = new javax.xml.ws.AsyncHandler<com.brunoribeiro.models.AnonymiseResponse>() {
                public void handleResponse(javax.xml.ws.Response<com.brunoribeiro.models.AnonymiseResponse> response) {
                    try {
                        responseMessage = response.get().getReturn() ;
                    } catch(Exception ex) {
                        responseMessage = ex.getMessage();
                    }
                }
            };
            java.util.concurrent.Future<? extends java.lang.Object> result = port.anonymiseAsync(text, author, asyncHandler);
            while(!result.isDone()) {
                // do something
                Thread.sleep(100);
            }
        } catch (Exception ex) {
            responseMessage = ex.getMessage();
        }
         
         return responseMessage;
    }

}
