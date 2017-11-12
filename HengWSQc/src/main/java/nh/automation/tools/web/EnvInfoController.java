package nh.automation.tools.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import nh.automation.tools.common.PageResult;
import nh.automation.tools.dto.Result;
import nh.automation.tools.entity.EnvInfo;
import nh.automation.tools.exception.BusinessException;
import nh.automation.tools.serviceImpl.EnvInfoServiceImp;

@Controller
public class EnvInfoController {

	@Autowired
	private EnvInfoServiceImp service;

	@RequestMapping(value = "getEnvInfoJson")
	public @ResponseBody List<EnvInfo> getAllEnvInfoJson() {
		return service.getAll();
	}
	/**
	 * 调试api看看返回结果是否正确
	 * http://localhost:8080/SSM/getMenuGirdJson?pageIndex=2&pageSize=5&sortField=%22%22&sortOrder=%22%22
	 * @param key
	 * @param pageIndex
	 * @param pageSize
	 * @param sortField
	 * @param sortOrder
	 * @return
	 */
	@RequestMapping(value = "getEnvInfoGirdJson")
	public @ResponseBody PageResult<EnvInfo> getEnvInfoList(@RequestParam(value="key",required=false) String key,
			@RequestParam(value="pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("sortField") String sortField, @RequestParam("sortOrder") String sortOrder) throws BusinessException {
		return service.queryByPage(key, pageIndex, pageSize, sortField, sortOrder);
	}

	@RequestMapping(value = "getEnvInfoList")
	public String getEnvInfoList() {
		return "env/envInfo";
	}
	

	@ResponseBody
	@RequestMapping(value = "saveEnvInfoList", method = RequestMethod.POST)
	public Result<Object> saveEnvInfoList(@RequestParam("data") String data) {
		return service.save(data);
	}

}
