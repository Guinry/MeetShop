package com.meetshop.service;

import com.meetshop.dto.Result;
import com.meetshop.entity.ShopType;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IShopTypeService extends IService<ShopType> {
    /**
     * 查询所有商铺类型
     * @return 商铺类型列表
     */
    Result queryList();
}
