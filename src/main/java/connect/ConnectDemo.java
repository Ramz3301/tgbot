package connect;

import com.google.gson.Gson;
import dto.ReportDTO;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConnectDemo {

    public static String httpGetRequest() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .uri(URI.create("https://644c-84-18-97-112.eu.ngrok.io/accountant/reports"))
                .headers("Accept-Enconding", "gzip, deflate")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();
        int responseStatusCode = response.statusCode();

        System.out.println("httpGetRequest: " + responseBody);
        System.out.println("httpGetRequest status code: " + responseStatusCode);
        return responseBody;
    }

    public ReportDTO getReport()  {
        String responseBody = null;
        try {
            responseBody = httpGetRequest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();
        ReportDTO reportDTO = gson.fromJson(responseBody, ReportDTO.class);
        System.out.println(reportDTO);
        return reportDTO;
    }

}