package com.yy.app.components.listview.swipemenulistview;

import android.content.pm.ApplicationInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yy.app.R;
import com.yy.app.view.swipelistview.BaseSwipListAdapter;


/**
 * SwipListAdapter 继承 baseAdapter
 */
class AppAdapter extends BaseSwipListAdapter {

    private SimpleActivity simpleActivity;

    public AppAdapter(SimpleActivity simpleActivity) {
        this.simpleActivity = simpleActivity;
    }

    @Override
    public int getCount() {
        return simpleActivity.getAppList().size();
    }


    /**
     * 根据position获取item实例
     *
     * @param position
     * @return
     */
    @Override
    public ApplicationInfo getItem(int position) {
        return simpleActivity.getAppList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(simpleActivity.getApplicationContext(),
                    R.layout.item_list_app, null);
            new ViewHolder(convertView);
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        ApplicationInfo item = getItem(position);  // 获取实例
        holder.iv_icon.setImageDrawable(item.loadIcon(simpleActivity.getPackageManager()));
        holder.tv_name.setText(item.loadLabel(simpleActivity.getPackageManager()));
        holder.iv_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(simpleActivity, "iv_icon_click", Toast.LENGTH_SHORT).show();
            }
        });
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(simpleActivity, "iv_icon_click", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }


    // 优化ViewHolder
    class ViewHolder {
        ImageView iv_icon;
        TextView tv_name;

        public ViewHolder(View view) {
            iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            view.setTag(this);
        }
    }


    /**
     * 是否启用滑动
     *
     * @param position
     * @return
     */
    @Override
    public boolean getSwipEnableByPosition(int position) {
        if (position % 2 == 0) {
            return false;
        }
        return true;
    }
}
