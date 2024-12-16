package com.example.nba.model;



public class Manager extends Person{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer teamId;

    public Manager(String name, int age, Integer teamId){
        this.id = Manager.MAX_ID;
        Manager.MAX_ID++;
        this.name = name;
        this.teamId = teamId;
        this.age = age;
    }

    public Manager(Integer id, String name,int age, Integer teamId){
        this.id = id;
        this.name = name;
        this.age = age;
        this.teamId = teamId;
    }
    public Manager(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : id+1;
        this.name = args[1];
        this.age = Integer.parseInt(args[2]);
        this.teamId = "null".equals(args[3]) ? null : Integer.parseInt(args[3]);
    }

    //Getters
    public static int getMaxId(){
        return MAX_ID;
    }
    
    public Integer getTeamId() {return teamId;}   
    //Setters  
    public void setTeamId(Integer teamId) {this.teamId = teamId;}
    //Usefull functions
    public String toString() {
        String res = """
            {
                id: %d,
                name: %s,
                age: %d,
                teamId: %d
            }
                """.formatted(id, name, age, teamId);
        return res;
    }

    @Override 
    public Integer getId(){return id;}


    @Override
    public String seq(boolean dbFlag){
        String res = "%d,%s,%d,%d";
        if(dbFlag){
            res = res.substring(3);
            return res.formatted(name, age, teamId);
        }
        
        return res.formatted(id, name, age, teamId);
    }
}
