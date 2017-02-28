package kr.co.hs.content.advancedpreference.sample;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import kr.co.hs.content.advancedpreference.AdvancedPreference;

/**
 * 생성된 시간 2017-02-16, Bae 에 의해 생성됨
 * 프로젝트 이름 : AdvancedPreference
 * 패키지명 : kr.co.hs.content.advancedpreference.sample
 */

public class SampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //initilize
        SharedPreferences preferences = getSharedPreferences("Test", MODE_PRIVATE);
        AdvancedPreference advancedPreference = new AdvancedPreference(preferences);
        Log.d("a","a");

        //or

        advancedPreference = new AdvancedPreference(getApplicationContext());
        //like this  PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //or
        advancedPreference = new AdvancedPreference(getApplicationContext(), "name", Context.MODE_PRIVATE);
        //like this   preferences = getSharedPreferences("name", Context.MODE_PRIVATE);



        //put data
        advancedPreference.set("Key", "Value");
        advancedPreference.commit();
        Log.d("a","a");

        //get data
        String data = advancedPreference.getString("Key", "Default");
        Log.d("a","a");
    }
}
