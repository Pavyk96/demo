package denis.demo.logging;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class ExternalLogService {

    private final RestClient client = RestClient.create();

    private static final String URL = "https://functions.yandexcloud.net/d4e67v0qar5v0f851onp";

    public void log(String message) {
        client.post()
                .uri(URL)
                .body(Map.of(
                        "level", "INFO",
                        "message", message,
                        "user", "backend",
                        "test", false
                ))
                .retrieve()
                .toBodilessEntity();
    }
}
