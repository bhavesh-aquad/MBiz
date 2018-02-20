package com.darshan.mbiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPassword extends AppCompatActivity {
    TextView tv_send_code;
    Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tv_send_code = (TextView) findViewById(R.id.tv_send_code);
        btn_send = (Button) findViewById(R.id.btn_send);

        /**Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle(null);*/

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_send_code.setText("A CODE SENT TO YOUR EMAIL ID");
            }
        });
    }

        public boolean onOptionsItemSelected(android.view.MenuItem item){
            if(item.getItemId()==R.id.forgotpassword);
            finish();

            return super.onOptionsItemSelected(item);
    }
}

