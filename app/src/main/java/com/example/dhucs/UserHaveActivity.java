package com.example.dhucs;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.dhucs.adapter.BaseRecyclerAdapter;
import com.example.dhucs.listeners.OnItemClickListener;
import com.example.dhucs.model.Activities;
import com.example.dhucs.model.User;
import com.example.dhucs.net.Urls;
import com.example.dhucs.utils.PrefUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserHaveActivity extends AppCompatActivity
{
    private ImageView haveacback;
    private RecyclerView recyvle;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_have);
        haveacback = findViewById(R.id.haveacback);
        haveacback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        recyvle = findViewById(R.id.recyvle);
        requestData();
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                //这里获取数据的逻辑
                requestData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        recyvle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        initData();
        recyvle.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(@NonNull int position)
            {
                final Activities activities = activitiesList.get(position);
                startActivity(new Intent(UserHaveActivity.this, UserScanActivity.class).putExtra("title", activities.getTitle())
                        .putExtra("img", activities.getImage())
                        .putExtra("content", activities.getContent())
                        .putExtra("ids", activities.getId()))
                ;
            }
        });
    }

    private BaseRecyclerAdapter adapter;
    private List<Activities> activitiesList = new ArrayList<>();
    protected MaterialDialog loadingDialog;


    private void initData()
    {
        adapter = new BaseRecyclerAdapter()
        {
            @Override
            protected void onBindView(@NonNull BaseViewHolder holder, @NonNull final int position)
            {
                final Activities activities = activitiesList.get(position);
                ImageView item_img = holder.getView(R.id.item_img);
                TextView item_title = holder.getView(R.id.item_title);
                TextView item_new = holder.getView(R.id.item_new);
                TextView item_date = holder.getView(R.id.item_date);
                item_title.setText(activities.getTitle());
                item_new.setText(activities.getContent());
                if (!activities.getImage().equals(""))
                {
                    item_img.setVisibility(View.VISIBLE);
                    byte[] decodedString = Base64.decode(activities.getImage().substring(activities.getImage().indexOf(",") + 1), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    item_img.setImageBitmap(decodedByte);
                } else
                {
                    item_img.setVisibility(View.GONE);
                }
            }

            @Override
            protected int getLayoutResId(int position)
            {
                return R.layout.iten_adapter;
            }

            @Override
            public int getItemCount()
            {
                return activitiesList.size();
            }
        };
    }

    private Handler mHandler = new Handler(new Handler.Callback()
    {
        @Override
        public boolean handleMessage(@NonNull Message msg)
        {
            switch (msg.what)
            {
                case 1:
                    String string = (String) msg.obj;
                    Log.e("测试接口333", string);
                    Gson gson = new Gson();
                    activitiesList = gson.fromJson(string, new TypeToken<List<Activities>>()
                    {
                    }.getType());
                    adapter.notifyDataSetChanged();
                    break;

            }
            return true;
        }
    });

    private void requestData()
    {

        showLoadingDialog();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        User user = new User();
        user.setId(PrefUtils.getInt(this, "userId", 0));
        Gson gson = new Gson();
        String Json = gson.toJson(user);
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), Json);
        final Request request = new Request.Builder()
                .url(Urls.getAllActivityForUser)
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(Call call, IOException e)
            {
                Log.e("error", "connectFail");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException
            {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = response.body().string();
                mHandler.sendMessage(msg);
                dismissLoadingDialog();
            }
        });
    }

    protected void showLoadingDialog()
    {
        loadingDialog = new MaterialDialog.Builder(this).content(R.string.loading).progress(true, 0).build();
        if (loadingDialog != null && !loadingDialog.isShowing())
        {
            loadingDialog.show();
        }
    }

    /**
     * 隐藏loading对话框
     */
    protected void dismissLoadingDialog()
    {
        if (loadingDialog != null && loadingDialog.isShowing())
        {
            loadingDialog.dismiss();
        }
    }
}
