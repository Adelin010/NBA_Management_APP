public abstract class Player {
    protected String name;
    protected int age;
    protected double salary;
    public Player(String name, int age, double salary) {}
    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setSalary(double salary) { this.salary = salary; }

}

