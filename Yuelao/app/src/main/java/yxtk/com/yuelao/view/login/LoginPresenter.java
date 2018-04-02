package yxtk.com.yuelao.view.login;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Response;
import okhttp3.ResponseBody;

import yxtk.com.utillibrary.mvp.BasePresenter;
import yxtk.com.utillibrary.mvp.PresenterView;
import yxtk.com.utillibrary.network.BaseResponse;
import yxtk.com.utillibrary.network.RetrofitUtil;
import yxtk.com.yuelao.config.Config;

class LoginPresenter extends BasePresenter<LoginPresenter.View> {

    private RetrofitUtil mRetrofitClient = RetrofitUtil.getInstance(Config.APP_HOST);
    LoginPresenter() {
    }

    @Override
    public void onViewAttached(View view) {
        super.onViewAttached(view);
    }

    interface View extends PresenterView {
        void onLoginStart();
        void onLoginSuccess();
        void onLoginFail();
    }

    public void login(String account,String pwd){
        Map<String,String> param = new HashMap();
        param.put("account",account);
        param.put("pwd",pwd);
        view.onLoginStart();
        mRetrofitClient.getDataByPost(/*Config.APP_HOST + */"/cas/v1/token", param, new Observer<ResponseBody>() {
            @Override
            public void onComplete() {

            }
            @Override
            public void onNext(ResponseBody response ) {
                try {
                    Log.e("nimei",response.string().trim());
                  //  String jsonString = new String(((TypedByteArray)response.getBody()).getBytes());

                }catch (Exception e){

                }

              view.onLoginSuccess();
            }
            @Override
            public void onError(Throwable e) {
                Log.e("nimei","error" + e.getMessage());
                view.onLoginFail();
            }
            @Override
            public void onSubscribe(Disposable d) {

            }
        });
    }
}
