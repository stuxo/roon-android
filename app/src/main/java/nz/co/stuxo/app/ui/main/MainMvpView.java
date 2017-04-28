package nz.co.stuxo.app.ui.main;

import java.util.List;

import nz.co.stuxo.app.data.model.Ribot;
import nz.co.stuxo.app.ui.base.MvpView;

public interface MainMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
