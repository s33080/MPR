package service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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

    public List<Employee> fetchEmployeesFromApi(String url) throws ApiException {

        HttpClient httpClient = HttpClient.newHttpClient(); //Creates HTTP client

        List<Employee> employees = new ArrayList<>(); //List to store employees

        try{
            //Builds GET request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            // Wysyłamy zapytanie i odbieramy odpowiedź
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Sprawdzamy kod statusu
            if (response.statusCode() == 200) {

                Gson gson = new Gson();

                JsonArray array = gson.fromJson(response.body(), JsonArray.class); // array of JSON objects (employees)

                for (JsonElement element : array) { //for each element in that array
                    JsonObject object = element.getAsJsonObject(); //treat it as object
                    String [] name = object.get("name").getAsString().split(" ");
                    String firstName = name[0];
                    String surname = name[1];
                    String email = object.get("email").getAsString();
//                    String companyName = object.get("company.name").getAsString();
                    JsonObject companyObject = object.getAsJsonObject("company"); //company is an object within the JSON, so we must get it out
                    String companyName = companyObject.get("name").getAsString();

                    Employee employee = Employee.Builder.newInstance()
                            .setName(firstName)
                            .setSurname(surname)
                            .setEmail(email)
                            .setCompanyName(companyName)
                            .setPosition(Position.PROGRAMMER)
                            .setSalary(Position.PROGRAMMER.getDefaultSalary())
                            .build();

                    employees.add(employee);
                }
            }
            else {
                System.out.println("Błąd HTTP: " + response.statusCode());
            }

        }
        catch (Exception e){
            throw new ApiException(e.getMessage());
        }
        return employees;
    }
}
