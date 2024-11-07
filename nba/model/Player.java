package nba.model;


public abstract class Player implements IdBounded{
    protected Integer id;
    protected String name;
    protected int age;
    protected double salary;
    //Nu ai voie sa ai constructori in clasele abstracte
    public Player(String name, int age, double salary) {}

    public Player() {

    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setSalary(double salary) { this.salary = salary; }
    @Override 
    public Integer getId(){return id;}
}

