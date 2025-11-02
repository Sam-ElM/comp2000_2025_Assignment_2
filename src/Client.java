import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

public class Client extends Thread {
    private Stage stage;

    public Client(Stage s) { 
        this.stage = s; 
    }

    @Override
    public void run() {
        try {
            var request = HttpRequest.newBuilder(URI.create("http://13.238.167.130/weather")).GET().build();
            var response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofInputStream());

            try (BufferedReader br = new BufferedReader(new InputStreamReader(response.body(), StandardCharsets.UTF_8))) {
                Stream<String> lines = br.lines();

                lines.map(line -> line.trim().split(" "))
                     .filter(parts -> parts.length == 5)
                     .forEach(parts -> {
                         try {
                             String attr = parts[1].toLowerCase(); 
                             int gx = Integer.parseInt(parts[2]);
                             int gy = Integer.parseInt(parts[3]);
                             float val = Float.parseFloat(parts[4]);

                             stage.applyWeatherUpdate(attr, gx, gy, val);

                         } catch (Exception ignored) {}
                     });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
