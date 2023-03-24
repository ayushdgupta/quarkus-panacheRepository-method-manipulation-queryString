package org.guptaji.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@Entity
//@NamedQueries({
//        @NamedQuery(name = "Employee.getByOrgAndAge", query = "select * from Employee where orgName = ?1 and age >= ?2")
//})
//public class Employee extends PanacheEntity {
public class Employee {
    @Id
    private Integer id;
    private String name;
    private int salary;
    private String orgName;
    private int age;

    public Employee(Integer id, String name, int salary, String orgName, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.orgName = orgName;
        this.age = age;
    }

    public Employee() {
        // default constructor
    }

    // named query
    // Named queries can only be defined inside your JPA entity classes (being the Panache entity class,
    // or the repository parameterized type), or on one of its super classes.
//    public List<Employee> findByOrgAndAge(String orgName, int age){
//        return  find("#Employee.getByOrgAndAge", orgName, age).list();
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary='" + salary + '\'' +
                ", orgName='" + orgName + '\'' +
                ", age=" + age +
                '}';
    }
}
