package nz.co.stuxo.app.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import nz.co.stuxo.app.data.model.Ribot;
import nz.co.stuxo.app.util.MyGsonTypeAdapterFactory;

public interface RoonHttpService {

    String ENDPOINT = "http://192.168.15.14:3001/roonAPI/";

    @GET("play_pause")
    Observable<ResponseBody> playPause(@Query("zoneId") String zoneId);


    /******** Helper class that sets up a new services *******/
    class Creator {

        public static RoonHttpService newRibotsService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RoonHttpService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RoonHttpService.class);
        }
    }
}
