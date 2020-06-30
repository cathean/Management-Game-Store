package managementgamestore;

import java.io.IOException;
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
        System.out.println(response.message());
    }
}
