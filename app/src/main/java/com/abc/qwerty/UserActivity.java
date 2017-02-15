package com.abc.qwerty;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity implements View.OnClickListener, AttendanceFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_user);

//        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> User details </font>"));

        if (findViewById(R.id.frameLayout_attendance) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            AttendanceFragment firstFragment = new AttendanceFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
//            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout_attendance, firstFragment).commit();
        }

        ImageView imageView_user = (ImageView) findViewById(R.id.imageView_user);

        Button button_attendance = (Button) findViewById(R.id.button_attendance);
        button_attendance.setOnClickListener(this);
        Button button_cgpa = (Button) findViewById(R.id.button_cgpa);
        button_cgpa.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_attendance:
                AttendanceFragment attendanceFragment = new AttendanceFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout_attendance, attendanceFragment).commit();
                Toast.makeText(this, " button attendance", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_cgpa:
                Toast.makeText(this, "button cgpa", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}