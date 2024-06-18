package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * employee login
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * create new employee
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * query by page
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * enable or disable employee account
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * query employee by id
     * @param id
     * @return
     */
    Employee getByID(Long id);

    /**
     * update employee info
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
