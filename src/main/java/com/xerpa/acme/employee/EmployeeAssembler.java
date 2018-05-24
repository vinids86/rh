package com.xerpa.acme.employee;

import com.xerpa.acme.resources.Employee;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsável por converter Objeto de entrada do funcionário para uma entidade do sistema
 */
public class EmployeeAssembler {

    public EmployeeEntity mapToEntity(Employee employee) {
        final EmployeeEntity entity = new EmployeeEntity();
        entity.setMinimumRestIntervalInMinutes(employee.getWorkload().get(0).getMinimumRestIntervalInMinutes());
        entity.setPisNumber(employee.getPisNumber());
        entity.setWorkloadInMinutes(employee.getWorkload().get(0).getWorkloadInMinutes());
        entity.setDaysOfWork(employee.getWorkload().get(0).getDays());
        return entity;
    }

    public List<EmployeeEntity> mapToEntityList(List<Employee> employees) {
        final List<EmployeeEntity> entities = new ArrayList<>();
        for (Employee employee : employees)
            entities.add(mapToEntity(employee));
        return entities;
    }
}
