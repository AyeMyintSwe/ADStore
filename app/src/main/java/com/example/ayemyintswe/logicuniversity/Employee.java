package com.example.ayemyintswe.logicuniversity;

import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.HashMap;

public class Employee  extends HashMap<String, String> {


    static String tokenURL="http://172.17.6.120/adapi/sa47team8/api/token";


    public Employee(String name, String ph, String email) {
        put("userName", name);
        put("phoneNumber", ph);
        put("email", email);
    }

    public static int Login(SharedPreferences pref){
        try {
            String id = URLEncoder.encode(pref.getString("username","default"), "UTF-8");
            String pw = URLEncoder.encode(pref.getString("password","default"), "UTF-8");
            String credential = String.format("username=%s&password=%s&grant_type=password", id, pw);
            String result = JSONParser.postStream(tokenURL, false, credential);
            JSONObject res = new JSONObject(result);
            if (res.has("access_token")){
                JSONParser.access_token = res.getString("access_token");
                Log.e("Si Thu","Lin Htut");
                return 1;
            }

        } catch (Exception e) {
            JSONParser.access_token = "";
            Log.e("Login", e.toString());
        }
        return 0;
    }

    public static Employee ReadEmployee() {
        try {



            JSONObject a = JSONParser.getJSONFromUrl("http://172.17.6.120/adapi/api/employee/getemp");
            Employee e = new Employee(a.getString("userName"),
                    a.getString("phoneNumber"),
                    a.getString("email")
            );
            return e;
        } catch (Exception e) {
            Log.e("Employee", "JSONArray error");
        }
        return(null);
    }


}
