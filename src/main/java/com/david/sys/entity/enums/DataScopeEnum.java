package com.david.sys.entity.enums;

/**
 * data range
 * @author David
 */
public enum DataScopeEnum {

	all("All data"),orgFollow("The organization and the following data"), org("Where the organization data"),self("Only my own data");

	private final String info;

	private DataScopeEnum(String info) {
		this.info = info;
	}

	public String getInfo() {
		return info;
	}

}
