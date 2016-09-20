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
    
    private int authorId;
    private String authorName;
    private Date dateAdded;

    public Author(int authorId) throws IllegalArgumentException{
        if(authorId < 0){
            throw new IllegalArgumentException("Author Id needs to be greater than 0 ");
        } else{
        this.authorId = authorId;
        }
    }

    public Author() {
    }

    public Author(int authorId, String authorName, Date dateAdded) throws IllegalArgumentException{
        if (authorId < 0 || authorName == null || authorName.isEmpty() || dateAdded == null){
            throw new IllegalArgumentException("AuthorId needs to be greater than 0, authorName cannot be empty or null and date cannot be null ");
        }
        this.authorId = authorId;
        this.authorName = authorName;
        this.dateAdded = dateAdded;
    }

    

    

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) throws IllegalArgumentException {
        
        if(authorId < 0){
            throw new IllegalArgumentException("Author Id needs to be greater than 0 ");
        } else{
        this.authorId = authorId;
        }
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
        int hash = 3;
        hash = 67 * hash + this.authorId;
        hash = 67 * hash + Objects.hashCode(this.authorName);
        hash = 67 * hash + Objects.hashCode(this.dateAdded);
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
        if (this.authorId != other.authorId) {
            return false;
        }
        if (!Objects.equals(this.authorName, other.authorName)) {
            return false;
        }
        if (!Objects.equals(this.dateAdded, other.dateAdded)) {
            return false;
        }
        return true;
    }
    
}
