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
import nh.automation.tools.entity.OutlookMenu;
import nh.automation.tools.exception.BusinessException;
import nh.automation.tools.serviceImpl.OutlookMenuServiceImp;

/**
 * 项目 ：UI自动化测试 SSM 类描述：
 * 
 * @author Eric
 * @date 2017年3月9日 nh.automation.tools.contorller
 */
@Controller
public class OutlookMenuController {

	@Autowired
	private OutlookMenuServiceImp service;

	@RequestMapping(value = "getMenuJson")
	public @ResponseBody List<OutlookMenu> getAllMenuJson() {
		return service.outlookMenus();
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
	@RequestMapping(value = "getMenuGirdJson")
	public @ResponseBody PageResult<OutlookMenu> getMenuList(@RequestParam(value="key",required=false) String key,
			@RequestParam(value="pageIndex") Integer pageIndex, @RequestParam("pageSize") Integer pageSize,
			@RequestParam("sortField") String sortField, @RequestParam("sortOrder") String sortOrder) throws BusinessException {
		return service.queryByPage(key, pageIndex, pageSize, sortField, sortOrder);
	}

	@RequestMapping(value = "getMenuList")
	public String getMenuList() {
		return "sys/MenuList";
	}

	@ResponseBody
	@RequestMapping(value = "saveMenuList", method = RequestMethod.POST)
	public Result<Object> saveMenuList(@RequestParam("data") String data) {
		return service.saveOutlookMenus(data);
	}
}
