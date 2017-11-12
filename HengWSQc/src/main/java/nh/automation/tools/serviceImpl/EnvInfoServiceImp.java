package nh.automation.tools.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import nh.automation.tools.common.BeanUtil;
import nh.automation.tools.common.JsonPluginsUtil;
import nh.automation.tools.common.PageResult;
import nh.automation.tools.dao.EnvInfoMapper;
import nh.automation.tools.dto.Result;
import nh.automation.tools.entity.EnvInfo;

@Service
public class EnvInfoServiceImp {
	
	@Autowired
	private EnvInfoMapper mapper;

	/*
	 * @see nh.automation.tools.dao.OutlookMenuMapper#outlookMenus()
	 */
	public List<EnvInfo> getAll() {
		return mapper.getAll();
	}

	public PageResult<EnvInfo> queryByPage(String key, Integer pageNo, Integer pageSize,String sortField, String sortOrder) {
		pageNo = pageNo == null ? 0 : pageNo;
		pageSize = pageSize == null ? 10 : pageSize;
		// startPage是告诉拦截器说我要开始分页了。分页参数是这两个。
		PageHelper.startPage(pageNo+1, pageSize);
		// 用PageInfo对结果进行包装
		return BeanUtil.toPageResult(mapper.selectEnvByText(key));
	}

	/*
	 * @see
	 * nh.automation.tools.service.OutlookMenuService#saveOutlookMenus(java.lang
	 * .String)
	 */
	public Result<Object> save(String jsonString) {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		List<EnvInfo> jsonToBeanList = JsonPluginsUtil.jsonToBeanList(jsonString, EnvInfo.class);
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
				if (mapper.deleteByPrimaryKey(jsonToBeanList.get(i).getId()) > 0) {
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

}
