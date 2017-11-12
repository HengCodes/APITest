package nh.automation.tools.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import nh.automation.tools.entity.EnvInfo;



public interface EnvInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EnvInfo record);

    int insertSelective(EnvInfo record);

    EnvInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EnvInfo record);

    int updateByPrimaryKey(EnvInfo record);
    
    List<EnvInfo> getAll();
    
    List<EnvInfo> selectEnvByText(@Param("envName") String envName);

}
