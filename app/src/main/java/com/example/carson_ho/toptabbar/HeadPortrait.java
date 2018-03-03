package com.example.carson_ho.toptabbar;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by newlife on 2018/2/23.
 */

public class HeadPortrait extends Activity implements View.OnClickListener{
    Button takePhoto;
    Bitmap photo;
    String picPath;
    Button capture;

    private static final int REQUEST_TAKE_PHOTO_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
// TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_portrait);
        takePhoto = (Button) findViewById(R.id.btn_ChooseFromAlbum);
        capture = (Button) findViewById(R.id.btn_takePhoto);
        takePhoto.setOnClickListener(this);
        capture.setOnClickListener(this);
    }


    @Override
    public void onClick(View viewid) {
        switch (viewid.getId()) {
            case R.id.btn_takePhoto: {// 打开相机
                String state = Environment.getExternalStorageState();// 获取内存卡可用状态
                if (state.equals(Environment.MEDIA_MOUNTED)) {
// 内存卡状态可用
                    Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                    startActivityForResult(intent, 1);
                } else {
// 不可用
                    Toast.makeText(HeadPortrait.this, "内存不可用", Toast.LENGTH_LONG)
                            .show();
                }
                break;
            }
            case R.id.btn_ChooseFromAlbum: {// 打开相册
                if (ContextCompat.checkSelfPermission(HeadPortrait.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限，REQUEST_TAKE_PHOTO_PERMISSION是自定义的常量
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_TAKE_PHOTO_PERMISSION);
                } else {
                    //有权限，直接拍照
//            takePhoto();
                    //调用相册
                    Intent i = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
// 设定结果返回
                    startActivityForResult(i, 2);
                }
// 打开本地相册

                break;
            }
            default:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case 1:
// 两种方式 获取拍好的图片
                    if (data.getData() != null || data.getExtras() != null) { // 防止没有返回结果
                        Uri uri = data.getData();
                        if (uri != null) {
                            this.photo = BitmapFactory.decodeFile(uri.getPath()); // 拿到图片
                        }
                        if (photo == null) {
                            Bundle bundle = data.getExtras();
                            if (bundle != null) {
                                photo = (Bitmap) bundle.get("data");
                                FileOutputStream fileOutputStream = null;
                                try {
// 获取 SD 卡根目录 生成图片并
                                    String saveDir = Environment
                                            .getExternalStorageDirectory()
                                            + "/dhj_Photos";
// 新建目录
                                    File dir = new File(saveDir);
                                    if (!dir.exists())
                                        dir.mkdir();
// 生成文件名
                                    SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");
                                    String filename = "MT" + (t.format(new Date())) + ".jpg";
// 新建文件
                                    File file = new File(saveDir, filename);
// 打开文件输出流
                                    fileOutputStream = new FileOutputStream(file);
// 生成图片文件
                                    this.photo.compress(Bitmap.CompressFormat.JPEG,
                                            100, fileOutputStream);
// 相片的完整路径
                                    this.picPath = file.getPath();
                                    ImageView imageView = (ImageView) findViewById(R.id.image);
                                    imageView.setImageBitmap(this.photo);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                } finally {
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                                Toast.makeText(getApplicationContext(), "获取到了",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "找不到图片",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    break;
                case 2: {
//打开相册并选择照片，这个方式选择单张
// 获取返回的数据，这里是android自定义的Uri地址
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
// 获取选择照片的数据视图
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
// 从数据视图中获取已选择图片的路径
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    cursor.close();
// 将图片显示到界面上
                    ImageView imageView = (ImageView) findViewById(R.id.image);
                    imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                    break;
                }
                default:
                    break;
            }
        }
    }
}
