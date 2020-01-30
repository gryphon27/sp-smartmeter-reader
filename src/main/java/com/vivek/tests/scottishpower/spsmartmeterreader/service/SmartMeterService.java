package com.vivek.tests.scottishpower.spsmartmeterreader.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vivek.tests.scottishpower.spsmartmeterreader.repository.SmartMeterRepository;
import com.vivek.tests.scottishpower.spsmartmeterreader.repository.entity.SmartAccount;

@Service
public class SmartMeterService {
	Logger LOG = LoggerFactory.getLogger(SmartMeterService.class);
	@Autowired
	private SmartMeterRepository smartMeterRepository;
	
	public SmartAccount getReadings(final Long accountNumber) {
		LOG.info("Finding reading for {}",accountNumber);
		Optional<SmartAccount> optionalSmartAccount = smartMeterRepository.findById(accountNumber);
		return optionalSmartAccount.isPresent()?optionalSmartAccount.get():null;
	}

}
