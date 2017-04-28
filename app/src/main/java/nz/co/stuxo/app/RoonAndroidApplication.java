package nz.co.stuxo.app;

import android.app.Application;
import android.content.Context;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import nz.co.stuxo.androidboilerplate.BuildConfig;
import timber.log.Timber;
import nz.co.stuxo.app.injection.component.ApplicationComponent;
import nz.co.stuxo.app.injection.component.DaggerApplicationComponent;
import nz.co.stuxo.app.injection.module.ApplicationModule;

public class RoonAndroidApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            Fabric.with(this, new Crashlytics());
        }
    }

    public static RoonAndroidApplication get(Context context) {
        return (RoonAndroidApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
