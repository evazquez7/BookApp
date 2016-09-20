/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Emilio
 */
public class AuthorService {
    

   private List<Author> authors;
   

    public List<Author> getAuthors() {
        return authors;
    }

   public AuthorService() {
       
       authors = new ArrayList<>();
       
       authors.add(new Author(1,"Juan Carrillo", new Date()));
       authors.add(new Author(2,"Edgar Perez", new Date()));
       authors.add(new Author(3, "Eric Corona", new Date()));

   }
  
}
