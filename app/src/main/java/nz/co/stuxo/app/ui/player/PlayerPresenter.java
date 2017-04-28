package nz.co.stuxo.app.ui.player;

import java.util.List;
import javax.inject.Inject;
import nz.co.stuxo.app.data.DataManager;
import nz.co.stuxo.app.data.model.Ribot;
import nz.co.stuxo.app.injection.ConfigPersistent;
import nz.co.stuxo.app.ui.base.BasePresenter;
import nz.co.stuxo.app.util.RxUtil;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@ConfigPersistent
public class PlayerPresenter extends BasePresenter<PlayerMvpView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public PlayerPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(PlayerMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadRibots() {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getRibots()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Ribot>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the ribots.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Ribot> ribots) {
                        if (ribots.isEmpty()) {
                            getMvpView().showRibotsEmpty();
                        } else {
                            getMvpView().showRibots(ribots);
                        }
                    }
                });
    }

    public void playPause(){
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.pause()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(new Subscriber<ResponseBody>() {
                @Override public void onCompleted() {

                }

                @Override public void onError(Throwable e) {
                    e.printStackTrace();
                    getMvpView().showError();
                }

                @Override public void onNext(ResponseBody responseBody) {

                }
            });
    }
}
