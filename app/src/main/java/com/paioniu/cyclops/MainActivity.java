package com.paioniu.cyclops;

import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.GeneralBasicParams;
import com.baidu.ocr.sdk.model.GeneralResult;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.paioniu.cyclops.adapter.ActivityAdapter;
import com.paioniu.cyclops.bean.ORCRep;
import com.paioniu.cyclops.bean.PNRep;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionYes;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RationaleListener;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import top.zibin.luban.Luban;

public class MainActivity extends AppCompatActivity {
    //TAG
    private static final String TAG = "Ezio";
    //定义请求码常量
    private static final int REQUEST_CODE_CHOOSE = 23;

    private static final int REQUEST_CODE_PERMISSION_SINGLE = 100;

    private static final int REQUEST_CODE_PERMISSION_MULTI = 101;

    @BindView(R.id.tv_hi) TextView tvHi;
    @BindView(R.id.bt_photo) Button btPhoto;
    @BindView(R.id.imageView) ImageView imageView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private ActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ActivityAdapter(null);
        adapter.openLoadAnimation();
        mRecyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.tv_hi) void sayHi() {
        Toast.makeText(this, tvHi.getText(), Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.bt_photo) void submit() {

        AndPermission.with(this)
            .requestCode(REQUEST_CODE_PERMISSION_SINGLE)
            .permission(Permission.STORAGE)
            .callback(this)
            // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框；
            // 这样避免用户勾选不再提示，导致以后无法申请权限。
            // 你也可以不设置。
            .rationale(new RationaleListener() {
                @Override
                public void showRequestPermissionRationale(int requestCode, Rationale rationale) {
                    // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                    AndPermission.rationaleDialog(MainActivity.this, rationale).show();
                }
            }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
            getTextByORC(mSelected.get(0));
        }
    }

    private void getTextByORC(Uri uri) {
        String photo = getImgPathFromUri(uri);
        Glide.with(this).load(uri).into(imageView);
        tvHi.setText("压缩图片...");
        //压缩
        Flowable.just(photo)
            .observeOn(Schedulers.io())
            .map(file -> {
                // 同步方法直接返回压缩后的文件
                File file1 = Luban.with(MainActivity.this).load(photo).get().get(0);

                Log.e(TAG, "LuBan map  ");
                return file1;
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(file -> {
                Log.e(TAG, "LuBan onNext  ");
                getTextByORC0(file.getAbsolutePath());
            });
    }

    private void getTextByORC0(String filePath) {
        tvHi.setText("识别图片文字...");
        // 通用文字识别参数设置
        GeneralBasicParams param = new GeneralBasicParams();
        param.setDetectDirection(true);
        param.setImageFile(new File(filePath));

        // 调用通用文字识别服务
        OCR.getInstance().recognizeGeneralBasic(param, new OnResultListener<GeneralResult>() {
            @Override
            public void onResult(GeneralResult result) {
                // 调用成功，返回GeneralResult对象
                Log.e(TAG, "GeneralResult  " + result.getJsonRes());
                ORCRep rep = new Gson().fromJson(result.getJsonRes(), ORCRep.class);
                tvHi.setText(result.getJsonRes());
                getActivityInfo(rep);
            }

            @Override
            public void onError(OCRError error) {
                // 调用失败，返回OCRError对象
            }
        });
    }

    private void getActivityInfo(ORCRep rep) {
        tvHi.setText("查询中...");
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < rep.getWords_result_num(); i++) {
            Pair<String, String> pair = getParams(i, rep.getWords_result().get(i));
            if (pair != null) {
                params.put(pair.first, pair.second);
            }
        }
        getActivityInfo0(params);
    }

    private void getActivityInfo0(Map<String, String> params) {

        String url = "http://m.beta.piaoniu.com/api/v2/activities";
        OkGo.<String>get(url)
            .params("keyword", params.get("activityName"))
            .converter(new StringConvert())
            .adapt(new ObservableResponse<>())
            .subscribe(stringResponse -> {
                PNRep rep = new Gson().fromJson(stringResponse.body(), PNRep.class);
                Log.e(TAG, "HTTPResult  " + rep.getTotalNum());

                adapter.setNewData(rep.getData());
            });
    }

    private Pair<String, String> getParams(int index, ORCRep.WordsResultBean words) {

        String key = null;
        String value;
        if (index == 0) {
            key = "activityName";
            value = words.getWords().replace("：", "").trim();
        } else {
            //日期 场馆 地址
            if (words.getWords().contains("日期") || words.getWords().contains("Date")) {
                key = "date";
            }
            if (words.getWords().contains("场馆") || words.getWords().contains("Venue")) {
                key = "venue";
            }
            if (words.getWords().contains("票价") || words.getWords().contains("Price")) {
                key = "price";
            }
            if (words.getWords().contains("地址") || words.getWords().contains("ADD")) {
                key = "add";
            }
            if (words.getWords().contains(")")) {
                value = words.getWords().split("\\)")[1].replaceAll("：", "").trim();
            } else {
                value = words.getWords().replaceAll("：", "").trim();
            }
        }
        if (key != null) {
            return Pair.create(key, value);
        }
        return null;
    }

    /**
     * <p>权限全部申请成功才会回调这个方法，否则回调失败的方法。</p>
     *
     * @param grantedPermissions AndPermission回调过来的申请成功的权限。
     */
    @PermissionYes(REQUEST_CODE_PERMISSION_SINGLE)
    private void getSingleYes(@NonNull List<String> grantedPermissions) {

        Matisse
            .from(MainActivity.this)
            .choose(MimeType.allOf())//照片视频全部显示
            .countable(true)//有序选择图片
            .maxSelectable(1)//最大选择数量
            .gridExpectedSize(360)//图片显示表格的大小getResources()
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//图像选择和预览活动所需的方向。
            .thumbnailScale(0.85f)//缩放比例
            .theme(R.style.Matisse_Zhihu)//主题  暗色主题 R.style.Matisse_Dracula
            .imageEngine(new GlideEngine())//加载方式
            .forResult(REQUEST_CODE_CHOOSE);//请求码
    }

    private String getImgPathFromUri(Uri imgUri) {
        if (Build.VERSION.SDK_INT >= 19) {
            if (DocumentsContract.isDocumentUri(this, imgUri)) {
                // document 类型的 Uri 通过document id 处理
                return getPathByDocument(imgUri);
            } else if ("content".equalsIgnoreCase(imgUri.getScheme())) {
                // content 类型的 Uri 使用普通方式处理
                return getImgPath(imgUri, null);
            } else if ("file".equalsIgnoreCase(imgUri.getScheme())) {
                // file 类型的 Uri 直接获取图片路径即可
                return imgUri.getPath();
            }
            return null;
        } else {
            return getImgPath(imgUri, null);
        }
    }

    private String getPathByDocument(Uri imgUri) {
        String docId = DocumentsContract.getDocumentId(imgUri);
        if ("com.android.providers.media.documents".equals(imgUri.getAuthority())) {
            // MediaProvider
            String id = docId.split(":")[1];
            String selection = MediaStore.Images.Media._ID + "=" + id;
            return getImgPath(imgUri, selection);
        } else if ("com.android.providers.downloads.documents".equals(imgUri.getAuthority())) {
            // DownloadsProvider
            Uri contentUri = ContentUris.withAppendedId(Uri.parse
                ("content://downloads/public_downloads"), Long.valueOf(docId));
            return getImgPath(contentUri, null);
        } else if ("com.android.externalstorage.documents".equals(imgUri.getAuthority())) {
            // ExternalStorageProvider
            if ("primary".equalsIgnoreCase(docId.split(":")[0])) {
                return Environment.getExternalStorageDirectory() + "/" + docId.split(":")[1];
            } else {
                return null;
            }
        }
        return null;
    }

    private String getImgPath(Uri imgUri, String selection) {
        String path = null;
        // 通过 Uri 和 selection 来获取真实的图片路径
        Cursor cursor = getContentResolver().query(imgUri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
}
