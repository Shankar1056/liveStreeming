package apextechies.livestreemingvideo.retrofit;


import apextechies.livestreemingvideo.ui.model.UserModel;
import apextechies.livestreemingvideo.ui.model.VideoModel;
import apextechies.livestreemingvideo.ui.model.CategoryModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Shankar on 4/23/2019.
 */

public interface ApiRetrofitService {

    @POST(ApiUrl.USER_LOGIN)
    @FormUrlEncoded
    Call<UserModel> userLogin(@Field("mobile") String mobile);

    @POST(ApiUrl.USER_SIGNUP)
    @FormUrlEncoded
    Call<UserModel> userSignup(@Field("mobile") String mobile, @Field("name") String name, @Field("email") String email);

    @GET(ApiUrl.VIDEOLIST)
    Call<VideoModel> getVideo();


}
