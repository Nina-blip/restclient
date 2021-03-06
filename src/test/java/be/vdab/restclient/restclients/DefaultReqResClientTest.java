package be.vdab.restclient.restclients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class DefaultReqResClientTest {
    private final DefaultReqResClient client;

    public DefaultReqResClientTest(DefaultReqResClient client) {
        this.client = client;
    }

    @Test
    void vindBestaandeUser(){
        assertThat(client.findById(1).get().getData().getId()).isOne();
    }

    @Test
    void vindOnbestaandeUser(){
        assertThat(client.findById(-1).isEmpty());
    }
}