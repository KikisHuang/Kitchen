package com.example.admin.kitchen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.admin.utils.CommonUtil.getVersion;

/**
 * Created by ${Kikis} on 2016-10-28.
 */

public class InitialActivity extends Activity{
    private TextView version_id;
    private Button gobt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_layout);
        init();
        setVersion();
        intent();
    }

    private void intent() {

        gobt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(InitialActivity.this,Welcom_Activity.class);

                startActivity(intent);
                finish();
            }
        });
    }

    private void setVersion() {
        version_id.setText(getVersion(this));
    }

    private void init() {
        version_id = (TextView) findViewById(R.id.version_id);
        gobt = (Button) findViewById(R.id.go_bt);
    }
}
