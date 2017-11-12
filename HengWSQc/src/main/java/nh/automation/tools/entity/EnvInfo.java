package nh.automation.tools.entity;

/**
 * 项目 ：UI自动化测试 SSM 类描述：
 * 环境信息
 * @author Eric
 * @date 2017年3月11日 nh.automation.tools.common
 */
public class EnvInfo {
	
	private int id;
	private String envName;
	private String address;
	private String mongoDbHost;
	private String mongoDbPort;
	private String mongoDbUser;
	private String mongoDbPwd;
	

	@Override
	public String toString() {
		return "EnvInfo [id=" + id + ", envName=" + envName + ", address=" + address + ", mongoDbHost=" + mongoDbHost
				+ ", mongoDbPort=" + mongoDbPort + ", mongoDbUser=" + mongoDbUser + ", mongoDbPwd=" + mongoDbPwd + "]";
	}

	public String getMongoDbHost() {
		return mongoDbHost;
	}

	public void setMongoDbHost(String mongoDbHost) {
		this.mongoDbHost = mongoDbHost;
	}

	public String getMongoDbPort() {
		return mongoDbPort;
	}

	public void setMongoDbPort(String mongoDbPort) {
		this.mongoDbPort = mongoDbPort;
	}

	public String getMongoDbUser() {
		return mongoDbUser;
	}

	public void setMongoDbUser(String mongoDbUser) {
		this.mongoDbUser = mongoDbUser;
	}

	public String getMongoDbPwd() {
		return mongoDbPwd;
	}

	public void setMongoDbPwd(String mongoDbPwd) {
		this.mongoDbPwd = mongoDbPwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnvName() {
		return envName;
	}

	public void setEnvName(String envName) {
		this.envName = envName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
