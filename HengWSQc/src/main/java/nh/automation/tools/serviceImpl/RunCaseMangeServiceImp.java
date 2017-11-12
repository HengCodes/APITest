package nh.automation.tools.serviceImpl;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nh.automation.tools.dao.RunCaseMangerMapper;
import nh.automation.tools.dao.WsDataMangerMapper;
import nh.automation.tools.entity.WsDataManger;
import nh.automation.tools.utils.ConvertUntil;
import nh.automation.tools.utils.HttpClientUtil;
import nh.automation.tools.utils.JsonUntil;
import nh.automation.tools.utils.Response;

@Service
public class RunCaseMangeServiceImp {

	@Autowired
	private RunCaseMangerMapper runCaseMangerMapper;

	@Autowired
	private WsDataMangerMapper wsMapper;

	// @Autowired
	// private EnvInfoMapper envMapper;

	/**
	 * 批量执行测试用例
	 * 
	 * @param id
	 * @param
	 * @return
	 */
	public void runCaseBacth(int id, String env, String host, String header) {
		/**
		 * 1、获取接口地址 2、获取请求方式 3、获取请求参数 4、拼接接口地址 5、发送接口请求 6、获取请求结果 7、校验实际结果与预期结果
		 */
		WsDataManger wsDataManger = selectCaseById(id);
		// 1、获取接口地址
		String apiAdress = wsDataManger.getApiHost();
		// 2、获取请求方式
		String apiType = wsDataManger.getApiType();
		// 3、获取请求参数
		// 3.1获取请求参数前判断是否有依赖，主要处理带cookies或者seeion的场景
		String dependPramater = null;// 依赖字段
		String apiParameter = null;
		if (wsDataManger.getDependStatus() != 0 && wsDataManger.getDepend() != null) {
			dependPramater = getDependPramater(wsDataManger.getDepend(), host, header);
		}
		// 3.1拼接依赖参数
		if (dependPramater != null && apiParameter != null) {
			apiParameter = wsDataManger.getApiParameter() + "&" + dependPramater;
			// 有时jquery保存json格式的数据有异常
			apiParameter = apiParameter.replaceAll("/", ",").replace("postData=", "");
		} else {
			if (apiParameter != null) {
				apiParameter = wsDataManger.getApiParameter();
				apiParameter = apiParameter.replaceAll("/", ",").replace("postData=", "");
			} else {

				apiParameter = wsDataManger.getApiParameter();
			}
		}

		// 4、拼接接口地址
		String url = env + apiAdress;
		System.err.println(url+apiType+ header+ apiParameter);
		// 5、发送接口请求
		long starttime = System.currentTimeMillis();
		Response callResponse = null;
		try {
			callResponse = HttpClientUtil.callResponse(url, apiType, header, apiParameter);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		long endtime = System.currentTimeMillis();

		// 6、获取请求结果
		int expectCode = wsDataManger.getExecutedCode();
		// String expectResult = wsDataManger.getExpectResult();
		float runTime = (float) (endtime - starttime) / 1000;
		// 7、校验实际结果与预期结果
		// 校验分为2步，
		/*
		 * 第一步校验状态码，如果不写预期值则不去比较mongodb，实现快速检测ws正常
		 * 
		 * 第一步校验数据库 根据预期值比较mongoDB
		 **/
		int statusCode = callResponse.getStatusCode();
		String APIResponse = callResponse.getBodyEntity();
		// 首先校验状态码 和实体
		if (statusCode == expectCode) {
			if (APIResponse == null || APIResponse == "") {
				wsDataManger.setExecutedStatus("FAIL");
				wsDataManger.setReason("请求接口失败");
				try {
					runCaseMangerMapper.updateRunCase(wsDataManger);
					return;
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				wsDataManger.setExecutedStatus("PASS");
				wsDataManger.setRunTime(runTime + "s");
				wsDataManger.setRunTimeRequest("Request URL:<br>" + url + "<br>" + "Request PostData：" + apiParameter);
				wsDataManger
						.setRunTimeResponse("ResponseCode：<br>" + statusCode + "<br>" + "ResponseBody：" + APIResponse);

				try {
					runCaseMangerMapper.updateRunCase(wsDataManger);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			wsDataManger.setExecutedStatus("FAIL");
			wsDataManger.setRunTimeRequest("Request URL:<br>" + url + "<br>" + "Request PostData：" + apiParameter);
			wsDataManger.setRunTimeResponse("ResponseCode：<br>" + statusCode + "<br>" + "ResponseBody：" + APIResponse);
			wsDataManger.setReason("返回状态码实际结果与预期结果不匹配：<br>预期结果：" + expectCode + "<br>" + "实际结果：" + statusCode);
			try {
				runCaseMangerMapper.updateRunCase(wsDataManger);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		// 校验API与MongoDB是否相等
		// wsDataManger.setAuthResult(authResult);
		// 删除了

	}

	/**
	 * 根据用例的Id查询测试用例
	 * 
	 * @param caseId
	 * @return
	 */
	public WsDataManger selectCaseById(int caseId) {

		WsDataManger selectCaseById = null;
		try {
			selectCaseById = wsMapper.searchByPrimaryKey(caseId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return selectCaseById;
	}

	/**
	 * 查询测试用例通过的数量
	 * 
	 * @return
	 */
	public Integer selectPassCase() {
		Integer selectPassCase = null;
		try {
			selectPassCase = runCaseMangerMapper.selectPassCase();
			return selectPassCase;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return selectPassCase;
	}

	/**
	 * 查询测试用例执行失败的数量
	 * 
	 * @return
	 */
	public Integer searchFailCase() {
		Integer searchFailCase = null;
		try {
			searchFailCase = runCaseMangerMapper.searchFailCase();
			return searchFailCase;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchFailCase;
	}

	/**
	 * 获取依赖接口的字段，返回依赖的key和value
	 * 
	 * @param depend
	 * @param testLocationIp
	 * @param header
	 * @return
	 */
	public String getDependPramater(String depend, String host, String header) {

		String paramValue = null;
		// 获取依赖的字段
		Map<String, String> dependId = ConvertUntil.getDependId(depend);

		Set<String> keySet = dependId.keySet();

		// 获取key
		for (String keys : keySet) {

			String vals = dependId.get(keys);// 获取值
			Integer key = Integer.valueOf(keys);// 字符串转换成

			WsDataManger wsDataManger = selectCaseById(key);// 获取依赖的接口信息

			// 获取测试地址
			String apiAdress = wsDataManger.getApiHost();
			// 3、获取请求方式
			String apiType = wsDataManger.getApiType();
			// 4、获取请求参数
			String apiParameter = wsDataManger.getApiParameter();
			// 拼接测试地址
			String url = host + apiAdress;

			if (wsDataManger.getDependStatus() != 0 && wsDataManger.getDepend() != null) {

				getDependPramater(wsDataManger.getDepend(), host, header);

			}
			Response callResponse = null;
			try {
				callResponse = HttpClientUtil.callResponse(url, apiType, header, apiParameter);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			// 获取依赖接口的每个key
			String[] split = vals.split("\\,");

			for (String s : split) {

				JsonUntil jsonUntil = new JsonUntil(s);

				paramValue = jsonUntil.getParamValue(callResponse.getBodyEntity()) + paramValue;// 获取参数值

			}

		}

		return paramValue.replaceAll(";", "&").replaceAll("null", "");
	}

}
