package apextechies.livestreemingvideo.retrofit;


import apextechies.livestreemingvideo.ui.model.UserModel;
import apextechies.livestreemingvideo.ui.model.VideoModel;
import apextechies.livestreemingvideo.ui.model.CategoryModel;

/**
 * Created by Shankar on 4/23/2019.
 */

public interface ServiceMethods {
    void login(String mobile, DownlodableCallback<UserModel> callback);
    void signup(String name, String email, String mobile, DownlodableCallback<UserModel> callback);
    void video(DownlodableCallback<VideoModel> callback);


}
