package com.example.carson_ho.toptabbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class CountManger extends AppCompatActivity {

    private RelativeLayout rlHeadPortrait;
    private RelativeLayout rlPhoneNumber;
    private Button btnOutOfLog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_manger);

        findViewId();

        setListener();
    }

    public void findViewId()
    {
        rlHeadPortrait = (RelativeLayout)findViewById(R.id.rl_HeadPortrait);
        rlPhoneNumber = (RelativeLayout)findViewById(R.id.rl_PhoneNumber);
        btnOutOfLog = (Button)findViewById(R.id.btn_outOfLog);
    }
    public void setListener()
    {
        rlHeadPortrait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountManger.this,HeadPortrait.class);
                startActivity(intent);
            }
        });

        rlPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountManger.this,PhoneNumber.class);
                startActivity(intent);
            }
        });

        btnOutOfLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountManger.this,LogIn.class);
                startActivity(intent);
            }
        });
    }
}
