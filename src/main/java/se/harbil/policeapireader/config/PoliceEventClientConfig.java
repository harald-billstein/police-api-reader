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
            .clientConnector(new ReactorClientHttpConnector(getInsecureHttpClient()))
            .baseUrl(apiUrl)
            .build();
    }

    /**
     * Returns an HttpClient configured to bypass SSL certificate verification for a specific remote API.
     *
     * <p>This method is used as a temporary workaround for situations where the remote API is experiencing issues
     * with its SSL certificate. InsecureTrustManagerFactory is used to trust all certificates, effectively disabling
     * SSL certificate validation. While this allows the client to connect to the API without verifying the certificate's
     * authenticity, it should be used with caution, as it may expose the client to potential security risks.
     *
     * <p>It is recommended to use this method only as a temporary solution until the remote API provider fixes the
     * certificate issue. Once the certificate issue is resolved, you should revert to a more secure SSL configuration.
     *
     * @return An HttpClient configured to bypass SSL certificate verification.
     * @throws SSLException if an error occurs while building the SSL context.
     */
    private HttpClient getInsecureHttpClient() throws SSLException {
        SslContext sslContext = SslContextBuilder
            .forClient()
            .trustManager(InsecureTrustManagerFactory.INSTANCE)
            .build();
        return HttpClient.create().secure(t -> t.sslContext(sslContext));
    }
}
