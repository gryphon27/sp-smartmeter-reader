package com.vivek.tests.scottishpower.spsmartmeterreader.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MeterReadings {

	@JsonProperty("Gas read")
	private Long gasReading;
	@JsonProperty("Electricity read")
	private Long electricityReading;
	public Long getGasReading() {
		return gasReading;
	}
	public void setGasReading(Long gasReading) {
		this.gasReading = gasReading;
	}
	public Long getElectricityReading() {
		return electricityReading;
	}
	public void setElectricityReading(Long electricityReading) {
		this.electricityReading = electricityReading;
	}
	@Override
	public String toString() {
		return "MeterReadings [gasReading=" + gasReading + ", electricityReading=" + electricityReading + "]";
	}
	
}
