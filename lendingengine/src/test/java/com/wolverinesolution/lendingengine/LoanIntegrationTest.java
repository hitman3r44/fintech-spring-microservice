package com.wolverinesolution.lendingengine;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wolverinesolution.lendingengine.application.model.LoanApplicationDTO;
import com.wolverinesolution.lendingengine.application.model.LoanRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles(profiles = "test")
public class LoanIntegrationTest {

    private static final String JOHN = "John";
    private static final Gson GSON = new Gson();

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int serverPort;

    @Test
    public void givenLoanRequestIsMadeLoanApplicationGetsCreated() throws Exception{
        final String baseURL = "http://localhost:"+serverPort+"/loan";
        HttpHeaders httpHeaders = getHttpHeaders();

        HttpEntity<LoanRequest> request = new HttpEntity<>(new LoanRequest(500,365,5.2),httpHeaders);

        testRestTemplate.postForEntity(baseURL+"/request", request,String.class);

        ResponseEntity<String> response = testRestTemplate.exchange(baseURL+"/requests",HttpMethod.GET,
                new HttpEntity(null,getHttpHeaders()),String.class);

        List<LoanApplicationDTO> loanApplicationDTOS = GSON.fromJson(response.getBody(), new TypeToken<List<LoanApplicationDTO>>(){}.getType());
        assertEquals(1,loanApplicationDTOS.size());
        assertEquals(loanApplicationDTOS.get(0).getBorrower().getUsername(), JOHN);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, JOHN);
        return httpHeaders;
    }
}
