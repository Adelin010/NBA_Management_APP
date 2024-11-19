package nba.model;

import nba.interfaces.*;


public abstract class Player implements IdBounded, FileBounded{
    //FIELDS
    protected Integer id;
    protected String name;
    protected int age;
    protected double salary;
  
    //GETTERS
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    //SETTERS
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setSalary(double salary) { this.salary = salary; }
    
    //TO STRING METHOD
    public String toString(){
        String res = """
            {
                id: %d,
                name: %s,
                age: %d,
                salary: %.2f
            }
                """.formatted(id, name, age, salary);
        return res;
    }

    //OVERRIDE FOR THE INTERFACES
    @Override
    public String fileFormat(){
        return "";
    }

    @Override 
    public Integer getId(){return id;}

}

