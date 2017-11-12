package nh.automation.tools.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nh.automation.tools.common.PageResult;
import nh.automation.tools.dto.Result;
import nh.automation.tools.entity.WsDataManger;
import nh.automation.tools.exception.BusinessException;
import nh.automation.tools.serviceImpl.WsDataMangerServiceImp;


@Controller
public class WsDataMangerController {

	@Autowired
	private WsDataMangerServiceImp service;
	
	/**
	 * 
	 * @return 
	 * @return返回测试数据管理页面
	 */
	@RequestMapping("/getWsDataCaseJson")
	public @ResponseBody List<WsDataManger> getAllWsDataCaseJson(){
		return service.getAll();
	}
	
	
	@RequestMapping(value = "getWsDataCaseList")
	public String getWsDataCaseList() {
		return "wsDataManger/wsDataCase";
	}

	
	@RequestMapping(value = "getWsDataCaseGirdJson")
	public @ResponseBody PageResult<WsDataManger> getEnvInfoList(@RequestParam(value="key",required=false) String key,
			@RequestParam(value="pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("sortField") String sortField, @RequestParam("sortOrder") String sortOrder) throws BusinessException {
		return service.queryByPage(key, pageIndex, pageSize, sortField, sortOrder);
	}
	
	@ResponseBody
	@RequestMapping(value = "saveWsDataCaseList", method = RequestMethod.POST)
	public Result<Object> saveWsDataCaseList(@RequestParam("data") String data) {
		return service.save(data);
	}

	/**
	 * 
	 * @return 执行结果页面
	 */
	@RequestMapping("/getWsDataCaseResult")
	public String getWsDataCaseResult(){
		return "wsDataManger/wsDataCaseResult";
	}


	

	/**
	 * @return
	 * 删除测试用例的结果集
	 */
	@RequestMapping("/deleteCaseRunResult")
	@ResponseBody
	public Map<String,String> deleteCaseRunResult(WsDataManger caseManger){
		
		Map<String, String> map = new HashMap<String, String>();
		try {
			service.deleteCaseRunResult(caseManger);
			map.put("status", "ok");
		} catch (Exception e) {
			
			e.printStackTrace();
		}		
		return map;
		
	}
	
	/**
	 * 执行测试报告页面，只显示已执行的测试用例	 
	 * @param textName 
	 * @param textValue
	 * @return List<CaseManger>
	 */
	@RequestMapping("/getRunCaseResult")
	@ResponseBody
	public List<WsDataManger> searchRunCaseResult(String textName,@RequestParam(value="key",required=false) String textValue,WsDataManger caseManger){
		List<WsDataManger> getRunCaseResult = service.searchRunCaseResult(textName,textValue,caseManger);
		return getRunCaseResult;
	}
	
	@RequestMapping("/getRunCaseLog")
	public String getRunCaseLog() {
		return "wsDataManger/caseRunlog";
	}
	
	
	@RequestMapping("/getRunCaseLog/{id}")
	@ResponseBody 
	public WsDataManger getRunCaseLogById(@PathVariable("id") Integer id) {
		return service.getWsDataById(id);
	}
	
	
}
