package org.guptaji.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import org.guptaji.entity.Employee;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepo implements PanacheRepositoryBase<Employee, Integer> {
}
