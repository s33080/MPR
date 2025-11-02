import java.net.URI;
import java.net.http.*;
import com.google.gson.*;
import java.io.IOException;

public static void fetchFromAPI() throws IOException, InterruptedException {
    // Tworzymy klienta HTTP
    HttpClient client = HttpClient.newHttpClient();


    // Budujemy zapytanie GET
    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://jsonplaceholder.typicode.com/posts/1"))
            .GET()
            .build();

    // Wysyłamy zapytanie i odbieramy odpowiedź
    HttpResponse<String> response = client.send(request,
            HttpResponse.BodyHandlers.ofString());

    // Sprawdzamy kod statusu
    if (response.statusCode() == 200) {
        // Parsujemy JSON z odpowiedzi
        Gson gson = new Gson();
        JsonObject post = gson.fromJson(response.body(), JsonObject.class);

        int id = post.get("id").getAsInt();
        String title = post.get("title").getAsString();
        String body = post.get("body").getAsString();

        System.out.println("ID: " + id);
        System.out.println("Tytuł: " + title);
        System.out.println("Treść: " + body);
    } else {
        System.out.println("Błąd HTTP: " + response.statusCode());
    }
}

public void main() throws IOException, InterruptedException {
    fetchFromAPI();
}