import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GeoNamesAPI {

    public static void main(String[] args) {
        String username = "foo";
        String searchQuery = "Tokyo";

        try {
            JSONObject jsonObject = getGeodata(searchQuery, username);
            // Now you can use jsonObject to access the parsed JSON data
            System.out.println(jsonObject.toString());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static JSONObject getGeodata(String searchQuery) throws IOException, ParseException {
        return getGeodata(searchQuery, 1, "APIUsername");
    }

    public static JSONObject getGeodata(String searchQuery, String username) throws IOException, ParseException {
        return getGeodata(searchQuery, 1, username);
    }

    public static JSONObject getGeodata(String searchQuery, int maxRows, String username)
            throws IOException, ParseException {
        String url = "http://api.geonames.org/searchJSON?q=" + searchQuery + "&maxRows=" + maxRows + "&username="
                + username;
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONParser parser = new JSONParser();

            // Return the first element of the array "geonames" inside the response.
            // yeah, java is very verbose.
            // {"adminCode1":"00","lng":"139.75309","geonameId":1861060,"toponymName":"Japan","countryId":"1861060","fcl":"A","population":126529100,"countryCode":"JP","name":"Japan","fclName":"country, state, region,...","countryName":"Japan","fcodeName":"independent political entity","adminName1":"","lat":"35.68536","fcode":"PCLI"}
            return (JSONObject) (((JSONArray) ((JSONObject) parser.parse(response.toString())).get("geonames"))
                    .iterator()).next();
        } else {
            throw new IOException("GET request failed with response code: " + responseCode);
        }
    }
}
