
package me.angrybyte.visibilitydemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mStateJavaContainer;
    private TextView mStateJavaContainerChild1;
    private TextView mStateJavaContainerChild2;
    private TextView mStateXmlContainer;
    private TextView mStateXmlContainerChild1;
    private TextView mStateXmlContainerChild2;
    private ViewGroup mContainerJavaWrapper;
    private ViewGroup mContainerXmlWrapper;

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStateJavaContainer = (TextView) findViewById(R.id.state_java_container);
        mStateJavaContainerChild1 = (TextView) findViewById(R.id.state_java_container_child_1);
        mStateJavaContainerChild2 = (TextView) findViewById(R.id.state_java_container_child_2);

        mStateXmlContainer = (TextView) findViewById(R.id.state_xml_container);
        mStateXmlContainerChild1 = (TextView) findViewById(R.id.state_xml_container_child_1);
        mStateXmlContainerChild2 = (TextView) findViewById(R.id.state_xml_container_child_2);

        mContainerJavaWrapper = (ViewGroup) findViewById(R.id.container_java_wrapper);
        mContainerXmlWrapper = (ViewGroup) findViewById(R.id.container_xml_wrapper);

        findViewById(R.id.set_gone_container_one).setOnClickListener(this);
        findViewById(R.id.set_visible_container_one).setOnClickListener(this);
        findViewById(R.id.set_invisible_container_one).setOnClickListener(this);
        findViewById(R.id.set_removed_container_one).setOnClickListener(this);

        findViewById(R.id.set_gone_container_two).setOnClickListener(this);
        findViewById(R.id.set_visible_container_two).setOnClickListener(this);
        findViewById(R.id.set_invisible_container_two).setOnClickListener(this);
        findViewById(R.id.set_removed_container_two).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateAllStates();
    }

    private void updateAllStates() {
        Runnable update = new Runnable() {
            @Override
            public void run() {
                mStateJavaContainer
                        .setText(getString(R.string.container_one, checkVisibility(R.id.container_java)));
                mStateJavaContainerChild1
                        .setText(getString(R.string.container_one_child_one, checkVisibility(R.id.container_java_child_1)));
                mStateJavaContainerChild2
                        .setText(getString(R.string.container_one_child_two, checkVisibility(R.id.container_java_child_2)));

                mStateXmlContainer
                        .setText(getString(R.string.container_two, checkVisibility(R.id.container_xml)));
                mStateXmlContainerChild1
                        .setText(getString(R.string.container_two_child_one, checkVisibility(R.id.container_xml_child_1)));
                mStateXmlContainerChild2
                        .setText(getString(R.string.container_two_child_two, checkVisibility(R.id.container_xml_child_2)));
            }
        };
        new Handler().post(update);
    }

    @NonNull
    private String checkVisibility(@IdRes int whichView) {
        View view = findViewById(whichView);
        if (view == null) {
            return getString(R.string.not_in_layout);
        }

        switch (view.getVisibility()) {
            case View.GONE: {
                return getString(R.string.gone);
            }
            case View.VISIBLE: {
                return getString(R.string.visible);
            }
            case View.INVISIBLE: {
                return getString(R.string.invisible);
            }
        }

        throw new IllegalStateException("View " + String.valueOf(view) + " is invalid");
    }

    private void setJavaContainerVisibility(int visibility) {
        ViewGroup javaContainer = (ViewGroup) findViewById(R.id.container_java);
        if (javaContainer == null) {
            Log.d(TAG, "Java container not available, need to inflate it again?");
            if (visibility == -1) {
                Log.d(TAG, "Not inflating, removal requested");
                updateAllStates();
                return;
            }
            javaContainer = (ViewGroup) getLayoutInflater().inflate(R.layout.container_java_visible, mContainerJavaWrapper, false);
            Log.d(TAG, "Java container inflated");
            mContainerJavaWrapper.addView(javaContainer);
        }

        if (visibility == -1) {
            mContainerJavaWrapper.removeView(javaContainer);
        } else {
            javaContainer.setVisibility(visibility);
        }
        updateAllStates();
    }

    private void setXmlContainerVisibility(int visibility) {
        ViewGroup xmlContainer = (ViewGroup) findViewById(R.id.container_xml);
        if (xmlContainer != null) {
            mContainerXmlWrapper.removeView(xmlContainer);
        }

        if (visibility == -1) {
            Log.d(TAG, "Not inflating, removal requested");
            updateAllStates();
            return;
        }

        @LayoutRes
        int layout = View.VISIBLE;
        switch (visibility) {
            case View.GONE: {
                layout = R.layout.container_xml_gone;
                break;
            }
            case View.VISIBLE: {
                layout = R.layout.container_xml_visible;
                break;
            }
            case View.INVISIBLE: {
                layout = R.layout.container_xml_invisible;
                break;
            }
        }

        xmlContainer = (ViewGroup) getLayoutInflater().inflate(layout, mContainerJavaWrapper, false);
        Log.d(TAG, "XML container inflated");
        mContainerXmlWrapper.addView(xmlContainer);

        updateAllStates();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_gone_container_one: {
                setJavaContainerVisibility(View.GONE);
                break;
            }
            case R.id.set_visible_container_one: {
                setJavaContainerVisibility(View.VISIBLE);
                break;
            }
            case R.id.set_invisible_container_one: {
                setJavaContainerVisibility(View.INVISIBLE);
                break;
            }
            case R.id.set_removed_container_one: {
                setJavaContainerVisibility(-1);
                break;
            }
            case R.id.set_gone_container_two: {
                setXmlContainerVisibility(View.GONE);
                break;
            }
            case R.id.set_visible_container_two: {
                setXmlContainerVisibility(View.VISIBLE);
                break;
            }
            case R.id.set_invisible_container_two: {
                setXmlContainerVisibility(View.INVISIBLE);
                break;
            }
            case R.id.set_removed_container_two: {
                setXmlContainerVisibility(-1);
                break;
            }
        }
    }

}
