package com.mygdx.game.android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.MainScreen;
import com.mygdx.game.MainScreen.ExternalInterface;
import com.mygdx.game.MainScreen.OnConfirmationListener;


public class AndroidLauncher extends AndroidApplication implements ExternalInterface {

    public static final int MSG_CONFIRM = 1;

    MainScreen game;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useAccelerometer = false;
        config.useCompass = false;

		initialize(new MainScreen(this), config);
	}


    public void showConfirmation(final String message, final OnConfirmationListener listener ) {
        runOnUiThread(new Runnable() {
            public void run() {
                new AlertDialog.Builder(AndroidLauncher.this)
                        .setMessage(message)
                        .setPositiveButton("Yes", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onConfirmation(true);
                            }
                        })
                        .setNegativeButton("No", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onConfirmation(false);
                            }
                        })
                        .create()
                        .show();
            }
        });
    }


    public void showAlert(final String message, final OnConfirmationListener listener ) {
        runOnUiThread(new Runnable() {
            public void run() {


                new AlertDialog.Builder(AndroidLauncher.this)
                        .setTitle("Atención")
                        .setMessage(message)
                        .setPositiveButton("Yes", new OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                listener.onConfirmation(true);
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .create()
                        .show();

            }
        });
    }




}
