package demo.graphql;

import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.inject.Inject;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MicronautTest
class DemoMicronautGraphqlTest {

    @Inject
    EmbeddedApplication<?> application;

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    void should_response_success_when_result_request_is_ok() {
        String query = "{ \"query\": \"{categoriaInvestimentoBySigla(sigla:\\\"CDB\\\"){sigla, descricao, investimentosEntity{ tiposInvestimentos } } } \"}";

        HttpRequest<String> request = HttpRequest.POST("/graphql", query);
        HttpResponse<Map> response = client.toBlocking().exchange(request, Argument.of(Map.class));
        assertEquals(HttpStatus.OK, response.status());
        assertNotNull(response.body());

        Map categoriaInvestimento = (Map) response.getBody(Map.class).get().get("data");
        Map categoriaInvestimentoSigla = (Map) categoriaInvestimento.get("categoriaInvestimentoBySigla");
        assertEquals("CDB", categoriaInvestimentoSigla.get("sigla"));
    }
}
