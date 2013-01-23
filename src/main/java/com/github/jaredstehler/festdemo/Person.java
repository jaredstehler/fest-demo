package com.github.jaredstehler.festdemo;

import java.util.List;

import com.google.common.base.Objects;

public class Person {
    
    private String name;
    private String email;
    private int favoriteNumber;
    
    private List<String> nicknames;

    public Person(String name, String email, int favoriteNumber, List<String> nicknames) {
        this.name = name;
        this.email = email;
        this.favoriteNumber = favoriteNumber;
        this.nicknames = nicknames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getFavoriteNumber() {
        return favoriteNumber;
    }

    public void setFavoriteNumber(int favoriteNumber) {
        this.favoriteNumber = favoriteNumber;
    }

    public List<String> getNicknames() {
        return nicknames;
    }

    public void setNicknames(List<String> nicknames) {
        this.nicknames = nicknames;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(name, email, favoriteNumber, nicknames);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof Person)){
            return false;
        }
        
        Person other = (Person) obj;
        return Objects.equal(name, other.name) &&
               Objects.equal(email, other.email) &&
               Objects.equal(favoriteNumber, other.favoriteNumber) &&
               Objects.equal(nicknames, other.nicknames);
    }

}
