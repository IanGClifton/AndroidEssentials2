package com.iangclifton.androidessentials;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;


public class AnimationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_animation);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_animation, container, false);

            rootView.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    final ObjectAnimator animator = ObjectAnimator.ofFloat(v, "alpha", 1f, 0f);
                    animator.setDuration(DateUtils.SECOND_IN_MILLIS);
                    animator.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            v.setBackgroundColor(Color.BLUE);
                            v.setAlpha(1f);
                        }
                    });
                    animator.start();
                }
            });

            rootView.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.animate().x(500).y(500);
                }
            });

            rootView.findViewById(R.id.large_text).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent = new Intent(getActivity(), AnimationResultActivity.class);
                    final String viewName = getString(R.string.transition_view);
                    final Bundle options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), v, viewName).toBundle();

                    ActivityCompat.startActivity(getActivity(), intent, options);
                }
            });

            return rootView;
        }
    }
}
