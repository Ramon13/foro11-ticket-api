package br.com.javamoon.integration;

import org.apache.commons.lang3.StringUtils;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import br.com.javamoon.core.util.IOUtils;
import br.com.javamoon.domain.model.Tag;
import br.com.javamoon.domain.model.User;
import br.com.javamoon.domain.repository.TagRepository;
import br.com.javamoon.domain.repository.UserRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class TicketRegisterIT {

	@LocalServerPort
	private int port;
	
	@Autowired
	private Flyway flyway;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TagRepository tagRepository;
	
	private final String BLANK = " ";
	private final String TITLE_EXCEED_MAX_LEN = StringUtils.rightPad("", 128, 'x');
	private final String TITLE_BELOW_MIN_LEN = StringUtils.rightPad("", 7, 'x');
	
	@BeforeEach
	private void setup() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/tickets";
		
		flyway.migrate();
		prepareData();
	}
	
	@Test
	void whenCreateAValidTicket_ThenShouldReturnA201StatusCode() {
		String ticket = IOUtils.getContentFromResource("/json/ticket/ticket-valid.json");
		
		testTicketCreationStatusCode(ticket, HttpStatus.CREATED.value());
	}
	
	@Test
	void whenTitleIsNull_onTicketCreation_ThenShouldReturnA400StatusCode() {
		String ticket = IOUtils.getContentFromResource("/json/ticket/ticket-null-title.json");
		
		testTicketCreationStatusCode(ticket, HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void whenTitleIsBlank_onTicketCreation_ThenShouldReturnA400StatusCode() {
		String ticketFormatter = IOUtils.getContentFromResource("/json/ticket/ticket-formatter-title.json");
		String ticket = String.format(ticketFormatter, BLANK);
		
		testTicketCreationStatusCode(ticket, HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void whenTitleExceedsMaxSize_onTicketCreation_thenShouldReturnA400StatusCode() {
		String ticketFormatter = IOUtils.getContentFromResource("/json/ticket/ticket-formatter-title.json");
		String ticket = String.format(ticketFormatter, TITLE_EXCEED_MAX_LEN);
		
		testTicketCreationStatusCode(ticket, HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	void whenTitleIsBelowMinSize_onTicketCreation_thenShouldReturnA400StatusCode() {
		String ticketFormatter = IOUtils.getContentFromResource("/json/ticket/ticket-formatter-title.json");
		String ticket = String.format(ticketFormatter, TITLE_BELOW_MIN_LEN);
		
		testTicketCreationStatusCode(ticket, HttpStatus.BAD_REQUEST.value());
	}
	
	private ValidatableResponse testTicketCreationStatusCode(String ticket, int expectedStatus) {
		return RestAssured.given()
			.body(ticket)
			.contentType(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(expectedStatus);
	}
	
	private void prepareData() {
		User user = new User();
		user.setName("John Doe");
		userRepository.saveAndFlush(user);
		
		Tag tag = new Tag();
		tag.setName("simple_tag");
		tagRepository.saveAndFlush(tag);
	}
}
 
