package com.vivek.tests.scottishpower.spsmartmeterreader;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("demo,base")
public class SmartmeterReaderApplicationTests {

	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testGetMeterReadings() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, new HttpHeaders());

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("api/smart/reads/12345"),
				HttpMethod.GET, entity, String.class);

		String expected = "{\"Gas read\":213,\"Electricity read\":2323}";

		JSONAssert.assertEquals(expected, response.getBody(), false);

	}

	private String createURLWithPort(String uri) {
		 return "http://localhost:" + port + uri;
	}

}
