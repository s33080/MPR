package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import exception.ApiException;
import model.Employee;
import model.Position;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiService {

    public List<Employee> fetchEmployeesFromApi() throws ApiException {

        HttpClient httpClient = HttpClient.newHttpClient(); //Creates HTTP client

        List<Employee> employees = new ArrayList<>(); //List to store employees

        try{
            //Builds GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.employee.com/v1/employees"))
                    .GET()
                    .build();

            // Wysyłamy zapytanie i odbieramy odpowiedź
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Sprawdzamy kod statusu
            if (response.statusCode() == 200) {
                // Parsujemy JSON z odpowiedzi
                Gson gson = new Gson();
                JsonObject post = gson.fromJson(response.body(), JsonObject.class);

                String name = post.get("name").getAsString();
                String email = post.get("email").getAsString();
                String companyName = post.get("company.name").getAsString();
                Position position = Position.PROGRAMMER;



                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("Company Name: " + companyName);
            } else {
                System.out.println("Błąd HTTP: " + response.statusCode());
            }

        }
        catch (Exception e){
            throw new ApiException(e.getMessage());
        }
        return employees;
    }
}
