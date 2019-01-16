package com.example.ayemyintswe.logicuniversity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends Activity implements View.OnClickListener{

    SharedPreferences pref;
    EditText t1,t2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        pref=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        t1=(EditText)findViewById(R.id.editText1);
        t1.setText(pref.getString("username","default"));
        t2=(EditText)findViewById(R.id.editText2);
        t2.setText(pref.getString("password","default"));

        Button b=(Button)findViewById(R.id.button1);
        b.setOnClickListener(this);




    }

    @Override
    public void onClick(View v){
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username", t1.getText().toString());
        editor.putString("password", t2.getText().toString());
        editor.commit();



        new AsyncTask<Void,Void,Integer>() {
            @Override
            protected Integer doInBackground(Void... v) {
               int a= Employee.Login(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
                return a;
            }
            @Override
            protected void onPostExecute(Integer a) {
                if(a==1){
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else if(a==0){
                    Toast.makeText(getApplicationContext(),"context1", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();


    }
}
