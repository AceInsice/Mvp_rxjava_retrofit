package yxtk.com.utillibrary.network;




import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo
 * @date 2016/12/09  17:04
 */

public interface ApiService {

    /**
     * 通过 List<MultipartBody.Part> 传入多个part实现多文件上传
     * @param parts 每个part代表一个
     * @return 状态信息
     */
    @Multipart
    @POST
    Observable<ResponseBody> uploadFilesWithParts(@Url String url, @Part() List<MultipartBody.Part> parts);

    /**
     * 通过 MultipartBody和@body作为参数来上传
     * @param multipartBody MultipartBody包含多个Part
     * @return 状态信息
     */
    @POST
    Observable<ResponseBody> uploadFileWithRequestBody(@Url String url, @Body MultipartBody multipartBody);

    //get参数请求
    @GET
    Observable<ResponseBody> getData(@Url String url, @QueryMap Map<String, String> map);
    //post参数请求
    @FormUrlEncoded
    @POST
    Observable<ResponseBody> getDataByPost(@Url String url, @FieldMap Map<String,String> map);
    //单文件图文上传
    @Multipart
    @POST
    Observable<ResponseBody> uploadFileWithPartMap
    (@Url String url, @PartMap() Map<String, RequestBody> partMap, @Part MultipartBody.Part file);

    //单文件图文上传
    @Multipart
    @POST
    Observable<ResponseBody> upload(@Url String url, @Part("description") RequestBody description, @Part MultipartBody.Part file);
    @GET("/mobilesafe/shouji360/360safesis/360MobileSafe_6.2.3.1060.apk")
    Call<ResponseBody> retrofitDownload();
}
