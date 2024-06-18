package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * dish controller
 */
@RestController
@RequestMapping("/admin/dish")
@Api(tags = "dish interface")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * create new dish
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("create new dish")
    public Result save(@RequestBody DishDTO dishDTO){
        log.info("create new dish: {}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return Result.success();
    }

    @GetMapping("/page")
    @ApiOperation("query dish by page")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO){
        log.info("query dish by category: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * delete dishes in batch
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("delete dishes in batch")
    public Result delete(@RequestParam List<Long> ids){
        log.info("delete dished in batch: {}", ids);
        dishService.deleteBatch(ids);
        return Result.success();
    }

    /**
     * query dish by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("query dish by id")
    public Result<DishVO> getByID(@PathVariable Long id){
        log.info("query dish by id: {}", id);
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return Result.success(dishVO);
    }

    @PutMapping
    @ApiOperation("update dish")
    public Result update(@RequestBody DishDTO dishDTO){
        log.info("update dish:{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    @ApiOperation("update dish status")
    public Result startOrstop(@PathVariable Integer status, Long id){
        dishService.startOrstop(status, id);
        return Result.success();
    }
}
