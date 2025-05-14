public class Employee {
    String name;
    int salary;
    int height;
    IT pos;
    Employee left, right;

    Employee(String name, int salary, IT pos) {
        this.name = name;
        this.salary = salary;
        this.pos = pos;
    }
}
