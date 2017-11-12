package nh.automation.tools.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nh.automation.tools.common.BeanUtil;
import nh.automation.tools.common.JsonPluginsUtil;
import nh.automation.tools.common.PageResult;
import nh.automation.tools.dao.WsDataMangerMapper;
import nh.automation.tools.dto.Result;
import nh.automation.tools.entity.WsDataManger;

@Service
public class WsDataMangerServiceImp {

	@Autowired
	private WsDataMangerMapper mapper;

	/*
	 * @see nh.automation.tools.dao.OutlookMenuMapper#outlookMenus()
	 */
	public List<WsDataManger> getAll() {
		return mapper.getAll();
	}

	public PageResult<WsDataManger> queryByPage(String key, Integer pageNo, Integer pageSize, String sortField,
			String sortOrder) {
		pageNo = pageNo == null ? 0 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		// startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		PageHelper.startPage(pageNo + 1, pageSize);
		// 用PageInfo对结果进行包装
		return BeanUtil.toPageResult(mapper.searchAPIByText(key));
	}

	/*
	 * @see
	 * nh.automation.tools.service.OutlookMenuService#saveOutlookMenus(java.lang
	 * .String)
	 */
	public Result<Object> save(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List<WsDataManger> jsonToBeanList = JsonPluginsUtil.jsonToBeanList(jsonString, WsDataManger.class);
		for (int i = 0; i < jsonToBeanList.size(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String id = jsonObject.get("caseId") != null ? jsonObject.get("caseId").toString() : "";
			String state = jsonObject.get("_state") != null ? jsonObject.get("_state").toString() : "";
			if (state.equals("added") || id.equals("")) // 新增：id为空，或_state为added
			{
				if (mapper.insert(jsonToBeanList.get(i)) > 0) {
					return new Result<Object>(true, "保存成功");
				}
			} else if (state.equals("removed") || state.equals("deleted")) {
				if (mapper.deleteByPrimaryKey(jsonToBeanList.get(i).getCaseId()) > 0) {
					return new Result<Object>(true, "删除成功");
				}
			} else if (state.equals("modified") || state.equals("")) // 更新：_state为空，或modified
			{
				if (mapper.updateByPrimaryKey(jsonToBeanList.get(i)) > 0) {
					return new Result<Object>(true, "更新成功");
				}
			} else {
				return new Result<Object>(true, "保存成功");
			}
		}
		return new Result<Object>(false, "提交失败,返回异常,请处理");
	}

	/**
	 * 删除测试用例的结果集
	 */
	public void deleteCaseRunResult(WsDataManger wsDataManger) {

		try {
			mapper.deleteCaseRunResult(wsDataManger);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询含有结果的测试用例
	 * 
	 * @return List<wsDataManger>
	 */
	public List<WsDataManger> searchRunCaseResult(String textName, String textValue, WsDataManger wsDataManger) {
		List<WsDataManger> searchRunCaseResult = new ArrayList<>() ;
		try {
			// 判断是按照接口名称查询，还是按照执行结果查询
			if (textValue != null) {
				if (textName.equals("apiName")) {
					wsDataManger.setApiName(textValue);
				} else {
					wsDataManger.setExecutedStatus(textValue);
				}
			}
			searchRunCaseResult = mapper.searchRunCaseResult(wsDataManger);
			return searchRunCaseResult;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return searchRunCaseResult;
	}

	public WsDataManger getWsDataById(Integer id) {
		return mapper.searchByPrimaryKey(id);
	}
}
