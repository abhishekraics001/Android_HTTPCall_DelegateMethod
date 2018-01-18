package saftyclock.luminous.com.httpexecute.retrofit.delegate;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by mappsdeveloper on 12-01-2018.
 */

public class AppDelegate extends AsyncTask<Void, Void, String> {


    Context context;
    public AppDelegate(Context context1) {
        this.context = context1;
        delegate = (AsyncResponse) context;
    }



    public AsyncResponse delegate = null;
    public interface AsyncResponse {
        void processFinish(String output);
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(Void... params) {
            return "www.xxx.com";
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        delegate.processFinish(s);
    }
}
