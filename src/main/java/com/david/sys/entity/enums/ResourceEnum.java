package com.david.sys.entity.enums;

/**
 * Resource Type
 * @author David
 */
public enum ResourceEnum {

	menu("Menu"), form("FormElement");

	private final String info;

	private ResourceEnum(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

}
