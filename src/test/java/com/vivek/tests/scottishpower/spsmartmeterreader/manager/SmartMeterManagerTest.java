package com.vivek.tests.scottishpower.spsmartmeterreader.manager;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.vivek.tests.scottishpower.spsmartmeterreader.exceptions.NoAccountFoundException;
import com.vivek.tests.scottishpower.spsmartmeterreader.model.MeterReadings;
import com.vivek.tests.scottishpower.spsmartmeterreader.repository.entity.SmartAccount;
import com.vivek.tests.scottishpower.spsmartmeterreader.service.SmartMeterService;


public class SmartMeterManagerTest {
	
	static SmartMeterService smartMeterService;
	
	static SmartMeterManager smartMeterManager;
	
	static SmartAccount smartAccount;
	
	@BeforeClass
	public static void setup(){
		smartMeterService = Mockito.mock(SmartMeterService.class);
		smartMeterManager = new SmartMeterManager();
		smartMeterManager.setSmartMeterService(smartMeterService);
		smartAccount = new SmartAccount();
		smartAccount.setAccountNumber(12345L);
		smartAccount.setGasSmartRead((long) 233.23);
		smartAccount.setElecSmartRead((long) 231.23);
	}

	@Test(expected=IllegalArgumentException.class)
	public void getReadings_testInvalidAccountNumber() throws NoAccountFoundException {
		String accountNumber="1234ABC";
		smartMeterManager.getReadings(accountNumber);
		
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void getReadings_testNullAccountNumber() throws NoAccountFoundException {
		smartMeterManager.getReadings(null);
		
	}
	
	@Test
	public void getReadings_testValidNumber() throws NoAccountFoundException {
		Mockito.when(smartMeterService.getReadings(Mockito.any(Long.class))).thenReturn(smartAccount);
		String accountNumber="1234";
		MeterReadings meterReadings=smartMeterManager.getReadings(accountNumber);
		Assert.assertEquals(smartAccount.getElecSmartRead(), meterReadings.getElectricityReading());
		Assert.assertEquals(smartAccount.getGasSmartRead(), meterReadings.getGasReading());
	}
	
	@Test(expected=NoAccountFoundException.class)
	public void getReadings_testNoAccountFoundException() throws NoAccountFoundException
	{
		Mockito.when(smartMeterService.getReadings(Mockito.any(Long.class))).thenReturn(null);
		String accountNumber="1234";
		MeterReadings meterReadings=smartMeterManager.getReadings(accountNumber);
		
	}
	
}
