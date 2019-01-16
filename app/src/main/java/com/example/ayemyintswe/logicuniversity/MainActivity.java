package com.example.ayemyintswe.logicuniversity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    SharedPreferences pref;
    TextView t1,t2;
    String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



       new AsyncTask<Void, Void,Employee>(){
            @Override
            protected Employee doInBackground(Void... params) {

                return Employee.ReadEmployee();
            }
            @Override
            protected void onPostExecute(Employee emp) {
                TextView t1=findViewById(R.id.textView3);
                TextView t2=findViewById(R.id.textView4);
                if(emp==null){
                    t1.setText("first time");
                    t2.setText("first time");}else{
                    t1.setText(emp.get("userName"));
                    t2.setText(emp.get("phoneNumber"));}}

        }.execute();


      /*  pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        username=pref.getString("username","default");
        password=pref.getString("password","default");
        t1=(TextView)findViewById(R.id.textView3);
        t1.setText(username);

        t2=(TextView)findViewById(R.id.textView4);
        t2.setText(password);*/




    }
}
