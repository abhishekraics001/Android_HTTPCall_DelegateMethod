package saftyclock.luminous.com.httpexecute.retrofit.delegate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import saftyclock.luminous.com.httpexecute.R;

public class CallDelegateFromActivity extends AppCompatActivity  implements AppDelegate.AsyncResponse{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delegate);


        AppDelegate xx = new AppDelegate(CallDelegateFromActivity.this);
        xx.execute();

    }


    @Override
    public void processFinish(String output) {
        Toast.makeText(getApplicationContext() , output , Toast.LENGTH_SHORT).show();
    }
}
