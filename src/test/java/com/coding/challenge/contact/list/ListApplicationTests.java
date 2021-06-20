package com.coding.challenge.contact.list;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes= ListApplication.class)
@ActiveProfiles("test")
@ComponentScan
class ListApplicationTests {

	@Test
	void contextLoads() {
	}

}
