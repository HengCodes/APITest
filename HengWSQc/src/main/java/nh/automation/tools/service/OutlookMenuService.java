package nh.automation.tools.service;

import java.util.List;

import nh.automation.tools.common.PageResult;
import nh.automation.tools.dto.Result;
import nh.automation.tools.entity.OutlookMenu;

/**
 * 项目 ：UI自动化测试 SSM 类描述：
 * 
 * @author Eric
 * @date 2017年3月9日 nh.automation.tools.ervice
 */

public interface OutlookMenuService {

	public List<OutlookMenu> outlookMenus();

	public PageResult<OutlookMenu> queryByPage(String key, Integer pageNo, Integer pageSize, String sortField,
			String sortOrder);

	public Result<Object> saveOutlookMenus(String dataJson);
}
