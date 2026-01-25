package com.meetshop.service.impl;

import cn.hutool.json.JSONUtil;
import com.meetshop.dto.Result;
import com.meetshop.entity.ShopType;
import com.meetshop.mapper.ShopTypeMapper;
import com.meetshop.service.IShopTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.meetshop.utils.RedisConstants.CACHE_SHOP_TYPE_KEY;

@Service
public class ShopTypeServiceImpl extends ServiceImpl<ShopTypeMapper, ShopType> implements IShopTypeService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    /**
     * 查询所有商铺类型
     * @return 商铺类型列表
     */
    @Override
    public Result queryList() {
        Set<String> stringShopTypeList = stringRedisTemplate.opsForZSet().range(CACHE_SHOP_TYPE_KEY, 0, -1);
        if(stringShopTypeList != null && !stringShopTypeList.isEmpty()) {
            List<ShopType> shopTypeList = stringShopTypeList.stream()
                    .map(shopType -> JSONUtil.toBean(shopType, ShopType.class))
                    .toList();
            return Result.ok(shopTypeList);
        }

        List<ShopType> shopTypeList = query().orderByAsc("sort").list();
        if(shopTypeList != null && !shopTypeList.isEmpty()) {
            for(ShopType shopType : shopTypeList) {
                stringRedisTemplate.opsForZSet()
                        .add(CACHE_SHOP_TYPE_KEY, JSONUtil.toJsonStr(shopType), shopType.getSort());
            }
        }
        return Result.ok(shopTypeList);
    }
}
