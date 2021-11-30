package com.captlu.oos;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class UploadFileVideo extends Thread {
    private String md5;
    private String fileName;
    private String randomStr;
    String filpath;
    Context context;
    private String path;
    private String mytime;
    private String filePicName;
    private String usercode;
    private VideoCallBack back;
    String name = "http://toplu911015.oss-cn-beijing.aliyuncs.com/";
    private Handler mHandler;

    public void setmHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    /**
     * @param filpath 视频文件地址
     * @param context
     */
    public UploadFileVideo(String filpath, Context context) {
        this.filpath = filpath;
        this.context = context;
        try {
            this.md5 = Base64Utils.getMd5ByFile(new File(filpath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        randomStr = java.util.UUID.randomUUID().toString();
        fileName = "video/" + randomStr + ".mp4";
        filePicName = "picture/" + randomStr + ".jpg";
    }

    public VideoCallBack getBack() {
        return back;
    }

    public void setBack(VideoCallBack back) {
        this.back = back;
    }

    public interface VideoCallBack {
        void onSuccess(String name, String picName, String md5);

        void onFailure();
    }

    @Override
    public void run() {
        String endpoint = "http://oss-cn-beijing.aliyuncs.com/";
        String accessKeyId = "LTAIetO0HivNrVSZ";
        String accessKeySecret = "17k4mo9jiYm64nM840W5C2QcWxEegv";

        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(
                accessKeyId, accessKeySecret);

        OSS oss = new OSSClient(context.getApplicationContext(), endpoint,
                credentialProvider);
        path = getBitmapsFromVideo(filpath);
        PutObjectRequest put = new PutObjectRequest("toplu911015", fileName, filpath);
        PutObjectRequest putpic = new PutObjectRequest("toplu911015", filePicName, path);
        try {
            PutObjectResult putResult = oss.putObject(put);
            PutObjectResult putResult2 = oss.putObject(putpic);
            handler.sendEmptyMessage(0x123);
            Log.d("PutObject", "UploadSuccess");
            Log.d("ETag", putResult.getETag());
            Log.d("RequestId", putResult.getRequestId());
        } catch (ServiceException e) {
            e.printStackTrace();
            if (back != null) {
                back.onFailure();
            }
        } catch (ClientException e) {
            e.printStackTrace();
            if (back != null) {
                back.onFailure();
            }
        }
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Toast.makeText(context, "uploadsuccess", Toast.LENGTH_SHORT).show();
            if (back != null) {
                back.onSuccess(name + fileName, name + filePicName, md5);
            }
            return false;
        }
    });

    public String getBitmapsFromVideo(String filpath) {
        File file = new File(filpath);
        String path = "";
        try {

            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(filpath);
            String time = retriever
                    .extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            Bitmap bitmap = retriever.getFrameAtTime(10,
                    MediaMetadataRetriever.OPTION_CLOSEST_SYNC);
            path = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + File.separator + ".jpg";
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(path);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }
}

