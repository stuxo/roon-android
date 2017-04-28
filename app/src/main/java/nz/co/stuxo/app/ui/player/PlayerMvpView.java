package nz.co.stuxo.app.ui.player;

import java.util.List;
import nz.co.stuxo.app.data.model.Ribot;
import nz.co.stuxo.app.ui.base.MvpView;

public interface PlayerMvpView extends MvpView {

    void showRibots(List<Ribot> ribots);

    void showRibotsEmpty();

    void showError();

}
