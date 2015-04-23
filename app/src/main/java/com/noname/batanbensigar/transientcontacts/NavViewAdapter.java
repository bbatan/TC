package com.noname.batanbensigar.transientcontacts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Batan Bensigar on 4/23/2015.
 */
public class NavViewAdapter extends RecyclerView.Adapter<NavViewAdapter.MenuViewHolder> {

    private LayoutInflater inflater;

    List<NavMenu> menuList = Collections.emptyList();

    public NavViewAdapter(Context context, List<NavMenu> menuItems) {
        inflater = LayoutInflater.from(context);
        this.menuList = menuItems;
    }

    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.nav_menu_item, viewGroup, false);
        MenuViewHolder menuViewHolder = new MenuViewHolder(view);

        return menuViewHolder;
    }

    @Override
    public void onBindViewHolder(MenuViewHolder viewHolder, int position) {
        NavMenu menuItem = menuList.get(position);
        viewHolder.imgIcon.setImageResource(menuItem.iconID);
        viewHolder.textTitle.setText(menuItem.menuTitle);
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        TextView textTitle;
        ImageView imgIcon;

        public MenuViewHolder(View itemView) {
            super(itemView);

            textTitle = (TextView) itemView.findViewById(R.id.menuTitle);
            imgIcon = (ImageView) itemView.findViewById(R.id.menuIcon);
        }
    }
}
