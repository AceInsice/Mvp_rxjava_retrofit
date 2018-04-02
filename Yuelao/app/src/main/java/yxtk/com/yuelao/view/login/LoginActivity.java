package yxtk.com.yuelao.view.login;

import yxtk.com.utillibrary.mvp.BaseActivity;
import yxtk.com.utillibrary.mvp.BasePresenter;
import yxtk.com.yuelao.R;


public class LoginActivity extends BaseActivity<LoginPresenter.View,LoginComponent> implements LoginPresenter.View{

    private LoginPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected LoginComponent createComponent() {
        return new LoginComponent() {
            @Override
            public LoginPresenter getPresenter() {
                return LoginModule.loginPresenter();
            }
        };
    }

    @Override
    protected void inject(LoginComponent component) {
        presenter = component.getPresenter();
    }

    @Override
    protected BasePresenter<LoginPresenter.View> getPresenter() {
        return presenter;
    }

    @Override
    protected LoginPresenter.View getPresenterView() {
        return this;
    }

    @Override
    public void onLoginStart() {

    }
    @Override
    public void onLoginSuccess() {

    }
    @Override
    public void onLoginFail() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.login("admin","admin");
    }
}
