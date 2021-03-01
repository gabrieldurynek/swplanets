package com.durynek.swplanets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class SwapiRestApi {
    public List<SwapiPlanet> getPlanetList() {
        // A API retorna resultados em páginas, portanto é necessário cercar a chamada da API
        // em um laço de repetição para acomodar as chamadas das próximas páginas
        String uri = "https://swapi.dev/api/planets/";
        List<SwapiPlanet> planetList = new ArrayList<>();
        while(uri != null) {
            // Caso essa API seja chamada com HTTP, o servidor retorna status 301 e redireciona para
            // a URL com HTTPS. É necessário, portanto, substituir chamadas HTTP por HTTPS.
            uri = uri.replace("http:","https:");
            RestTemplate restTemplate = new RestTemplate();
            String s = restTemplate.getForObject(uri, String.class);
            uri = null;
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            SwapiPlanetList list = null;
            try {
                // Transforma a resposta da chamada em objeto Java, pega a URL da próxima página e
                // coloca os planetas na lista completa
                list = mapper.readValue(s, SwapiPlanetList.class);
                uri = list.getNext();
                planetList.addAll(list.getResults());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return planetList;
    }

    public SwapiPlanet getPlanetByName(String name) {
        List<SwapiPlanet> planetList = getPlanetList();
        return planetList.stream()
                .filter(planetApi -> planetApi.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}