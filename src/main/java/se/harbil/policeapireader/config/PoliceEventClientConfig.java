package se.harbil.policeapireader.config;

import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import javax.net.ssl.SSLException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

/**
 * The PoliceEventClientConfig class is a Spring configuration class responsible for creating instances of
 * WebClient for interacting with the police event API and its extended information API.
 */
@Configuration
@RequiredArgsConstructor
public class PoliceEventClientConfig {

    @Value("${POLICE_API_URL}")
    private final String apiUrl;
    @Value("${POLICE_EXTENDED_BASE_URL}")
    private final String extendedInfoBaseUrl;

    /**
     * Creates and configures a WebClient bean for interacting with the police event API.
     *
     * @return An instance of WebClient configured with the base URL for the police event API.
     */
    @Bean
    @Qualifier("policeEventClient")
    @SneakyThrows
    public WebClient createPoliceEventClient() {
        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
            .baseUrl(apiUrl)
            .build();
    }

    /**
     * Creates and configures a WebClient bean for interacting with the extended information API related to police events.
     *
     * @return An instance of WebClient configured with the base URL for the extended information API.
     */
    @Bean
    @Qualifier("policeEventExtendedInfoClient")
    @SneakyThrows
    public WebClient createPoliceEventExtendedInfoClient() {
        return WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(getHttpClient()))
            .baseUrl(extendedInfoBaseUrl)
            .build();
    }

    // TODO polisen.se cert Grade B after update. REMOVE when they fix
    private HttpClient getHttpClient() throws SSLException {
        SslContext sslContext = SslContextBuilder
            .forClient()
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
            .build();
        return HttpClient.create().secure(t -> t.sslContext(sslContext));
    }
}
