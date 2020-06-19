package be.vdab.restclient.restclients;

import be.vdab.restclient.dto.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Component
public class DefaultReqResClient implements ReqResClient {
    private final WebClient webClient;
    private final String eenUserURI;

    public DefaultReqResClient(WebClient.Builder builder, @Value("${reqres.eenUser}") String eenUserURI) {
        this.webClient = builder.build();
        this.eenUserURI = eenUserURI;
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            return Optional.of(webClient.get().uri(eenUserURI, uriBuilder
                    -> uriBuilder.build(id))
                    .retrieve().bodyToMono(User.class).block());
        } catch (WebClientResponseException ex) {
            return Optional.empty();
        }
    }
}
