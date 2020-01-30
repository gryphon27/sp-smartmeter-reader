package com.vivek.tests.scottishpower.spsmartmeterreader.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vivek.tests.scottishpower.spsmartmeterreader.exceptions.NoAccountFoundException;
import com.vivek.tests.scottishpower.spsmartmeterreader.manager.SmartMeterManager;

@RestController
public class SmartMeterController {
	Logger LOG = LoggerFactory.getLogger(SmartMeterController.class);
	@Autowired
	private SmartMeterManager smartMeterManager;

	@GetMapping("/reads/{accountNumber}")
	public ResponseEntity<String> getBook(@PathVariable String accountNumber) {
		ResponseEntity<String> response = null;
		try {
			Thread.currentThread().setName(accountNumber);
			LOG.info("Received reading request for account {}", accountNumber);
			new ObjectMapper().toString();
			response = new ResponseEntity<>(
					new ObjectMapper().writeValueAsString(smartMeterManager.getReadings(accountNumber)), HttpStatus.OK);
			LOG.info("Request completed successfully {}", accountNumber);
		} catch (IllegalArgumentException exe) {
			LOG.info(exe.getLocalizedMessage());
			LOG.error(exe.getLocalizedMessage(), exe);
			response = new ResponseEntity<>(exe.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
		} catch (NoAccountFoundException exe) {
			LOG.info(exe.getLocalizedMessage());
			LOG.error(exe.getLocalizedMessage(), exe);
			response = new ResponseEntity<>(exe.getLocalizedMessage(), HttpStatus.NOT_FOUND);
		} catch (JsonProcessingException exe) {
			LOG.info(exe.getLocalizedMessage());
			LOG.error(exe.getLocalizedMessage(), exe);
			response = new ResponseEntity<>(exe.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			Thread.currentThread().setName("");
		}
		return response;
	}

}
