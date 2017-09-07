package com.example.admin.flipkart.category.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.flipkart.R;
import com.example.admin.flipkart.models.Category;
import com.example.admin.flipkart.models.CategoryChild;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 24-08-2017.
 */

public class AdapterAllCategory extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<Category> categoryList;

    public AdapterAllCategory(Context context, ArrayList<Category> categoryList) {
        this.mContext = context;
        this.categoryList = categoryList;
    }



    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<CategoryChild> childList = categoryList.get(groupPosition).getChildren();
        return childList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //getting datas from CategoryChild CLASS directly
        CategoryChild child = (CategoryChild) getChild(groupPosition,childPosition);

        if(convertView == null){
            LayoutInflater clayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = clayoutInflater.inflate(R.layout.row_child_category,null);
        }
        TextView tv_child = (TextView) convertView.findViewById(R.id.categoryCHILD);
        tv_child.setText(child.getName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        List<CategoryChild> childList = categoryList.get(groupPosition).getChildren();
        return childList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return categoryList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        Category category = (Category) getGroup(groupPosition);
        if (convertView == null ){
            LayoutInflater clayoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = clayoutInflater.inflate(R.layout.row_group_category,null);
        }

        TextView tvParent = (TextView) convertView.findViewById(R.id.categoryGRP);
        tvParent.setText(category.getName());

        ImageView ivCategoryIcon = (ImageView) convertView.findViewById(R.id.categoryIcon);

        String iURL = category.getIconUrl();
        Glide.with(mContext)
                .load(iURL)
                .into(ivCategoryIcon);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
