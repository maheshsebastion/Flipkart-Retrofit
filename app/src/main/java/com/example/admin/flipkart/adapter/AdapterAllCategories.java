package com.example.admin.flipkart.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.flipkart.R;
import com.example.admin.flipkart.models.allcategorylist.Category;

import java.util.List;

/**
 * Created by Admin on 23-08-2017.
 */

public class AdapterAllCategories extends BaseAdapter {

    Context mContext;
    List<Category> categories;

    String baseURL,iPath,iName,URL;

    public AdapterAllCategories(Context context, List<Category> cList) {
        this.mContext = context;
        this.categories = cList;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView iv_icon;
        TextView iv_category;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_listview, null);
            holder = new ViewHolder();
            holder.iv_icon = (ImageView) convertView.findViewById(R.id.icon);
            holder.iv_category = (TextView) convertView.findViewById(R.id.category);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Category category = (Category) getItem(position);

        //Setting the Category and Icon in LISTVIEW
        holder.iv_category.setText(category.getName());
        Glide.with(mContext)
                .load(category.getIconUrl())
                .into(holder.iv_icon);



        return convertView;
    }
    @Override
    public int getCount() {
        return categories.size();
    }

    @Override
    public Object getItem(int position) {
        return categories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return categories.indexOf(getItem(position));
    }
}
