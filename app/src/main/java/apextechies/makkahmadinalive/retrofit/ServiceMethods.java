package apextechies.makkahmadinalive.retrofit;


import apextechies.makkahmadinalive.ui.model.UserModel;
import apextechies.makkahmadinalive.ui.model.VideoModel;

/**
 * Created by Shankar on 4/23/2019.
 */

public interface ServiceMethods {
    void login(String mobile, DownlodableCallback<UserModel> callback);
    void signup(String name, String email, String mobile, DownlodableCallback<UserModel> callback);
    void video(DownlodableCallback<VideoModel> callback);


}
