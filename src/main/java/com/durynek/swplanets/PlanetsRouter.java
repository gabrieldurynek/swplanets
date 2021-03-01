package com.durynek.swplanets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class PlanetsRouter {
    // classe que implementa a função de roteamento. Na classe PlanetsHandler temos os métodos que processam as requests
    @Bean
    public RouterFunction<ServerResponse> routes(PlanetsHandler planetsHandler) {

        return RouterFunctions
                .route(RequestPredicates.POST("/add").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), planetsHandler::addPlanet)
                .andRoute(RequestPredicates.DELETE("/planet/{id}").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), planetsHandler::removePlanet)
                .andRoute(RequestPredicates.GET("/planet/{id}").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), planetsHandler::getPlanet)
                .andRoute(RequestPredicates.GET("/search/{name}").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), planetsHandler::searchPlanet)
                .andRoute(RequestPredicates.GET("/planets").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), planetsHandler::listPlanets)
                .andRoute(RequestPredicates.GET("/swapiplanets").and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), planetsHandler::listSwapiPlanets);
    }
}