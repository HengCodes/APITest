package nh.automation.tools.common;

import java.util.List;

/**
 * 项目    ：UI自动化测试 SSM
 * 类描述：
 * @author Eric
 * @date 2017年3月11日
 * nh.automation.tools.common
 */
public class PageResult<T> extends BaseEntity {  
      
    /*serialVersionUID*/  
    private static final long serialVersionUID = 1L;  
  
    private List<T> data;//数据  
      
    private long pageNo;//当前页  
      
    private long pageSize;//条数  
      
    private long total;//总条数  
      
    private long pages;//总页面数目  
  
    public List<T> getData() {  
        return data;  
    }  
  
    public void setData(List<T> data) {  
        this.data = data;  
    }  
  
    public long getPageNo() {  
        return pageNo;  
    }  
  
    public void setPageNo(long pageNo) {  
        this.pageNo = pageNo;  
    }  
  
    public long getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(long pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public long getTotal() {  
        return total;  
    }  
  
    public void setTotal(long total) {  
        this.total = total;  
    }  
  
    public long getPages() {  
        return pages;  
    }  
  
    public void setPages(long pages) {  
        this.pages = pages;  
    }  
      
}  