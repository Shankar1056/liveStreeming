package apextechies.livestreemingvideo.retrofit;

/**
 * Created by Shankar on 4/23/2019.
 */

public interface DownlodableCallback<T>  {
    void onSuccess(T result);

    void onFailure(String error);
    void onUnauthorized(int errorNumber);
}
