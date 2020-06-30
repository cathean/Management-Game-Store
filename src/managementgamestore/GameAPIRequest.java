package managementgamestore;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Properties;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author ivans
 */
public class GameAPIRequest {
    public static void main(String args[]) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://chicken-coop.p.rapidapi.com/games?title=tomb%20raider")
                .get()
                .addHeader("x-rapidapi-host", "chicken-coop.p.rapidapi.com")
                .addHeader("x-rapidapi-key", "8c48c14b5dmsh18f9df73b7bd045p151b67jsn00d403ecbbc3")
                .build();

        Response response = client.newCall(request).execute();
        String r = response.body().string();
        System.out.println(r);
        
        Gson gson = new Gson();
        
        Properties data = gson.fromJson(response.body(), Properties.class);
        String q = data.getProperty("query");
        
        
        System.out.println(q);
    }
}
