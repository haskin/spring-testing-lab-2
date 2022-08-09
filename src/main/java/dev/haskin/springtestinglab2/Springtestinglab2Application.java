package dev.haskin.springtestinglab2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*
 * Problems:
 	- The URL you will need is: http://api.coincap.io/v2/assets/bitcoin - the API
   			is free to use and does not require authentication.
 	x Create an endpoint that uses this API to return the current cost of 1 Bitcoin.
	x  Make sure each method is unit test and integration tested as appropriate.
 */
@SpringBootApplication
public class Springtestinglab2Application {

	public static void main(String[] args) {
		SpringApplication.run(Springtestinglab2Application.class, args);
	}

	@Bean
	RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

}
