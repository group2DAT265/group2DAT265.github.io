package org.jabref.logic.importer.fetcher;

import com.google.gson.JsonPrimitive;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;

public class SemanticScholar {

    private static final String BASE_URL = "https://api.semanticscholar.org/v1/paper/";
    private static final String CITATIONS_KEY = "citations";
    private static final String YEAR_KEY = "year";

    public static HashMap<String, Integer> getOneCitationReportByDOI(String DOI) throws IOException, InterruptedException {
        String urlString = getPaperQuery(DOI);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // TODO: Add error handling
        // TODO: HTTP 429
        JSONObject responseObject = getResponseJSON(response.body());
        JSONArray citationsArray = responseObject.getJSONArray(CITATIONS_KEY);
        return getCitationYears(citationsArray);
    }

    public static HashMap<String, Integer> getCitationReportsByDOIs(ArrayList<String> DOIs) throws IOException, InterruptedException {
        ArrayList<HashMap<String, Integer>> citationReports = new ArrayList<>();
        for (String doIs : DOIs) {
            HashMap<String, Integer> citationReport = getOneCitationReportByDOI(doIs);
            citationReports.add(citationReport);
        }

        return combineReports(citationReports);
    }

    private static String getPaperQuery(String DOI) {
        return SemanticScholar.BASE_URL+DOI;
    }

    private static JSONObject getResponseJSON(String response) {
        return new JSONObject(response);
    }

    private static HashMap<String, Integer> getCitationYears(JSONArray citationsArray) {
        HashMap<String, Integer> citationReport = new HashMap<>();
        for (int i = 0; i < citationsArray.length(); i++) {
            JSONObject obj = citationsArray.getJSONObject(i);

            if (!obj.isNull(YEAR_KEY)) {
                String year = obj.getString(YEAR_KEY);
                Integer numCitations = citationReport.getOrDefault(year, 0);
                citationReport.put(year, (numCitations + 1));
            }
        }
        return citationReport;
    }

    private static HashMap<String, Integer> combineReports(ArrayList<HashMap<String, Integer>> citationReports) {
        HashMap<String, Integer> combinedReports = new HashMap<>();

        for (HashMap<String, Integer> report: citationReports) {
            report.forEach((k, v) -> combinedReports.merge(k, v, Integer::sum));
        }

        return combinedReports;
    }
}
