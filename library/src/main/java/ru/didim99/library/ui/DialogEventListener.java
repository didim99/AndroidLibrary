package ru.didim99.library.ui;

import androidx.appcompat.app.AlertDialog;

/**
 * Created by didim99 on 26.02.19.
 */
@FunctionalInterface
public interface DialogEventListener {
  void onEvent(AlertDialog d);
}
