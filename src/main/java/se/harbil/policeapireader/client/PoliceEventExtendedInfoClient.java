package se.harbil.policeapireader.client;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

/**
 * The PoliceEventExtendedInfoClient class is responsible for making HTTP requests to an external service
 * to retrieve extended information related to police events. It uses the Jsoup library to perform the HTTP request
 * and parse the HTML response into a Document object.
 */
@Component
public class PoliceEventExtendedInfoClient {

    /**
     * Makes an HTTP GET request to retrieve extended information for a specified URL path.
     *
     * @param path The URL path for which extended information is to be fetched.
     * @return A Document object representing the HTML response containing extended information.
     * @throws IOException If there is an error while making the HTTP request or parsing the response.
     */
    public Document call(final String path) throws IOException {
        return Jsoup.connect(path).sslSocketFactory(socketFactory()).get();
    }

    /**
     * Returns an SSLSocketFactory configured to bypass SSL certificate verification for a specific remote domain.
     *
     * <p>This method is used as a temporary workaround for situations where the remote domain is experiencing issues
     * with its SSL certificate. It employs a custom TrustManager that trusts all certificates, effectively disabling
     * SSL certificate validation. While this allows the client to establish SSL connections without verifying the certificate's
     * authenticity, it should be used with caution, as it may expose the client to potential security risks.
     *
     * <p>It is recommended to use this method only as a temporary solution until the remote domain resolves the certificate issue.
     * Once the certificate issue is fixed, it is advisable to revert to a more secure SSL configuration.
     *
     * @return An SSLSocketFactory configured to bypass SSL certificate verification for a specific remote domain.
     * @throws RuntimeException if an error occurs while creating the SSL socket factory.
     */
    private SSLSocketFactory socketFactory() {
        TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] certs, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String authType) {
            }
        }};

        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            return sslContext.getSocketFactory();

        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException("Failed to create a SSL socket factory", e);
        }
    }
}
