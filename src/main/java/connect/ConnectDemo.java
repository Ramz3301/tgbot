package connect;

import com.google.gson.Gson;
import dto.ReportDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
public class ConnectDemo {

    /**
     * get connect with other MS and get JSON body
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    private static String httpGetRequest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
//                .uri(URI.create("https://4c8f-5-101-22-143.eu.ngrok.io/accountant/reports"))
                .uri(URI.create("https://c54a-84-18-99-236.eu.ngrok.io/accountant/reports"))
                .headers("Accept-Enconding", "gzip, deflate")
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String responseBody = response.body();
        int responseStatusCode = response.statusCode();

        log.info("httpGetRequest: " + responseBody);
        log.info("httpGetRequest status code: " + responseStatusCode);
        return responseBody;
    }


    /**
     * get Report from accountant and parse it to DTO
     * @return
     */
    public ReportDTO getReportDTO() {
        String responseBody;
        try {
            responseBody = httpGetRequest();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Gson gson = new Gson();
        ReportDTO reportDTO = gson.fromJson(responseBody, ReportDTO.class);
        return reportDTO;
    }

}