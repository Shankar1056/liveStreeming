package apextechies.livestreemingvideo.retrofit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import apextechies.livestreemingvideo.ui.model.UserModel;
import apextechies.livestreemingvideo.ui.model.VideoModel;
import apextechies.livestreemingvideo.ui.model.CategoryModel;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Shankar on 4/23/2019.
 */

public class RetrofitDataProvider extends AppCompatActivity implements ServiceMethods {
    Context context;

    private ApiRetrofitService createRetrofitService() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUrl.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiRetrofitService.class);
    }

    public RetrofitDataProvider(Context context) {
        this.context = context;
    }

    @Override
    public void login(String mobile, final DownlodableCallback<UserModel> callback) {
        createRetrofitService().userLogin(mobile).enqueue(
                new Callback<UserModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UserModel> call, @NonNull final Response<UserModel> response) {
                        if (response.isSuccessful()) {
                            UserModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void signup(String name, String email, String mobile, final DownlodableCallback<UserModel> callback) {
        createRetrofitService().userSignup(name, email, mobile).enqueue(
                new Callback<UserModel>() {
                    @Override
                    public void onResponse(@NonNull Call<UserModel> call, @NonNull final Response<UserModel> response) {
                        if (response.isSuccessful()) {
                            UserModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<UserModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void category(final DownlodableCallback<CategoryModel> callback) {
        createRetrofitService().getCategory().enqueue(
                new Callback<CategoryModel>() {
                    @Override
                    public void onResponse(@NonNull Call<CategoryModel> call, @NonNull final Response<CategoryModel> response) {
                        if (response.isSuccessful()) {
                            CategoryModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CategoryModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }

    @Override
    public void video(final DownlodableCallback<VideoModel> callback) {
        createRetrofitService().getVideo().enqueue(
                new Callback<VideoModel>() {
                    @Override
                    public void onResponse(@NonNull Call<VideoModel> call, @NonNull final Response<VideoModel> response) {
                        if (response.isSuccessful()) {
                            VideoModel mobileRegisterPojo = response.body();
                            callback.onSuccess(mobileRegisterPojo);

                        } else {
                            if (response.code() == 401) {
                                callback.onUnauthorized(response.code());
                            } else {
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<VideoModel> call, @NonNull Throwable t) {
                        Log.d("Result", "t" + t.getMessage());
                        callback.onFailure(t.getMessage());
                    }
                }
        );
    }


}