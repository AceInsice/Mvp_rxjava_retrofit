package yxtk.com.utillibrary.network;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author nanchen
 * @fileName RetrofitRxDemoo
 * @packageName com.nanchen.retrofitrxdemoo.util
 * @date 2016/12/12  10:38
 */

public class RetrofitUtil{

    public static final int DEFAULT_TIMEOUT = 5;
    private Retrofit mRetrofit;
    private static RetrofitUtil mInstance;
    private ApiService mApiService;

    /**
     * 私有构造方法
     */
    private RetrofitUtil(String baseUrl){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        mApiService = mRetrofit.create(ApiService.class);
    }

    public static RetrofitUtil getInstance(String baseUrl){
        if (mInstance == null){
            synchronized (RetrofitUtil.class){
                mInstance = new RetrofitUtil(baseUrl);
            }
        }
        return mInstance;
    }

    /**
     * get参数请求
     * @param subscriber
     */
    public void getData(String url, Map params, Observer<ResponseBody> subscriber){
        toSubscribe(mApiService.getData(url,params),subscriber);
    }

    /**
     * post参数请求
     * @param subscriber
     */
    public void getDataByPost(String url, Map<String,String> params, Observer<ResponseBody> subscriber){
        toSubscribe(mApiService.getDataByPost(url,params),subscriber);
    }

    /**
     * 多文件上传
     * @param subscriber
     */
    public void uploadFileWithRequestBody(String url, List<File> files, Observer<ResponseBody> subscriber){
        toSubscribe(mApiService.uploadFileWithRequestBody(url,filesToMultipartBody(files)),subscriber);
    }
    /**
     * 单文件图文上传
     * @param subscriber
     */
    public void uploadFileWithDescription(String url,File file,String descriptionString, Observer<ResponseBody> subscriber){
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);
        toSubscribe(mApiService.upload(url,description,body),subscriber);
    }


    private <T> void toSubscribe(Observable<T> observable, Observer subscriber){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public Retrofit getRetrofit(){
        return mRetrofit;
    }

    public static MultipartBody filesToMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
           // RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }


    public static List<MultipartBody.Part> filesToMultipartBodyParts(List<File> files) {
        List<MultipartBody.Part> parts = new ArrayList<>(files.size());
        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
            //RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            parts.add(part);
        }
        return parts;
    }



}
