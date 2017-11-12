package nh.automation.tools.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import nh.automation.tools.serviceImpl.RunCaseMangeServiceImp;


@Controller
public class RunCaseMangerController {
	
	@Autowired
	private RunCaseMangeServiceImp server;

	/**
	 * 
	 * @param caseId
	 * @param host
	 * 批量执行测试用例
	 */
	@RequestMapping("/runMuchCase")
	@ResponseBody
	public Map<String, String> runMuchCase(int[] caseId,String testEnv,String testText,String header){
		
		Integer id=null;
		//遍历选中的测试用例
		for(int i=0;i<caseId.length;i++){
			id =caseId[i];
			server.runCaseBacth(id, testEnv,testText,header);
			
		}
		
		
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("pass", server.selectPassCase().toString());
		map.put("fail", server.searchFailCase().toString());
		map.put("status", "ok");
		
		return map;
	}
	

}
