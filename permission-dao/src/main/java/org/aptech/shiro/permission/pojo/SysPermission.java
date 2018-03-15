package org.aptech.shiro.permission.pojo; 
import java.io.Serializable;
import java.util.List; 
/**
 * 权限实体类
 * @author Miao  Long
 *
 */
public class SysPermission implements Serializable {
	
	private Integer id;
	
	private String text;
	
	private String type; 
	
	private String url;
	
	private String percode;
	
	private Integer parentid;
	 
	private String parentids;
	
	private Integer available = 1;
	
	private List<SysPermission> children;

	
	
	public List<SysPermission> getChildren() {
		return children;
	}

	public void setChildren(List<SysPermission> children) {
		this.children = children;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	 
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPercode() {
		return percode;
	}

	public void setPercode(String percode) {
		this.percode = percode;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getParentids() {
		return parentids;
	}

	public void setParentids(String parentids) {
		this.parentids = parentids;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "SysPermission [id=" + id + ", name=" + text + ", type=" + type + ", url=" + url + ", percode=" + percode
				+ ", parentid=" + parentid + ", parentids=" + parentids + ", available=" + available + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	 
	
}
