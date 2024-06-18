package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

public interface DishService {
    /**
     * create dish and flavor accordingly
     * @param dishDTO
     */
    public void saveWithFlavor(DishDTO dishDTO);

    /**
     * query dish by category
     * @param dishPageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * delete dishes in batch
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * query dish and its flavor by id
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     * update dish basic info and flavor by id
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     * set dish status whether on sale
     * @param status
     * @param id
     */
    void startOrstop(Integer status, Long id);
}
