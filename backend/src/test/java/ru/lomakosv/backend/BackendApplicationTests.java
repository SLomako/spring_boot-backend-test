package ru.lomakosv.backend;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lomakosv.backend.domain.Author;

import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.with;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BackendApplicationTests {

	private static final String BASE_URI = "http://localhost:8080";
	private static final String BASE_PATH = "/";

	@BeforeAll
	static void setup() {
		RestAssured.baseURI = BASE_URI;
	}

	private final RequestSpecification spec = with()
			.baseUri(BASE_URI)
			.basePath(BASE_PATH);

	@Test
	void homeControllerTest() {
		Author[] authors = spec.get("all")
				.then()
				.statusCode(200)
				.extract()
				.response()
				.as(Author[].class);

		List<String> filteredAuthorsId1 = Stream.of(authors)
				.filter(author -> author.getId().equals("1"))
				.map(Author::toString)
				.toList();

		List<String> filteredAuthorsId2 = Stream.of(authors)
				.filter(author -> author.getId().equals("2"))
				.map(Author::toString)
				.toList();

		assertEquals("[Author(id=1, firstName=Dima, lastName=Дима)]", filteredAuthorsId1.toString());
		assertEquals("[Author(id=2, firstName=noDima, lastName=неДима)]", filteredAuthorsId2.toString());


	}

}
