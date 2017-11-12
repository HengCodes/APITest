package nh.automation.tools.dto;

/**
 * 项目    ：UI自动化测试 SSM
 * 类描述：
 * @author Eric
 * @date 2017年3月14日
 * nh.automation.tools.dto
 */
public class Result<T> {

    private boolean success;// 是否成功标志

    private T data;// 成功时返回的数据

    private String message;// 错误信息

    public Result() {
    }

    // 成功时的构造器
    public Result(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    // 错误时的构造器
    public Result(boolean success, String error) {
        this.success = success;
        this.message = error;
    }

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	}

	/**
	 * @return the getMessage
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param  the getMessage to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

  
}