package denis.demo.logging;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class ExternalLogService {

    private final RestClient client = RestClient.create();

    private static final String URL = "https://functions.yandexcloud.net/d4ej5u8bbdnu0cd8dkos";

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
