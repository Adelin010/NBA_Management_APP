package com.example.nba.model;



public class Manager extends Person{
    //FIELDS
    protected static int MAX_ID = 1;
    protected Integer teamId;
    protected String password;

    public Manager(String name,String password ,int age, Integer teamId){
        this.id = Manager.MAX_ID;
        Manager.MAX_ID++;
        this.name = name;
        this.teamId = teamId;
        this.age = age;
        this.password = password;
    }

    public Manager(Integer id,String password, String name,int age, Integer teamId){
        this.id = id;
        this.name = name;
        this.age = age;
        this.teamId = teamId;
        this.password = password;
    }
    public Manager(String[] args){
        this.id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : id+1;
        this.name = args[1];
        this.password = args[2];
        this.age = Integer.parseInt(args[3]);
        this.teamId = "null".equals(args[4]) ? null : Integer.parseInt(args[4]);
    }

    //Getters
    public static int getMaxId(){
        return MAX_ID;
    }
    
    public Integer getTeamId() {return teamId;}   
    public String getPassword(){return password;}
    //Setters  
    public void setTeamId(Integer teamId) {this.teamId = teamId;}
    public void setPassword(String password){this.password = password;}
    //Usefull functions
    public String toString() {
        String res = """
            {
                id: %d,
                name: %s,
                password: %s,
                age: %d,
                teamId: %d
            }
                """.formatted(id, name,password, age, teamId);
        return res;
    }

    @Override 
    public Integer getId(){return id;}


    @Override
    public String seq(boolean dbFlag){
        String res = "%d,%s,%s,%d,%d";
        if(dbFlag){
            res = res.substring(3);
            return res.formatted(name,password, age, teamId);
        }
        
        return res.formatted(id, name,password, age, teamId);
    }
}
