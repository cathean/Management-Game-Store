package managementgamestore;
/**
 *
 * @author ivans
 */

// The source code from OkHttp Recipe

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

public final class GameAPIRequest {
    final static String rapidHost = "chicken-coop.p.rapidapi.com";
    final static String rapidKey = "8c48c14b5dmsh18f9df73b7bd045p151b67jsn00d403ecbbc3";
    
    public static JsonArray listSearchGame;
    public static ArrayList<JsonObject> infoGameResults = new ArrayList<>();
    
    public void getGameSearch(String title) throws IOException {
        String gameSearchBodyResponse = "";
        Request request = new Request.Builder()
            .url("https://chicken-coop.p.rapidapi.com/games?title=" + title)
            .get()
            .addHeader("x-rapidapi-host", rapidHost)
            .addHeader("x-rapidapi-key", rapidKey)
            .build();
        
        // GET connection validation
        try (Response response = new OkHttpClient().newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response.code());
            
            // Store the Json in string variable
            gameSearchBodyResponse = response.body().string();
        }
        
        // Store all the searched game information
        JsonObject jsonObj = new JsonParser().parse(gameSearchBodyResponse).getAsJsonObject();
        listSearchGame = jsonObj.getAsJsonArray("result");
        
        System.out.println(gameSearchBodyResponse);
    }
    
    public void run(String title, String platform) throws Exception {
        Gson gson = new Gson();
        String gameInfoBodyResponse = "";
        Request request = new Request.Builder()
            .url("https://chicken-coop.p.rapidapi.com/games/" + title + "?platform=" + platform)
            .get()
            .addHeader("x-rapidapi-host", rapidHost)
            .addHeader("x-rapidapi-key", rapidKey)
            .build();
        
        
        final ProgressListener progressListener = new ProgressListener() {
            boolean firstUpdate = true;
            
            @Override public void update(long bytesRead, long contentLength, boolean done) {
                if (done) {
                    System.out.println("completed");
                } else {
                    if (firstUpdate) {
                        firstUpdate = false;
                        if (contentLength == -1) {
                            System.out.println("content-length: unknown");
                        } else {
                            System.out.format("content-length: %d\n", contentLength);
                        }
                    }

                    System.out.println(bytesRead);
                    
                    // Progress value
                    if (contentLength != -1) {
                        System.out.format("%d%% done\n", (100 * bytesRead) / contentLength);
                    }
                }
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
            .addNetworkInterceptor(chain -> {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
            })
            .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            
            // Store the Json in string variable
            gameInfoBodyResponse = response.body().string();
            System.out.println(gameInfoBodyResponse);
        }
        
        // Convert the Json into Json Object Class
        infoGameResults.add(new JsonParser().parse(gameInfoBodyResponse).getAsJsonObject());
        
        if(infoGameResults.get(infoGameResults.size() - 1).get("result").isJsonObject())
            System.out.println(infoGameResults.get(infoGameResults.size() - 1).getAsJsonObject("result").get("title").getAsString());
        
    }
    
    public static void doClean() {
        listSearchGame = null;
        infoGameResults.clear();
    }
    
    public static void doSearch(String title) throws Exception {
        new GameAPIRequest().getGameSearch(title);
        String platform = "";
        for(int i = 1; i < listSearchGame.size(); i++)
        {
            System.out.println();
            
            switch(listSearchGame.get(i).getAsJsonObject().get("platform").getAsString()) {
                case "PC" : platform = "pc";
                            break;
                case "PS3" : platform = "playstation-3";
                            break;
                case "PS4" : platform = "playstation-4";
                            break;
                case "Switch" : platform = "switch";
                            break;
                case "iOS" : platform = "ios";
                            break;
                case "X360" : platform = "xbox";
                            break;
                default:    platform = listSearchGame.get(i).getAsJsonObject().get("platform").getAsString();
                            
            }
            
            new GameAPIRequest().run(listSearchGame.get(i).getAsJsonObject().get("title").getAsString(), platform);
        }
    }

    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final ProgressListener progressListener;
        private BufferedSource bufferedSource;

        ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override public long contentLength() {
            return responseBody.contentLength();
        }

        @Override public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
                    return bytesRead;
                }
            };
        }
    }

    interface ProgressListener {
        void update(long bytesRead, long contentLength, boolean done);
    }
}
