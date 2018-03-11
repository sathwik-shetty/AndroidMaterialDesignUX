package com.abc.qwerty;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener, HomeFragment.OnFragmentInteractionListener, TimelineFragment.OnFragmentInteractionListener {
    ImageView imageView_user;
    ImageView imageView;

    FloatingActionButton fab;
    RelativeLayout relativeLayout_fabs;

    FloatingActionButton fab_library;
    FloatingActionButton fab_xerox;

    float fromDegrees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_library = (FloatingActionButton) findViewById(R.id.fab_library);
        fab_xerox = (FloatingActionButton) findViewById(R.id.fab_xerox);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, fromDegrees = fromDegrees+135, fab.getWidth()/2, fab.getHeight()/2);
                rotateAnimation.setDuration(200);
                rotateAnimation.setFillEnabled(true);
                rotateAnimation.setFillAfter(true);
                fab.setAnimation(rotateAnimation);

                if ((fab_library.getVisibility() == View.INVISIBLE) || (fab_xerox.getVisibility() == View.INVISIBLE)) {

                    // get the center for the clipping circle
                    int cx = fab_xerox.getWidth() / 2;
                    int cy = fab_xerox.getHeight() / 2;

                    // get the final radius for the clipping circle
                    float finalRadius = (float) Math.hypot(cx, cy);

                    // create the animator for this view (the start radius is zero)
                    final Animator anim = ViewAnimationUtils.createCircularReveal(fab_xerox, cx, cy, 0, finalRadius);

                    // make the view visible and start the animation
                    fab_xerox.setVisibility(View.VISIBLE);

                    anim.setDuration(200);
                    anim.start();

                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            // get the center for the clipping circle
                            int cx = fab_library.getWidth() / 2;
                            int cy = fab_library.getHeight() / 2;

                            // get the final radius for the clipping circle
                            float finalRadius = (float) Math.hypot(cx, cy);

                            // create the animator for this view (the start radius is zero)
                            final Animator anim =
                                    ViewAnimationUtils.createCircularReveal(fab_library, cx, cy, 0, finalRadius);

                            // make the view visible and start the animation
                            fab_library.setVisibility(View.VISIBLE);
                            anim.setDuration(200);
                            anim.start();
                        }
                    });
                }
                else{
                    final View myView = fab_library;

                    int cx = myView.getWidth() / 2;
                    int cy = myView.getHeight() / 2;

                    float initialRadius = (float) Math.hypot(cx, cy);

                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            myView.setVisibility(View.INVISIBLE);
                        }
                    });

                    anim.setDuration(200);
                    // start the animation
                    anim.start();

                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            final View myView = fab_xerox;

                            int cx = myView.getWidth() / 2;
                            int cy = myView.getHeight() / 2;

                            float initialRadius = (float) Math.hypot(cx, cy);

                            Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

                            anim.addListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    super.onAnimationEnd(animation);
                                    myView.setVisibility(View.INVISIBLE);
                                }
                            });

                            anim.setDuration(200);
                            // start the animation
                            anim.start();
                        }
                    });
                }
            }
        });

        final Button button_home = (Button) findViewById(R.id.button_home);
        button_home.setOnClickListener(this);
        Button button_timeline = (Button) findViewById(R.id.button_timeline);
        button_timeline.setOnClickListener(this);
        final Button button_reminders = (Button) findViewById(R.id.button_reminders);
        button_reminders.setOnClickListener(this);

        imageView_user = (ImageView) findViewById(R.id.imageView_user);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserActivity.class);
                Pair<View, String> pair = Pair.create((View) imageView_user, imageView_user.getTransitionName());
                Pair<View, String> pair2 = Pair.create((View) button_home, button_home.getTransitionName());
                Pair<View, String> pair3 = Pair.create((View) button_reminders, button_reminders.getTransitionName());
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(HomeActivity.this, pair, pair2, pair3).toBundle();
                startActivity(intent, bundle);
            }
        });

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.frameLayout_home) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            HomeFragment firstFragment = new HomeFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frameLayout_home, firstFragment).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_home:
                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout_home, homeFragment).commit();
                Toast.makeText(this, "button home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_timeline:
                TimelineFragment timelineFragment = new TimelineFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.frameLayout_home, timelineFragment).commit();
                Toast.makeText(this, "button timeline", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_reminders:
                Toast.makeText(this, "button reminders", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_xerox:
                break;
            case R.id.fab_library:
                break;
            default: break;
        }
    }

    @Override
    public void onBackPressed() {
        if ((fab_library.getVisibility() == View.VISIBLE) || (fab_xerox.getVisibility() == View.VISIBLE)){
            RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, fromDegrees = fromDegrees+135, fab.getWidth()/2, fab.getHeight()/2);
            rotateAnimation.setDuration(200);
            rotateAnimation.setFillEnabled(true);
            rotateAnimation.setFillAfter(true);
            fab.setAnimation(rotateAnimation);

            final View myView = fab_library;

            int cx = myView.getWidth() / 2;
            int cy = myView.getHeight() / 2;

            float initialRadius = (float) Math.hypot(cx, cy);

            Animator anim =
                    ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            });

            anim.setDuration(200);
            // start the animation
            anim.start();

            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    final View myView = fab_xerox;

                    int cx = myView.getWidth() / 2;
                    int cy = myView.getHeight() / 2;

                    float initialRadius = (float) Math.hypot(cx, cy);

                    Animator anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, initialRadius, 0);

                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            myView.setVisibility(View.INVISIBLE);
                        }
                    });

                    anim.setDuration(200);
                    // start the animation
                    anim.start();
                }
            });
        }
        else
            super.onBackPressed();
    }
}
