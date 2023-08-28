package com.pedrovalverde.vacunacion.service.impl;

import com.pedrovalverde.vacunacion.commons.Commons;
import com.pedrovalverde.vacunacion.entities.Employee;
import com.pedrovalverde.vacunacion.entities.enums.VaccinationStatus;
import com.pedrovalverde.vacunacion.exception.GeneralException;
import com.pedrovalverde.vacunacion.mappers.EmployeeMapper;
import com.pedrovalverde.vacunacion.pojos.RegisterEmployeeINP;
import com.pedrovalverde.vacunacion.pojos.UpdateEmployeeINP;
import com.pedrovalverde.vacunacion.repositories.EmployeeRepository;
import com.pedrovalverde.vacunacion.security.UserInfoAuthentication;
import com.pedrovalverde.vacunacion.service.EmployeeService;
import com.pedrovalverde.vacunacion.service.tools.EmployeeTool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
public class EmployeeServiceImpl extends EmployeeTool implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Map<String, Object> createEmployee(RegisterEmployeeINP employee) throws GeneralException {
        log.debug("Service to save an employee : {}", employee);
        Map<String,Object> resp = new LinkedHashMap<>();
        String password = createAuthPassword();
        Commons.validateIdentifier(employee.getIdentification());
        existEmployeeByIdentification(employee.getIdentification());
        Employee employeeEntity = EmployeeMapper.toEntityRegister(employee);
        Employee emp = employeeRepository.save(employeeEntity);
        createAuthForEmployee(emp, passwordEncoder.encode(password));
        resp.put("username", employee.getIdentification());
        resp.put("password", password);
        resp.put("employee", emp);
        return resp;
    }

    @Override
    @Transactional
    public Employee updateEmployee(RegisterEmployeeINP employee) throws GeneralException {
        log.debug("Service to update an employee : {}", employee);
        Commons.validateIdentifier(employee.getIdentification());
        Employee employeeEntity = updateDataEmployee(employee);
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public Set<Employee> getAllEmployee() throws GeneralException {
        return employeeRepository.findAllByOrderBySurnameAsc();
    }

    @Override
    @Transactional
    public Map<String, Object> deleteEmployee(UUID employeeId) throws GeneralException {
        Map<String,Object> response = new LinkedHashMap<>();
        getEmployeeById(employeeId);
        employeeRepository.deleteById(employeeId);
        response.put("response", "ok");
        return response;
    }

    @Override
    @Transactional
    public Employee updateEmployee(UpdateEmployeeINP employee) throws Exception {
        log.debug("Service to update an employee with complete information : {}", employee);
        return employeeRepository.save( updateCompleteDataEmployee(employee) );
    }

    @Override
    public Set<Employee> getAllEmployeeByStatusVaccination(Boolean statusVaccinated) throws GeneralException {
        return employeeRepository.findByVaccinationStatusOrderBySurnameAsc( statusVaccinated ? VaccinationStatus.Y : VaccinationStatus.N);
    }

    @Override
    public Set<Employee> getAllEmployeeByTypeVaccination(UUID vaccinateType) throws GeneralException {
        return employeeRepository.getByVaccinateType(vaccinateType);
    }

    @Override
    public Set<Employee> getEmployeeByVaccinateDate(Date fromDate, Date toDate) throws GeneralException {
        return employeeRepository.getByRangeDate(fromDate, toDate);
    }

    @Override
    public Employee getInfoByEmployee() throws GeneralException {
        return employeeRepository.findByIdentificationAndStatus(UserInfoAuthentication.getUsername(),true)
                .orElseThrow(this::generateErrorNotFoundEmployee);
    }

    /**
     * Search and Validate employee into database for identifier
     *
     * @param identification           Identifier
     */
    private void existEmployeeByIdentification(String identification) {
        employeeRepository.findByIdentificationAndStatus(identification, true)
                .stream().findAny().ifPresent(s -> {
                    throw generateErrorEmployee("El empleado ya se encuentra registrado.");
                });
    }

    private Employee updateDataEmployee (RegisterEmployeeINP employeeINP) {
        String[] parts = employeeINP.getLastname().trim().split(" ");
        String surname = "";
        String secondSurname = "";
        if(parts.length == 2){
            surname = parts[0];
            secondSurname = parts[1];
        }else {
            surname = employeeINP.getLastname();
        }
        Employee employee = getEmployeeById(employeeINP.getId());
        employee.setIdentification(employeeINP.getIdentification());
        employee.setNames(employeeINP.getNames());
        employee.setSurname(surname);
        employee.setSecondSurname(secondSurname);
        employee.setEmail(employeeINP.getEmail());
        return employee;
    }

    private Employee updateCompleteDataEmployee (UpdateEmployeeINP employeeINP) {
        Employee employee = getEmployeeByIdentification(employeeINP.getUsername());
        employee.setBirthDate(employeeINP.getBirthDate());
        employee.setVaccinationStatus(employeeINP.getVaccinationStatus() ? VaccinationStatus.Y : VaccinationStatus.N);
        employee.setMobilePhones(createEmployeePhone(employee, employeeINP));
        employee.setAddresses(createEmployeeAddress(employee, employeeINP));

        if(employeeINP.getVaccinationStatus()){
            employee.setVaccinationEmployees(createEmployeeVaccination(employee, employeeINP));
        }
        return employee;
    }

    private void createAuthForEmployee(Employee employee, String password){
        employee.setAuths(createAuth(employee, password));
        employeeRepository.save(employee);
    }

    private Employee getEmployeeById(UUID employeeId){
        return employeeRepository.findByIdAndStatus(employeeId,true)
                .orElseThrow(this::generateErrorNotFoundEmployee);
    }

    private Employee getEmployeeByIdentification(String identifier){
        return employeeRepository.findByIdentificationAndStatus(identifier,true)
                .orElseThrow(this::generateErrorNotFoundEmployee);
    }

    private String createAuthPassword(){
        return Commons.createAleatoryPassword();
    }
}
