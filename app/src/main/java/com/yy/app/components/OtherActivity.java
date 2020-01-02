package com.yy.app.components;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.yy.app.R;
import com.yy.app.base.BaseActivity;


/**
 * 其他零碎知识点
 */
public class OtherActivity extends BaseActivity {


    @Override
    public void initContentViewXml() {
        setContentView(R.layout.activity_other);
    }

    @Override
    public void initView() {
        super.initView();
        Button toastBtn = findViewById(R.id.toast);
        Button snackbar = findViewById(R.id.btn_snackbar);
        FloatingActionButton btn_floatingAction = findViewById(R.id.btn_floatingAction);
        btn_floatingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "message", Snackbar.LENGTH_LONG)
                        .setAction("取消1", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("你点击了 [取消1] ");
                            }
                        })
                        .show();
            }
        });
        snackbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Snackbar.make(v, "message", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showToast("你点击了 [取消] ");
                            }
                        })
                        .show();

            }
        });
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast();
            }
        });
    }

    public void toast() {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        Button btn_toast = (Button) layout.findViewById(R.id.btn_toast);
        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("你点击了我~, 你个坏人~");
            }
        });


        TextView text = (TextView) layout.findViewById(R.id.tv_toast);
        text.setText("这就是我,自定义toast~");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();


    }


    /**
     * alertDialog
     */
    public void alertDialog() {


        // 1,
        AlertDialog.Builder builder = new AlertDialog.Builder(OtherActivity.this);
        builder.setMessage("dialog_fire_missiles")
                .setPositiveButton("fire", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        builder.create();


        // 2
        builder.setTitle("选择名字")
                .setItems(R.array.planets_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        builder.create();


        // 3, 自定义view
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_other, null))
                // Add action buttons
                .setPositiveButton("signin", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        showToast("点击了 [cancel] 按钮");
                    }
                });


    }


    /**
     * 菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.create_new:
                //                创建文件....
                return true;
            case R.id.open:
                //                打开文件
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * 上下文菜单
     *
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

    }


    /**
     * 上下文菜单的点击事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.create_new:
                //...
                return true;
            case R.id.open:
                //                ...
                return true;
            default:
                return super.onContextItemSelected(item);
        }


    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();

        // 添加点击事件
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }

    public void sp() {

        // 自己定义前缀
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        // 默认是当前应用的包名作为前缀的
        SharedPreferences sharedPref2 = getPreferences(Context.MODE_PRIVATE);

        // 存储值
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("key", "newValue");
        editor.commit();

        // 获取的时候,有默认值
        int highScore = sharedPref.getInt("key", 0);

    }


}
