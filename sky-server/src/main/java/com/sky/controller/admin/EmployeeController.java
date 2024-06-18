package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.constant.JwtClaimsConstant;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.properties.JwtProperties;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.EmployeeService;
import com.sky.utils.JwtUtil;
import com.sky.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工管理
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "employee interface")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(value = "employee login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("员工登录：{}", employeeLoginDTO);
        Employee employee = employeeService.login(employeeLoginDTO);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * 退出
     *
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation(value = "employee logout")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * create new employee
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("create new employee")
    public Result<String> save(@RequestBody EmployeeDTO employeeDTO){
        log.info("create new employee: {}", employeeDTO);
        employeeService.save(employeeDTO);

        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("query employee by page")
    public Result<PageResult> page(EmployeePageQueryDTO employeePageQueryDTO){
        log.info("query employee by page with parameter: {}", employeePageQueryDTO);
        PageResult pageResult = employeeService.pageQuery(employeePageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * enable or disable employee account
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("enable or disable employee account")
    public Result startOrstop(@PathVariable Integer status, Long id){
        log.info("enable or disable employee account: {}, {}", status, id);
        employeeService.startOrStop(status, id);
        return Result.success();
    }

    /**
     * query employee info by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("query employee info by id")
    public Result<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getByID(id);
        return Result.success(employee);
    }

    /**
     * update employee info
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("update employee info")
    public Result update(@RequestBody EmployeeDTO employeeDTO){
        log.info("update employee info: {}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }
}
