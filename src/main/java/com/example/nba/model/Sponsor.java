package com.example.nba.model;



public class Sponsor extends Person{
    //FIELDS
    protected static int MAX_ID = 1;
    protected int pf;
    protected String email;
    //CONSTRUCTORS
    public Sponsor(String sponsorName) {
        this.id = Sponsor.MAX_ID;
        Sponsor.MAX_ID++;
        this.name = sponsorName;
    }

    public Sponsor(Integer id, String sponsorName, int age, String email, boolean pf) {
        this.id = id;
        this.name = sponsorName;
        this.age = age;
        this.email = email;
        this.pf = pf == true ? 1 : 0;
    }

    public Sponsor(String[] args){
        id = Integer.parseInt(args[0]);
        MAX_ID = MAX_ID > id ? MAX_ID : 1+id;
        name = args[1];
        age = Integer.parseInt(args[2]);
        email = args[3];
        pf = Integer.parseInt(args[4]);
    }

    //GETTERS
    public static int getMaxId(){
        return MAX_ID;
    }

    public String getSponsorName() {
        return name;
    }
    public String getEMail(){
        return email;
    }
    public boolean isPF(){
        return pf == 1 ? true : false;
    }

    //SETTERS
    public void setSponsorName(String sponsorName) {
        this.name = sponsorName;
    }
    public void setEMail(String email){
        this.email = email;
    }
    public void setPF(){
        this.pf = 1;
    }
   
    //TO STRING METHOD  
    public String toString(){
        String res = """
            {
                id: %d,
                name: %s,
                age: %d,
                email: %s, 
                pf: %d
            }
                """.formatted(id, name, age, email, pf);
        return res;
    }

    //OVERRIDE FOR THE INTERFACES
    @Override
    public Integer getId(){return id;}

   
    @Override
    public String seq(boolean dbFlag){
        String res = "%d,%s,%d,%s,%d";
        if(dbFlag){
            res = res.substring(3);
            return res.formatted(name, age, email, pf);
        }
        
        return res.formatted(id, name, age, email, pf);
    }
}
