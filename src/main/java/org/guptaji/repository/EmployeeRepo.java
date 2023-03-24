package org.guptaji.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.guptaji.entity.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@ApplicationScoped
//@NamedQueries({
//        @NamedQuery(name = "Employee.getByOrgAndAge", query = "select * from Employee where orgName = ?1 and age >= ?2")
//})
public class EmployeeRepo implements PanacheRepositoryBase<Employee, Integer> {

    // Named Query does n't work on repository

    // named query
    // Named queries can only be defined inside your JPA entity classes (being the Panache entity class,
    // or the repository parameterized type), or on one of its super classes.
//    public List<Employee> findByOrgAndAge(String orgName, int age){
//        return  find("#Employee.getByOrgAndAge", orgName, age).list();
//    }

}
