package tn.esprit.spring.configuration;

import com.google.ads.googleads.lib.GoogleAdsClient;
import com.google.api.client.util.Value;
import com.google.auth.Credentials;
import com.google.auth.oauth2.UserCredentials;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class GoogleAdsConfig {

    @Value("${google.ads.developerToken}")
    private String developerToken;

    @Value("${google.ads.clientId}")
    private String clientId;

    @Value("${google.ads.clientSecret}")
    private String clientSecret;

    @Value("${google.ads.refreshToken}")
    private String refreshToken;

    @Value("${google.ads.loginCustomerId}")
    private Long loginCustomerId;

    @Bean
    public GoogleAdsClient googleAdsClient() throws IOException {
        Object HttpTransportUtils;
        return GoogleAdsClient.newBuilder()
                .setCredentials(createCredentials())
                .setDeveloperToken(developerToken)
                .setLoginCustomerId(loginCustomerId)
                .setLoggerFactory(LoggerFactory.getLogger("GoogleAdsLogger"))
                .setTransport(HttpTransportUtils.createHttpClientTransport())
                .build();
    }

    private Credentials createCredentials() throws IOException {
        return new UserCredentials(clientId, clientSecret, refreshToken);
    }
}