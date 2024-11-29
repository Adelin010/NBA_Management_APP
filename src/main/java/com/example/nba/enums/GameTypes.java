package com.example.nba.enums;

public enum GameTypes {
    RS("Regular Season"),
    PG("PlayoffGame"),
    PT("Play in Tournament");

    private final String des;

    GameTypes(String desc){
        this.des = desc;
    }

    public String description(){
        return this.des;
    }

}
