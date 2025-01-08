package com.example.nba.model;


public class DraftPlayer extends Person{



    @Override
    public Integer getId() {
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

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
