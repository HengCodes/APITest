package nh.automation.tools.dao;

import nh.automation.tools.entity.WsDataManger;

public interface RunCaseMangerMapper {

	/**
	 * 更新用例的结果executedResult,executedStatus,runTime，reason
	 * @param caseManger
	 * @throws Exception
	 */
	public void updateRunCase(WsDataManger wsData) throws Exception;
	
	/**
	 * 统计测试通过的测试用例
	 * @return
	 * @throws Exception
	 */
	public Integer selectPassCase() throws Exception;
	
	
	/**
	 * 统计Fail的测试用例
	 * @return
	 * @throws Exception
	 */
	public Integer searchFailCase() throws Exception; 
}
