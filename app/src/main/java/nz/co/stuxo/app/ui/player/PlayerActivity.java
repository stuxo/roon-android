package nz.co.stuxo.app.ui.player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;
import nz.co.stuxo.androidboilerplate.R;
import nz.co.stuxo.app.data.SyncService;
import nz.co.stuxo.app.data.model.Ribot;
import nz.co.stuxo.app.ui.base.BaseActivity;
import nz.co.stuxo.app.ui.main.RibotsAdapter;
import nz.co.stuxo.app.util.DialogFactory;

public class PlayerActivity extends BaseActivity implements PlayerMvpView {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
            "uk.co.ribot.androidboilerplate.ui.main.PlayerActivity.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject PlayerPresenter mPlayerPresenter;

    @BindView(R.id.progress) TextView mProgress;

    @BindView(R.id.play_pause) Button mPlayPause;


    @OnClick(R.id.play_pause)
    public void onPlayPauseClicked(){
        mPlayerPresenter.playPause();
    }

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, PlayerActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_player);
        ButterKnife.bind(this);

        mPlayerPresenter.attachView(this);
        mPlayerPresenter.loadRibots();

        if (getIntent().getBooleanExtra(EXTRA_TRIGGER_SYNC_FLAG, true)) {
            startService(SyncService.getStartIntent(this));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mPlayerPresenter.detachView();
    }

    /***** MVP View methods implementation *****/

    @Override
    public void showRibots(List<Ribot> ribots) {
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_ribots))
                .show();
    }

    @Override
    public void showRibotsEmpty() {
        Toast.makeText(this, R.string.empty_ribots, Toast.LENGTH_LONG).show();
    }

}
