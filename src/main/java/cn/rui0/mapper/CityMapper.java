package cn.rui0.mapper;

import cn.rui0.model.City;
import org.apache.ibatis.annotations.Param;

public interface CityMapper {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
    City findByName(@Param("cityName") String cityName);
}
