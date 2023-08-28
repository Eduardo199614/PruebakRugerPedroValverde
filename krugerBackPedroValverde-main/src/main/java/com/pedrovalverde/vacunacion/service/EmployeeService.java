package com.pedrovalverde.vacunacion.service;

import com.pedrovalverde.vacunacion.entities.Employee;
import com.pedrovalverde.vacunacion.exception.GeneralException;
import com.pedrovalverde.vacunacion.pojos.RegisterEmployeeINP;
import com.pedrovalverde.vacunacion.pojos.UpdateEmployeeINP;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public interface EmployeeService {

    
    Map<String, Object> createEmployee(RegisterEmployeeINP employee) throws GeneralException;

    
    Employee updateEmployee(RegisterEmployeeINP employee) throws GeneralException;

    
    Set<Employee> getAllEmployee() throws GeneralException;

    
    Map<String,Object> deleteEmployee(UUID employeeId) throws GeneralException;


    
    Employee updateEmployee(UpdateEmployeeINP employee) throws GeneralException, Exception;

    
    Set<Employee> getAllEmployeeByStatusVaccination(Boolean statusVaccinated) throws GeneralException;

     
    Set<Employee> getAllEmployeeByTypeVaccination(UUID vaccinateType) throws GeneralException;

    
    Set<Employee> getEmployeeByVaccinateDate(Date fromDate, Date toDate) throws GeneralException;

    
    Employee getInfoByEmployee() throws GeneralException;
}
