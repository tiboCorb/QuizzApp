package com.example.quizzapp.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizzapp.R;

public class CustomSpinnerAdapter extends BaseAdapter
{
    private int logos[];
    private String[]  languagesNames;
    private LayoutInflater inflater;

    public CustomSpinnerAdapter(Context applicationContext, int[] logos, String[] languagesNames)
    {
        this.logos = logos;
        this.languagesNames = languagesNames;
        inflater = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount()
    {
        return logos.length;
    }

    @Override
    public Object getItem(int i)
    {
        return null;
    }

    @Override
    public long getItemId(int i)
    {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {

        view = inflater.inflate(R.layout.custom_spinner_items, viewGroup, false);
        ImageView language_icon = (ImageView) view.findViewById(R.id.imageView);
        TextView language_name = (TextView) view.findViewById(R.id.textView);
        language_icon.setImageResource(logos[i]);
        language_name.setText(languagesNames[i]);
        return view;
    }
}
