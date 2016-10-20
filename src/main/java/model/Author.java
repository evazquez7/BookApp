/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Emilio
 */
public class Author {
    
    private Integer authorId;
    private String authorName;
    private Date dateAdded;


    public Author() {
    }

    public Author(Integer authorId, String authorName, Date dateAdded){
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateAdded = dateAdded;
    }

    

    

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
        
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) throws IllegalArgumentException{
        if(authorName == null || authorName.isEmpty()){
            throw new IllegalArgumentException ("Author name cannot be null or empty");
        }
        this.authorName = authorName;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) throws NullPointerException {
        if (dateAdded == null){
            throw new NullPointerException("Date cannot be null");
        }
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Author{" + "authorId=" + authorId + ", authorName=" + authorName + ", dateAdded=" + dateAdded + '}';
    }

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.authorId);
        hash = 97 * hash + Objects.hashCode(this.authorName);
        hash = 97 * hash + Objects.hashCode(this.dateAdded);
        return hash;
    }
    
    


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Author other = (Author) obj;
        if (!Objects.equals(this.authorName, other.authorName)) {
            return false;
        }
        if (!Objects.equals(this.authorId, other.authorId)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        return true;
    }

    
    
}
