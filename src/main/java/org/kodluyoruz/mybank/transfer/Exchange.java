package org.kodluyoruz.mybank.transfer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Component
public class Exchange implements CommandLineRunner {

    private final RestTemplate restTemplate;

    public Exchange(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.rootUri("https://api.exchangeratesapi.io/latest?base=TRY").build();
    }


    @Override
    public void run(String... args) throws Exception {

        HashMap<String, Double> map = new HashMap<>();

    }
}
