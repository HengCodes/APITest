package nh.automation.tools.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import nh.automation.tools.entity.WsDataManger;

public interface WsDataMangerMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(WsDataManger record);

	WsDataManger searchByPrimaryKey(Integer id);

	int updateByPrimaryKey(WsDataManger record);

	List<WsDataManger> getAll();

	List<WsDataManger> searchAPIByText(@Param("apiName") String apiName);
	
	List<WsDataManger> searchResultByExecutedStatus(@Param("executedStatus") String executedStatus);

	public List<WsDataManger> searchRunCaseResult(WsDataManger record);

	public void deleteCaseRunResult(WsDataManger record);

}
