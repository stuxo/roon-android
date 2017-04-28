package nz.co.stuxo.app.injection.component;

import dagger.Subcomponent;
import nz.co.stuxo.app.injection.PerActivity;
import nz.co.stuxo.app.injection.module.ActivityModule;
import nz.co.stuxo.app.ui.main.MainActivity;
import nz.co.stuxo.app.ui.player.PlayerActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(PlayerActivity playerActivity);

}
