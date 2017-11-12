package nh.automation.tools.emums;

/**
 * 项目 ：UI自动化测试 SSM 类描述：
 * 
 * @author Eric
 * @date 2017年3月12日 nh.automation.tools.emums
 */
public enum AjaxSaveStateEnum {

	SUCCESS(1, "saved success"), NO_NUMBER(0, "not found"), REPEAT_APPOINT(-1, "repeat saved"), INNER_ERROR(-2, "system exception");

	private int state;

	private String stateInfo;

	private AjaxSaveStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static AjaxSaveStateEnum stateOf(int index) {
		for (AjaxSaveStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}