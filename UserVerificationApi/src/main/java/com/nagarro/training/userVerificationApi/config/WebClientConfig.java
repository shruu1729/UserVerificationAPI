package com.nagarro.training.userVerificationApi.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
/*
 * @author shreyarathour
 * Configuration class for creating and customizing WebClients used to communicate with external APIs.
 */
@Configuration
public class WebClientConfig {

    /**
     * Configures and creates a WebClient for communicating with the Random User API.
     *
     * @return WebClient for the Random User API
     */
    @Bean(name = "randomUserApiWebClient")
    WebClient getRandomUserWebClient() {
    	// Configuring the HttpClient with specific options and timeouts
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                .doOnConnected(conn -> conn
                        .addHandlerFirst(new ReadTimeoutHandler(2000, TimeUnit.SECONDS))
                        .addHandlerFirst(new WriteTimeoutHandler(2000, TimeUnit.SECONDS)));
     // Creating a ReactorClientHttpConnector with the configured HttpClient
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient.wiretap(true));
     // Building the WebClient with the configured connector, base URL, and default headers
        return WebClient.builder()
                .clientConnector(connector)
                .baseUrl("https://randomuser.me/api/") 
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Media-Type", "application/json") // Separate multiple headers with different names
                .build();
    }
    /**
     * Configures and creates a WebClient for communicating with the Nationality API.
     *
     * @return WebClient for the Nationality API
     */
    @Bean(name = "NationalityApiWebClient")
    WebClient getNationalityWebClient() {
    	// Configuring the HttpClient with specific options and timeouts
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .doOnConnected(conn -> conn
                        .addHandlerFirst(new ReadTimeoutHandler(2000, TimeUnit.SECONDS))
                        .addHandlerFirst(new WriteTimeoutHandler(2000, TimeUnit.SECONDS)));
        // Creating a ReactorClientHttpConnector with the configured HttpClient
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient.wiretap(true));
     // Building the WebClient with the configured connector, base URL, and default headers
        return WebClient.builder()
                .clientConnector(connector)
                .baseUrl("https://api.nationalize.io/")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Media-Type", "application/json") // Separate multiple headers with different names
                .build();
    }
    /**
     * Configures and creates a WebClient for communicating with the Gender API.
     *
     * @return WebClient for the Gender API
     */
    @Bean(name = "GenderApiWebClient")
    WebClient getGenderWebClient() {
    	 // Configuring the HttpClient with specific options and timeouts
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 1000)
                .doOnConnected(conn -> conn
                        .addHandlerFirst(new ReadTimeoutHandler(2000, TimeUnit.SECONDS))
                        .addHandlerFirst(new WriteTimeoutHandler(2000, TimeUnit.SECONDS)));
     // Creating a ReactorClientHttpConnector with the configured HttpClient
        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient.wiretap(true));
        // Building the WebClient with the configured connector, base URL, and default headers
        return WebClient.builder()
                .clientConnector(connector)
                .baseUrl("https://api.genderize.io/")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Media-Type", "application/json") // Separate multiple headers with different names
                .build();
    }
}

