package jp.hexachord.dto;

import java.math.BigDecimal;

public class Rate {
	private String dateStr;
	private BigDecimal usd;

	private int year;
	private int month;
	private int day;

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
		String[] date = dateStr.split("/");
		this.year = Integer.valueOf(date[0]);
		this.month = Integer.valueOf(date[1]);
		this.day = Integer.valueOf(date[2]);
	}

	public BigDecimal getUsd() {
		return usd;
	}

	public void setUsd(BigDecimal usd) {
		this.usd = usd;
	}

	public int getYear() {
		return this.year;
	}

	public int getMonth() {
		return this.month;
	}

	public int getDay() {
		return this.day;
	}

	public String getYearMonthStr() {
		return String.format("%d%02d", year, month);
	}
}
