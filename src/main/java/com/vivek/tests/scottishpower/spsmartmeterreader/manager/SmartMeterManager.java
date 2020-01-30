package com.vivek.tests.scottishpower.spsmartmeterreader.manager;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vivek.tests.scottishpower.spsmartmeterreader.constants.SmartMeterConstants;
import com.vivek.tests.scottishpower.spsmartmeterreader.exceptions.NoAccountFoundException;
import com.vivek.tests.scottishpower.spsmartmeterreader.model.MeterReadings;
import com.vivek.tests.scottishpower.spsmartmeterreader.repository.entity.SmartAccount;
import com.vivek.tests.scottishpower.spsmartmeterreader.service.SmartMeterService;

@Component
public class SmartMeterManager {
	Logger LOG = LoggerFactory.getLogger(SmartMeterManager.class);
	
	@Autowired
	private SmartMeterService smartMeterService;

	public MeterReadings getReadings(final String accountNumber) throws NoAccountFoundException {
		MeterReadings meterReadingsVo = null;
		if (accountNumber != null && StringUtils.isNumeric(accountNumber.trim())) {
			LOG.debug("Getting meter readings for {}",accountNumber);
			meterReadingsVo = copyReadingInVo(smartMeterService.getReadings(Long.valueOf(accountNumber)));
			LOG.debug("Meter readings found for {}",accountNumber);
		}else{
			throw new IllegalArgumentException(SmartMeterConstants.INVALID_ACCOUNT_NUMBER_MSG);
		}
		return meterReadingsVo;
	}

	private MeterReadings copyReadingInVo(final SmartAccount readings) throws NoAccountFoundException {
		MeterReadings readingsVo=null;
		if(readings!=null){
			LOG.debug("Smart account details",readings);
			readingsVo= new MeterReadings();
			readingsVo.setGasReading(readings.getGasSmartRead());
			readingsVo.setElectricityReading(readings.getElecSmartRead());
			LOG.debug("Extracted meter readings",readingsVo);
		}else{
			throw new NoAccountFoundException(SmartMeterConstants.NO_ACCOUNT_FOUND_MSG);
		}
		return readingsVo;
	}
	
	public void setSmartMeterService(SmartMeterService smartMeterService) {
		this.smartMeterService = smartMeterService;
	}
}
