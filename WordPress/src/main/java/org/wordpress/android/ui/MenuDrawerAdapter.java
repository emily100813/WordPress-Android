package org.wordpress.android.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.wordpress.android.R;

import java.util.ArrayList;
import java.util.List;

public class MenuDrawerAdapter extends BaseAdapter {

    private final LayoutInflater mInflater;
    private final ArrayList<MenuDrawerItem> mItems = new ArrayList<MenuDrawerItem>();

    public MenuDrawerAdapter(Context context) {
        super();
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    void setItems(List<MenuDrawerItem> visibleItems) {
        mItems.clear();
        mItems.addAll(visibleItems);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final MenuViewHolder holder;
        if (convertView == null || !(convertView.getTag() instanceof MenuViewHolder)) {
            convertView = mInflater.inflate(R.layout.menu_drawer_row, parent, false);
            holder = new MenuViewHolder(convertView);
        } else {
            holder = (MenuViewHolder) convertView.getTag();
        }

        final MenuDrawerItem item = mItems.get(position);
        int badgeCount = item.getBadgeCount();

        holder.txtTitle.setText(item.getTitleRes());
        holder.imgIcon.setImageResource(item.getIconRes());

        if (badgeCount > 0) {
            holder.txtBadge.setVisibility(View.VISIBLE);
            holder.txtBadge.setText(String.valueOf(badgeCount));
        } else {
            holder.txtBadge.setVisibility(View.GONE);
        }

        if (item.isSelected()) {
            convertView.setBackgroundResource(R.color.md__background_selected);
        } else {
            convertView.setBackgroundResource(0);
        }

        return convertView;
    }

    private static class MenuViewHolder {
        final TextView txtTitle;
        final TextView txtBadge;
        final ImageView imgIcon;

        MenuViewHolder(View view) {
            txtTitle = (TextView) view.findViewById(R.id.menu_row_title);
            txtBadge = (TextView) view.findViewById(R.id.menu_row_badge);
            imgIcon = (ImageView) view.findViewById(R.id.menu_row_icon);
        }
    }
}