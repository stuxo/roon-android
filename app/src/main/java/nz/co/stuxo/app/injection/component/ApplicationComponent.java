package nz.co.stuxo.app.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import nz.co.stuxo.app.data.DataManager;
import nz.co.stuxo.app.data.SyncService;
import nz.co.stuxo.app.data.local.DatabaseHelper;
import nz.co.stuxo.app.data.local.PreferencesHelper;
import nz.co.stuxo.app.data.remote.RoonHttpService;
import nz.co.stuxo.app.injection.ApplicationContext;
import nz.co.stuxo.app.injection.module.ApplicationModule;
import nz.co.stuxo.app.util.RxEventBus;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    RoonHttpService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    RxEventBus eventBus();

}
