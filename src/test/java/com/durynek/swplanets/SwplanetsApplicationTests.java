package com.durynek.swplanets;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
class SwplanetsApplicationTests {
//	@Autowired
//	private WebTestClient webClient;
//	@Test
//	void testAddPlanet() {
//		Planet p = new Planet();
//		p.setName("Tatooine");
//		p.setClimate("hot");
//		p.setTerrain("unknown");
//		webClient.post().uri("/add", p)
//				.header(HttpHeaders.ACCEPT, "application/json")
//				.exchange()
//				.expectStatus().isOk();
//	}

}
