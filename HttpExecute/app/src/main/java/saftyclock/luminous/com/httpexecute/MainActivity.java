package saftyclock.luminous.com.httpexecute;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import saftyclock.luminous.com.httpexecute.retrofit.APIClient;
import saftyclock.luminous.com.httpexecute.retrofit.APIInterface;
import saftyclock.luminous.com.httpexecute.retrofit.User;
import saftyclock.luminous.com.httpexecute.retrofit.UserList;
import saftyclock.luminous.com.httpexecute.retrofit.delegate.CallDelegateFromActivity;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Button btnvolly = (Button) findViewById(R.id.button);
        Button ArQurry = (Button) findViewById(R.id.button2);
        Button Retrofit = (Button) findViewById(R.id.button3);
        Button calldelegate = (Button) findViewById(R.id.button4);

        btnvolly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                /*  for volley
                    1. Add dependancy in gradel file
                    2. create application class and add in menifest file in application tag

                */
                vollyStringRequest();
                vollyJsonObjectRequest();
                vollJSONArryRequest();
            }
        });

        ArQurry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*
                    for AQueary
                    1. Add .jar file in lib folder
                    2. Then go to module setting and add jar dependancy

                 */
               ArQurryStringRequest();
               ArQurryJSONObjectRequest();
               ArQurryArrayRequest();
            }
        });

        Retrofit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resTrofit();
            }
        });


        calldelegate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , CallDelegateFromActivity.class));
            }
        });
    }







    void  vollyStringRequest()
    {
        String tag_json_obj = "json_obj_req";
       // RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://mapps.luminousindia.com/WebApiDemo/api/values?&F=2&S=2";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>()
        {
                    @Override
                    public void onResponse(String response) 
                    {
                        System.out.println("XX onResponse  " + response);
                    }
        }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error)
        {
            System.out.println("XX onResponse  " + error.toString());
        }
        });
       // queue.add(stringRequest);
        AppController.getInstance().addToRequestQueue(stringRequest, tag_json_obj);
    }



    void vollyJsonObjectRequest() {
        String tag_json_obj = "json_obj_req";
        String url = "http://mapps.luminousindia.com/WebApiDemo/api/values?&F=2&S=2";
        System.out.println("XX  "+ url);
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("XX onResponse  " + response.toString());
                        try {
                            JSONObject aa = new JSONObject(response.toString());

                        }
                        catch (JSONException e) {
                            System.out.println("XX JSONException " + e.toString());
                        }
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("XX onErrorResponse  "+ error.getMessage());
                pDialog.hide();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }


    void  vollJSONArryRequest()
    {
         String mJSONURLString = "http://pastebin.com/raw/Em972E5s";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        System.out.println("XX onResponse  " + response);
                        try
                        {
                            for(int i=0;i<response.length();i++)
                            {
                                JSONObject student = response.getJSONObject(i);
                                String firstName = student.getString("firstname");
                                String lastName = student.getString("lastname");
                                String age = student.getString("age");
                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, "jsonarray");
    }









    void ArQurryStringRequest()
    {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        AQuery aq = new AQuery(getApplicationContext());
        AjaxCallback<String> cb = new AjaxCallback<String>() {
            @Override
            public void callback(String url, String responceData, AjaxStatus status)
            {
                System.out.println("XX callback  " + status + "   responceData   " + responceData);
                pDialog.dismiss();
            }
        };

        HashMap<String, String> postData = new HashMap<String, String>();
        String BaseURL =  "http://mapps.luminousindia.com/WebApiDemo/api/values?&F=2&S=2";
     //   postData.put("F" , "2");
      //  postData.put("S" , "3");

        System.out.println("XX ajax  1111111" + BaseURL + "   Data   " + postData.toString());
        aq.ajax(BaseURL, postData, String.class, cb);
    }


    void ArQurryJSONObjectRequest()
    {
            final ProgressDialog pDialog = new ProgressDialog(this);
            pDialog.setMessage("Loading...");
            pDialog.show();
            AQuery aq = new AQuery(getApplicationContext());
            AjaxCallback<JSONObject> cb = new AjaxCallback<JSONObject>() {
                @Override
                public void callback(String url, JSONObject responceData, AjaxStatus status)
                {
                    System.out.println("XX callback  " + status + "   responceData   " + responceData);
                    pDialog.dismiss();
                    try
                    {
                        boolean  statusmsg = responceData.getBoolean("status");
                        String  message = responceData.getString("msg");
                        if(statusmsg)
                        {

                        }
                        else
                        {
                           // Util.showToastSomthingWrong(getApplicationContext());
                        }
                    }
                    catch (JSONException e)
                    {
                        e.printStackTrace();
                        System.out.println("XX JSONException  " +e.toString());
                    }
                }
            };

            HashMap<String, String> postData = new HashMap<String, String>();
            String BaseURL =  "http://mapps.luminousindia.com/WebApiDemo/api/values?";
            postData.put("F" , "2");
            postData.put("S" , "3");

            System.out.println("XX ajax  1111111" + BaseURL + "   Data   " + postData.toString());
            aq.ajax(BaseURL, postData, JSONObject.class, cb);
    }


    void ArQurryArrayRequest()
    {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        AQuery aq = new AQuery(getApplicationContext());
        AjaxCallback<JSONArray> cb = new AjaxCallback<JSONArray>() {
            @Override
            public void callback(String url, JSONArray responceData, AjaxStatus status)
            {
                System.out.println("XX callback  " + status + "   responceData   " + responceData);
                pDialog.dismiss();
                try
                {
                    for(int i=0;i<responceData.length();i++)
                    {
                        JSONObject student = responceData.getJSONObject(i);
                        String firstName = student.getString("firstname");
                        String lastName = student.getString("lastname");
                        String age = student.getString("age");
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };

        HashMap<String, String> postData = new HashMap<String, String>();
        String BaseURL =  "http://pastebin.com/raw/Em972E5s";
        System.out.println("XX ajax  1111111" + BaseURL + "   Data   " + postData.toString());
        aq.ajax(BaseURL, postData, JSONArray.class, cb);
    }





    APIInterface apiInterface;

    void  resTrofit()
    {

        /**  POST API  -    https://reqres.in/api/users/2
            Create new user
         **/
        User user = new User("morpheus", "leader");
        Call<User> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                User user1 = response.body();
                Toast.makeText(getApplicationContext(), user1.name + " " + user1.job + " " + user1.id + " " + user1.createdAt, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                call.cancel();
            }
        });




        /**  GET API  -  https://reqres.in/api/users?page=2
            GET List Users
         **/
        Call<UserList> call2 = apiInterface.doGetUserList("2");
        call2.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, retrofit2.Response<UserList> response)
            {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();
                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });


        /**   POST name and job Url encoded.
             https://reqres.in/api/users?
             {
                "name": "morpheus",
                "job": "leader"
             }
         **/
        Call<UserList> call3 = apiInterface.doCreateUserWithField("morpheus","leader");
        call3.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, retrofit2.Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });


    }
}
