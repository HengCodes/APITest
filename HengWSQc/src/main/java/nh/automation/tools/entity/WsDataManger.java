package nh.automation.tools.entity;
/**
 * 项目 ：UI自动化测试 SSM 类描述：
 * 测试数据管理
 * @author Eric
 * @date 2017年3月11日 nh.automation.tools.common
 */
public class WsDataManger {

	private int caseId;
	private String apiName;
	private String apiHost;
	private String apiType;
	private String apiParameter;
	private String expectResult;
	private String executedResult;
	private String executedStatus;
	private int executedCode;
	private String apiDese;
	private String runTime;
	private String reason;
	private String parameterType;
	private String depend;
	private int dependStatus;
	public String getMongoDbQuery() {
		return mongoDbQuery;
	}

	public void setMongoDbQuery(String mongoDbQuery) {
		this.mongoDbQuery = mongoDbQuery;
	}

	public String getRunTimeRequest() {
		return runTimeRequest;
	}

	public void setRunTimeRequest(String runTimeRequest) {
		this.runTimeRequest = runTimeRequest;
	}

	public String getRunTimeResponse() {
		return runTimeResponse;
	}

	public void setRunTimeResponse(String runTimeResponse) {
		this.runTimeResponse = runTimeResponse;
	}

	public String getMongoDbQueryResult() {
		return mongoDbQueryResult;
	}

	public void setMongoDbQueryResult(String mongoDbQueryResult) {
		this.mongoDbQueryResult = mongoDbQueryResult;
	}

	private String mongoDbQuery;
	private String runTimeRequest;
	private String runTimeResponse;
	private String mongoDbQueryResult;
	

	public int getCaseId() {
		return caseId;
	}

	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiHost() {
		return apiHost;
	}

	public void setApiHost(String apiHost) {
		this.apiHost = apiHost;
	}

	public String getApiParameter() {
		return apiParameter;
	}

	public void setApiParameter(String apiParameter) {
		this.apiParameter = apiParameter;
	}

	public String getExpectResult() {
		return expectResult;
	}

	public void setExpectResult(String expectResult) {
		this.expectResult = expectResult;
	}

	public String getApiDese() {
		return apiDese;
	}

	public void setApiDese(String apiDese) {
		this.apiDese = apiDese;
	}

	public int getExecutedCode() {
		return executedCode;
	}
	
	public void setExecutedCode(int executedCode) {
		this.executedCode = executedCode;
	}
	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getExecutedResult() {
		return executedResult;
	}

	public void setExecutedResult(String executedResult) {
		this.executedResult = executedResult;
	}

	public String getExecutedStatus() {
		return executedStatus;
	}

	public void setExecutedStatus(String executedStatus) {
		this.executedStatus = executedStatus;
	}

	public String getParameterType() {
		return parameterType;
	}

	public void setParameterType(String parameterType) {
		this.parameterType = parameterType;
	}

	public String getDepend() {
		return depend;
	}

	public void setDepend(String depend) {
		this.depend = depend;
	}

	public int getDependStatus() {
		return dependStatus;
	}

	public void setDependStatus(int dependStatus) {
		this.dependStatus = dependStatus;
	}

	@Override
	public String toString() {
		return "CaseManger [caseId=" + caseId + ", apiName=" + apiName + ", apiHost=" + apiHost + ", apiType=" + apiType
				+ ", apiParameter=" + apiParameter + ", expectResult=" + expectResult + ", executedResult=" + executedResult
				+ ", executedResult=" + executedResult+ ", apiDese=" + apiDese + ", runTime=" + runTime + ", reason=" + reason
				+ "]";
	}

}
