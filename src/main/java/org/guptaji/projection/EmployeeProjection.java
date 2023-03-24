package org.guptaji.projection;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class EmployeeProjection {

    // Here i don't want employee's age
    public Integer id;
    public String name;
    public int salary;
    public String orgName;

    public EmployeeProjection(Integer id, String name, int salary, String orgName) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.orgName = orgName;
    }

    public EmployeeProjection() {
        // default constructor
    }

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
}
