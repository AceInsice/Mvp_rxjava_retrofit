package yxtk.com.yuelao.view.main;

import yxtk.com.utillibrary.mvp.BaseActivity;
import yxtk.com.utillibrary.mvp.BasePresenter;
import yxtk.com.yuelao.R;


public class HomeActivity extends BaseActivity<HomePresenter.View, HomeComponent> implements HomePresenter.View{

    private HomePresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected HomeComponent createComponent() {
        return new HomeComponent() {
            @Override
            public HomePresenter getPresenter() {
                return HomeModule.homePresenter();
            }
        };
    }

    @Override
    protected void inject(HomeComponent component) {
        presenter = component.getPresenter();
    }

    @Override
    protected BasePresenter<HomePresenter.View> getPresenter() {
        return presenter;
    }

    @Override
    protected HomePresenter.View getPresenterView() {
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
