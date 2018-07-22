package com.somayahalharbi.bakingapp.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.somayahalharbi.bakingapp.R;
import com.somayahalharbi.bakingapp.models.Step;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_STEP_FRAGMENT = "step_fragment";
    private final String STEPS_LIST = "steps_list";
    private final String CURRENT_STEP_INDEX = "step_index";
    private final String LANDSCAPE_EXTRA = "landscape_mode";
    Fragment stepsFragment;

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.back_button)
    ImageView navigationBack;
    private int currentIndex;
    private ArrayList<Step> stepList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_details);
        ButterKnife.bind(this);
        stepsFragment = new StepDetailFragment();

        final Bundle bundle = getIntent().getExtras();
        if (savedInstanceState == null) {
            if (bundle != null && bundle.containsKey(STEPS_LIST) && bundle.containsKey(CURRENT_STEP_INDEX)) {
                currentIndex = bundle.getInt(CURRENT_STEP_INDEX);
                stepList = bundle.getParcelableArrayList(STEPS_LIST);
            }
        }
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(CURRENT_STEP_INDEX) && savedInstanceState.containsKey(STEPS_LIST)) {
                currentIndex = savedInstanceState.getInt(CURRENT_STEP_INDEX);
                stepList = savedInstanceState.getParcelableArrayList(STEPS_LIST);
                stepsFragment = getSupportFragmentManager().getFragment(savedInstanceState, EXTRA_STEP_FRAGMENT);

            }
        }
        setSupportActionBar(mToolBar);
        navigationBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        displayData();


    }

    private void displayData() {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(STEPS_LIST, stepList);
        bundle.putInt(CURRENT_STEP_INDEX, currentIndex);
        bundle.putBoolean(LANDSCAPE_EXTRA, isRotated());
        stepsFragment.setArguments(bundle);
        FragmentManager stepsFragmentManager = getSupportFragmentManager();
        stepsFragmentManager.beginTransaction().replace(R.id.steps_container, stepsFragment).commit();

    }

    private boolean isRotated() {

        boolean mRotation;
        assert (this.getSystemService(Context.WINDOW_SERVICE)) != null;
        assert this.getSystemService(Context.WINDOW_SERVICE) != null;
        final int rotation = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getOrientation();
        switch (rotation) {
            case Surface.ROTATION_90:
                mRotation = true;
                break;

            case Surface.ROTATION_180:
                mRotation = true;
                break;

            default:
                mRotation = false;
        }
        return mRotation;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(CURRENT_STEP_INDEX, currentIndex);
        outState.putParcelableArrayList(STEPS_LIST, stepList);
        getSupportFragmentManager().putFragment(outState, EXTRA_STEP_FRAGMENT, stepsFragment);
        super.onSaveInstanceState(outState);
    }

}
