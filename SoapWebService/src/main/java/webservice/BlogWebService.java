/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import com.brunoribeiro.DataSource.*;

/**
 *
 * @author brunoribeiro - 2017138
 */
@WebService(serviceName = "BlogWebService")
public class BlogWebService {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Anonymise")
    public String Anonymise(@WebParam(name = "text") String text, @WebParam(name = "author") String author) {
        Post post = new Post(author, text);
        String response = post.anonymiseText();
        return response;
    } 
}
