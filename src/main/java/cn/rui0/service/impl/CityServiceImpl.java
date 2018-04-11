package cn.rui0.service.impl;

import cn.rui0.mapper.CityMapper;
import cn.rui0.model.City;
import cn.rui0.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 城市业务逻辑实现类
 *
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    public City findCityByName(String cityName) {
        return cityMapper.findByName(cityName);
    }

}
