package yxtk.com.utillibrary.mvp;

import android.support.annotation.CallSuper;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import yxtk.com.utillibrary.network.RetrofitUtil;

public class BasePresenter<V extends PresenterView> {
    private final CompositeDisposable disposables = new CompositeDisposable();
    public V view;

    @CallSuper
    public void onViewAttached(V view) {
        if (this.view != null) {
            throw new IllegalStateException("View " + this.view + " is already attached. Cannot attach " + view);
        }
        this.view = view;
    }

    @CallSuper
    public void onViewDetached() {
        if (view == null) {
            throw new IllegalStateException("View is already detached");
        }
        view = null;
        disposables.clear();
    }

    @CallSuper
    protected final void addToAutoUnsubscribe(Disposable subscription) {
        disposables.add(subscription);
    }
}
