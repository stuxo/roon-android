package nz.co.stuxo.app.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import nz.co.stuxo.app.injection.component.ApplicationComponent;
import nz.co.stuxo.app.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
