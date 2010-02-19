package com.synitex.sample.client;

import java.io.Serializable;

public class TableRowData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private Integer maxValue;
	private Integer minValue;

	public TableRowData() {
		// TODO Auto-generated constructor stub
	}

	public TableRowData(String name, Integer minValue, Integer maxValue) {
		this.name = name;
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	public Integer getMinValue() {
		return minValue;
	}

	public void setMinValue(Integer minValue) {
		this.minValue = minValue;
	}

}
