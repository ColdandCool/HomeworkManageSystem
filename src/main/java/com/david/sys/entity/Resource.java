package com.david.sys.entity;

import com.david.sys.entity.enums.ResourceEnum;

/**
 * Resource table
 * 
 * @author David
 */
public class Resource extends DataEntity<Resource> {

	private static final long serialVersionUID = 1L;

	private String name; // name
	private ResourceEnum type = ResourceEnum.menu; // type
	private String icon;//icon
	private String url; // url
	private String permission; // permission
	private String parentId; // parentId
	private Resource resource;//Superior resources
	private String parentIds; // Superior id
	private int sort=0;// sort
	private Boolean available = Boolean.TRUE;//it's usable or not

	public Resource() {
		super();
	}

	public Resource(String id) {
		super(id);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ResourceEnum getType() {
		return type;
	}

	public void setType(ResourceEnum type) {
		this.type = type;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public boolean isRootNode() {
		return parentId.equals("0");
	}

	public String makeSelfAsParentIds() {
		return getParentIds() + getId() + "/";
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}
}
