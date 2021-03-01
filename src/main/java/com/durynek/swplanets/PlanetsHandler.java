package com.durynek.swplanets;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Component
public class PlanetsHandler {
    // integração com o BD
    @Autowired private PlanetRepository planetRepository;

    public Mono<ServerResponse> addPlanet(ServerRequest request) {
        // recebe, via PUT, parâmetros no corpo JSON para criar um registro.
        // O mapeamento é feito para a classe Planet e salvo no banco de dados
        final Mono<Planet> planet = request.bodyToMono(Planet.class);

        return planet
                .flatMap(p -> {
                    try {
                        Integer id = planetRepository.save(p).getId();
                        return ServerResponse.ok()
                                .contentType(MediaType.TEXT_PLAIN)
                                .body(BodyInserters.fromValue("Planet with id "+id+" added successfully!"));
                    }
                    catch(DataIntegrityViolationException e) {
                        return ServerResponse.badRequest()
                                .contentType(MediaType.TEXT_PLAIN)
                                .body(BodyInserters.fromValue("Data integrity exception!"));
                    }
                });
    }
    public Mono<ServerResponse> getPlanet(ServerRequest request) {
        // busca planeta por id recebida na URL (método GET)
        String id = request.pathVariable("id");
        try {
            Optional<Planet> planet = planetRepository.findById(Integer.valueOf(id));
            // se planeta existe, retorna JSON com suas prorpiedades. Se não, retorna bad request (400) com mensagem de erro
            return planet
                    .map(value -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromValue(value)))
                    .orElseGet(() -> ServerResponse.status(404).contentType(MediaType.TEXT_PLAIN)
                            .body(BodyInserters.fromValue("Planet doesn't exist")));
        }
        catch (NumberFormatException e) {
            return ServerResponse.badRequest().contentType(MediaType.TEXT_PLAIN)
                    .body(BodyInserters.fromValue("ID should be a number!"));
        }
    }
    public Mono<ServerResponse> removePlanet(ServerRequest request) {
        // busca planeta por id recebida na URL (método DELETE)
        String id = request.pathVariable("id");
        try {
            Optional<Planet> planet = planetRepository.findById(Integer.valueOf(id));
            // se planeta existe, remove o registro. Se não, retorna bad request (400) com mensagem de erro
            if (planet.isPresent()) {
                planetRepository.delete(planet.get());
                return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromValue("Planet removed successfully"));
            } else {
                return ServerResponse.status(404).contentType(MediaType.TEXT_PLAIN)
                        .body(BodyInserters.fromValue("Planet doesn't exist"));
            }
        }
        catch (NumberFormatException e) {
            return ServerResponse.badRequest().contentType(MediaType.TEXT_PLAIN)
                    .body(BodyInserters.fromValue("ID should be a number!"));
        }
    }
    public Mono<ServerResponse> searchPlanet(ServerRequest request) {
        // busca planeta por id recebida na URL (método GET)
        String name = request.pathVariable("name");
        // busca no BD registros com nomes que contenham o parâmetro recebido. A lista pode vir vazia.
        List<Planet> planets = planetRepository.findByNameLike("%"+name+"%");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(planets));
    }
    public Mono<ServerResponse> listPlanets(ServerRequest request) {
        // lista todos os registros do BD
        List<Planet> planets = planetRepository.findAll();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(planets));
    }
    public Mono<ServerResponse> listSwapiPlanets(ServerRequest request) {
        // chama função dentro da classe helper responsável pela API swapi.dev
        SwapiRestApi api = new SwapiRestApi();
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(api.getPlanetList()));
    }
}
