package com.adg.adapter;


import java.util.ArrayList;

import com.adg.object.SearchObj;
import com.adg.weather.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class SearchAdapter extends BaseAdapter {
	
    Context context;
    ArrayList<SearchObj> serArr = new ArrayList<SearchObj>();
	
    public SearchAdapter(Context c, ArrayList<SearchObj> so) {
        this.context = c;
        this.serArr = so;
    }

    public int getCount() {
        return serArr.size();
    }

    public Object getItem(int arg0) {
        return null;
    }

    public long getItemId(int arg0) {
        return 0;
    }
	
    View child;
    TextView loc;

    public View getView(int arg0, View arg1, ViewGroup arg2) {
        LayoutInflater li = (LayoutInflater) context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);

        child = li.inflate(R.layout.search_item, null);
        loc = (TextView) child.findViewById(R.id.searchLocation);
        if (serArr.size() == 0) {
            loc.setText("There are no locations that you requested");
        } else {
            loc.setText(
                    serArr.get(arg0).getAreaName() + ", "
                    + serArr.get(arg0).getRegionName() + "\n"
                    + serArr.get(arg0).getContryName());
        }
        return child;
    }
	
}
