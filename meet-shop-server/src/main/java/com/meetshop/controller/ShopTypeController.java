package com.meetshop.controller;


import com.meetshop.dto.Result;
import com.meetshop.entity.ShopType;
import com.meetshop.service.IShopTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/shop-type")
@Api(tags = "店铺类型相关接口")
@Slf4j
public class ShopTypeController {
    @Resource
    private IShopTypeService typeService;

    @GetMapping("list")
    @ApiOperation("查询所有店铺类型")
    public Result queryTypeList() {
        log.info("查询所有店铺类型");
        return typeService.queryList();
    }
}
