package com.dedek.lelemas.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dedek.lelemas.MainActivity;
import com.dedek.lelemas.R;

/**
 * Created by KOSEN 560 on 9/10/2018.
 */

public class GridViewAdapter extends BaseAdapter {
    String [] result;
    Context context;
    //int [] imageId;       // custom image. we dont need this for now
    private static LayoutInflater inflater=null;

    public GridViewAdapter(Activity activity, String[] osNameList) {
        // TODO Auto-generated constructor stub
        result=osNameList;
        context=activity;
        //imageId=osImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public GridViewAdapter(MainActivity mainActivity, String[] osNameList , int[] osImages) {   // deprecated for now not using custom image
        // TODO Auto-generated constructor stub
        result=osNameList;
        context=mainActivity;
        //imageId=osImages;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView os_text;
        ImageView os_img;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.gridview_content, null);
        holder.os_text = rowView.findViewById(R.id.os_texts);
        //holder.os_img =(ImageView) rowView.findViewById(R.id.os_images);

        holder.os_text.setText(result[position]);
        //holder.os_img.setImageResource(imageId[position]);

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[position], Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }

}
