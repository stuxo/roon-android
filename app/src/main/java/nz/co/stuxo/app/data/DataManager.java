package nz.co.stuxo.app.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import nz.co.stuxo.app.data.remote.RoonHttpService;
import okhttp3.ResponseBody;
import rx.Observable;
import rx.functions.Func1;
import nz.co.stuxo.app.data.local.DatabaseHelper;
import nz.co.stuxo.app.data.local.PreferencesHelper;
import nz.co.stuxo.app.data.model.Ribot;

@Singleton
public class DataManager {

    private final RoonHttpService mRoonHttpService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public DataManager(RoonHttpService roonHttpService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper) {
        mRoonHttpService = roonHttpService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<List<Ribot>> getRibots() {
        return mDatabaseHelper.getRibots().distinct();
    }

    public Observable<ResponseBody> pause() {
        return mRoonHttpService.playPause("1601e32f092c00e039c380e60bef598525a3");
    }
}
