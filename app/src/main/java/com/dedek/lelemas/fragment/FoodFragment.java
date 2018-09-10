package com.dedek.lelemas.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.dedek.lelemas.Adapter.GridViewAdapter;
import com.dedek.lelemas.MainActivity;
import com.dedek.lelemas.R;
import com.dedek.lelemas.helper.DatabaseHelper;

/**
 * Created by Konstantin on 22.12.2014.
 */
public class FoodFragment extends ContentFragment{

    private View containerView;
    protected ImageView mImageView;
    //protected int res;
    private Bitmap bitmap;

    DatabaseHelper db;
    GridView gridview;

    public static String[] foods = {
            "Pelet 1",
            "Pelet 2",
            "Pelet 3",
            "Pelet 4",
            "Pelet 5",
    };

    public static FoodFragment newInstance() {
        FoodFragment contentFragment = new FoodFragment();
        //Bundle bundle = new Bundle();
        //bundle.putInt(Integer.class.getName(), resId);
        //contentFragment.setArguments(bundle);
        return contentFragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.containerView = view.findViewById(R.id.container);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //res = getArguments().getInt(Integer.class.getName());
        Log.i("FoodFragment", "OnCreate");
        db = DatabaseHelper.getInstance(getActivity().getApplicationContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_food, container, false);
        gridview = rootView.findViewById(R.id.gridFood);
        gridview.setAdapter(new GridViewAdapter(this.getActivity(), foods));

        FloatingActionButton fab = this.getActivity().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Click action
            }
        });

        return rootView;
    }

    @Override
    public void takeScreenShot() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Bitmap bitmap = Bitmap.createBitmap(containerView.getWidth(),
                        containerView.getHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                containerView.draw(canvas);
                FoodFragment.this.bitmap = bitmap;
            }
        };

        thread.start();

    }

    @Override
    public Bitmap getBitmap() {
        return bitmap;
    }
}

