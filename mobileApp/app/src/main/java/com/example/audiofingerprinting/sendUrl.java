package com.example.audiofingerprinting;
import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class sendUrl {
    private static String backendURL = "http://10.0.2.2:5000/api/addSongToDatabase";
    private static OkHttpClient client = (OkHttpClient) new OkHttpClient();
    private sendUrl(){
    }

    public static void asynchronousPostRequest(String jsonBody){
        RequestBody body = RequestBody.create(jsonBody,MediaType.get("application/json"));

        Request request = new Request.Builder().url(backendURL).post(body).build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try(ResponseBody responseBody = response.body() ){
                    if(!response.isSuccessful()){
                        System.out.println("Unexpected Code"+response);
                        return;
                    }
                    System.out.println("Response: "+ responseBody.string());
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        });
    }
}
