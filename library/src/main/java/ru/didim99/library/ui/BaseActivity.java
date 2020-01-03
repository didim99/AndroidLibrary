package ru.didim99.library.ui;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.CallSuper;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import ru.didim99.library.R;
import ru.didim99.library.utils.MyLog;

/**
 * Basic Activity with common UI features
 * Created by didim99 on 16.09.18.
 */
public abstract class BaseActivity extends AppCompatActivity {
  private static final String LOG_TAG = MyLog.LOG_TAG_BASE + "_BaseAct";

  //main workflow
  protected boolean disableBackBtn = false;
  protected boolean uiLocked = false;

  @Override
  @CallSuper
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupActionBar();
  }

  @Override
  public final void onBackPressed() {
    if (!uiLocked) super.onBackPressed();
  }

  @Override
  @CallSuper
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home)
      onBackPressed();
    return false;
  }

  private void setupActionBar() {
    ActionBar bar = getSupportActionBar();
    if (bar != null) {
      onSetupActionBar(bar);
      if (!disableBackBtn) {
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);
      }
    }
  }

  protected void onSetupActionBar(ActionBar bar) {}

  protected void inputFieldDialog(@StringRes int titleId, @LayoutRes int viewId,
                                  DialogEventListener listener) {
    MyLog.d(LOG_TAG, "Field dialog called");
    AlertDialog dialog = new AlertDialog.Builder(this)
      .setTitle(titleId).setView(viewId)
      .setPositiveButton(R.string.dialogButtonOk, null)
      .setNegativeButton(R.string.dialogButtonCancel, null)
      .create();
    MyLog.d(LOG_TAG, "Field dialog created");
    dialog.setOnShowListener((di) -> {
      AlertDialog d = (AlertDialog) di;
      d.getButton(AlertDialog.BUTTON_POSITIVE)
        .setOnClickListener(v -> listener.onEvent(d));
    });
    dialog.show();
  }
}
