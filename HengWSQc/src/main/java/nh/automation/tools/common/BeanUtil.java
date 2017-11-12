package nh.automation.tools.common;

import java.util.List;

import com.github.pagehelper.Page;

/**
 * 项目 ：UI自动化测试 SSM 类描述：
 * 
 * @author Eric
 * @date 2017年3月11日 nh.automation.tools.common
 */
public class BeanUtil {

	public static <T> PageResult<T> toPageResult(List<T> datas) {
		PageResult<T> result = new PageResult<T>();
		if (datas instanceof Page) {
			Page<T> page = (Page<T>) datas;
			result.setPageNo(page.getPageNum());
			result.setPageSize(page.getPageSize());
			result.setData(page.getResult());
			result.setTotal(page.getTotal());
			result.setPages(page.getPages());
		} else {
			result.setPageNo(1);
			result.setPageSize(datas.size());
			result.setData(datas);
			result.setTotal(datas.size());
		}
		return result;
	}

}