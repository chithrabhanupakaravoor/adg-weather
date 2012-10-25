package com.adg.handlers;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;


public class MessageHandler extends Handler {
	
    ProgressDialog progressBar;
    public final static int SHOW_LOADING_MESSAGE = 1;
    public final static int SHOW_RUSIAN_BAR = 2;
    public final static int SHOW_TURTH_BAR = 3;
    public final static int SHOW_WHAT_SIR = 4;
    public final static int SHOW_MY_DAY = 5;
    public final static int SHOW_ANOTHER_THING = 6;
    public final static int SHOW_IF_ONLY = 7;
    public final static int SHOW_I_WONDER = 8;
    public final static int SHOW_LOADING = 9;
    Activity a;
    public MessageHandler(Activity activity) {
        this.a = activity;
    }
	
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
        case SHOW_LOADING_MESSAGE:
            progressBar = ProgressDialog.show(a, "We are watching you",
                    "Always watching...", true);
            progressBar.show();
            break;

        case SHOW_RUSIAN_BAR:
            progressBar = ProgressDialog.show(a, "In Soviet Russia",
                    "Movies watch you", true);
            progressBar.show();
            break;

        case SHOW_TURTH_BAR:
            progressBar = ProgressDialog.show(a, "We are only telling you this",
                    "to distract you", true);
            progressBar.show();
            break;

        case SHOW_WHAT_SIR:
            progressBar = ProgressDialog.show(a, "Hey you!",
                    "Check out the sixth movie on this list", true);
            progressBar.show();
            break;

        case SHOW_MY_DAY:
            progressBar = ProgressDialog.show(a, "Back in my day...",
                    "Cows were call milk machine", true);
            progressBar.show();
            break;

        case SHOW_ANOTHER_THING:
            progressBar = ProgressDialog.show(a, "Back in my day...",
                    "phones didn't have 'g' except on the 4", true);
            progressBar.show();
            break;

        case SHOW_IF_ONLY:
            progressBar = ProgressDialog.show(a, "LOOK OUT BEHIND YOU!!!",
                    "haha, you should have seen the look on your face", true);
            progressBar.show();
            break;

        case SHOW_I_WONDER:
            progressBar = ProgressDialog.show(a, "We wonder...",
                    "What if your soulmate is looking at the same loading bar",
                    true);
            progressBar.show();
            break;

        case SHOW_LOADING:
            progressBar = ProgressDialog.show(a, "Loading", "Please wait", true);
            progressBar.show();
            break;	

        default:
            if (progressBar.isShowing()) {
                progressBar.dismiss();
            }
            break;
        }
    }
}
