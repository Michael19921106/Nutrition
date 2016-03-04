package com.zxxk.demo.nutrition.ui.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zxxk.demo.nutrition.R;
import com.zxxk.demo.nutrition.entity.Tab;
import com.zxxk.demo.nutrition.interfaces.NavigationDrawerCallbacks;
import com.zxxk.demo.nutrition.utils.L;
import com.zxxk.demo.nutrition.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-03
 * Time: 16:37
 * Description:
 */
public class NavigationFragmentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG = NavigationFragmentAdapter.class.getSimpleName();
    private List<Tab> mTabs;
    private NavigationDrawerCallbacks mCallbacks;
    private boolean isKitKatWithNavigation;
    private int mSelectionPosition = -1;
    public static final class Type {
        public static final int TYPE_HEADER = 0;
        public static final int TYPE_ITEM = 1;
        public static final int TYPE_BOTTOM_SPACE = 2;
    }
    public NavigationFragmentAdapter(boolean isKitKatWithNavigation){
        mTabs = new ArrayList<>();
        this.isKitKatWithNavigation = isKitKatWithNavigation;
    }
    public void setThemes(List<Tab> tabs){
        mTabs.clear();
        mTabs.addAll(tabs);
        notifyDataSetChanged();
    }
    public void setNavigationDrawerCallBacks(NavigationDrawerCallbacks callBacks){
        this.mCallbacks = callBacks;
    }

    @Override
    public int getItemViewType(int position) {
        if (isKitKatWithNavigation) {
            if (position == 0) {
                return Type.TYPE_HEADER;
            } else if (position == mTabs.size() + 2) {
                return Type.TYPE_BOTTOM_SPACE;
            } else {
                return Type.TYPE_ITEM;
            }
        } else {
            return position == 0 ? Type.TYPE_HEADER : Type.TYPE_ITEM;
        }

    }

    @Override
    public int getItemCount() {
        if (isKitKatWithNavigation) {
            return mTabs != null ? mTabs.size() + 3 : 3;
        } else {
            return mTabs != null ? mTabs.size() + 2 : 2;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = null;
        switch (viewType) {
            case Type.TYPE_HEADER:
                itemView = UIUtils.inflate(R.layout.nav_drawer_header, viewGroup);
                return new HeaderViewHolder(itemView);
            case Type.TYPE_ITEM:
                itemView = UIUtils.inflate(R.layout.nav_drawer_item, viewGroup);
                final ItemViewHolder holder = new ItemViewHolder(itemView, mCallbacks);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        selectPosition(holder.getPosition() - 1);
                    }
                });
                return holder;
            case Type.TYPE_BOTTOM_SPACE:
                View view = new View(viewGroup.getContext());
                view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, UIUtils.getNavigationBarHeight(viewGroup.getContext())));
                UIUtils.setAccessibilityIgnore(view);
                return new BottomViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        int viewType = getItemViewType(position);

        switch (viewType) {
            case Type.TYPE_HEADER:
                HeaderViewHolder headerViewHolder = (HeaderViewHolder) viewHolder;
                bindHeaderData(headerViewHolder, position);
                break;
            case Type.TYPE_ITEM:
                ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                bindItemData(itemViewHolder, position);
                break;
            case Type.TYPE_BOTTOM_SPACE:
                break;
            default:
                throw new IllegalArgumentException(TAG + " error view type!");
        }
    }

    private void bindHeaderData(HeaderViewHolder viewHolder, int position) {
    }

    private void bindItemData(ItemViewHolder viewHolder, int position) {
        Resources resources = viewHolder.itemView.getContext().getResources();
        if (position == 1) {
            viewHolder.tvItemName.setText(resources.getString(R.string.app_name));
        } else {
            viewHolder.tvItemName.setText(mTabs.get(position - 2).getName());

        }

        if (mSelectionPosition == position) {
            L.i(TAG, "selected item = " + position);
            viewHolder.itemView.setBackgroundColor(resources.getColor(R.color.colorPrimary));
            viewHolder.tvItemName.setTextColor(resources.getColor(R.color.white));
        } else {
            viewHolder.itemView.setBackgroundColor(resources.getColor(R.color.navigation_item_selected));
            viewHolder.tvItemName.setTextColor(resources.getColor(R.color.colorPrimary));
        }
    }

    public void selectPosition(int position) {
        int realPosition = position + 1;
        int lastPosition = mSelectionPosition;

        if (lastPosition != -1 && lastPosition != realPosition) {
            notifyItemChanged(lastPosition);
        }

        if (mSelectionPosition != realPosition) {
            mSelectionPosition = realPosition;
            notifyItemChanged(mSelectionPosition);
        }

        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        ImageView ivHeader;

        public HeaderViewHolder(View itemView) {
            super(itemView);

        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemName;

        public ItemViewHolder(View itemView, final NavigationDrawerCallbacks callBacks) {
            super(itemView);

            tvItemName = (TextView) itemView.findViewById(R.id.tvItemName);

        }
    }

    public static class BottomViewHolder extends RecyclerView.ViewHolder {
        public BottomViewHolder(View itemView) {
            super(itemView);
        }
    }
}