package com.example.nba.model;

import com.example.nba.interfaces.*;


public abstract class Person implements Entity{
    //FIELDS
    protected Integer id;
    protected String name;
    protected int age;
  
    //GETTERS
    public String getName() { return name; }
    public int getAge() { return age; }

    //SETTERS
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    
    //TO STRING METHOD
    public String toString(){
        String res = """
            {
                id: %d,
                name: %s,
                age: %d,
            }
                """.formatted(id, name, age);
        return res;
    }

    //OVERRIDE FOR THE INTERFACES
    
    @Override 
    public Integer getId(){return id;}
    @Override
    public String seq(boolean dbFlag){
        String res = "%d,%s,%d";
        if(dbFlag){
            res = res.substring(3);
            return res.formatted(name, age);
        }
        
        return res.formatted(id, name, age);
    }

}

