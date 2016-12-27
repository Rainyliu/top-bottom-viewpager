package com.rainy.topbottomviewpager;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.imagezoom.ImageViewTouch;
import com.rainy.topbottomviewpager.bean.FeedItem;
import com.rainy.topbottomviewpager.bean.TagItem;
import com.rainy.topbottomviewpager.constants.AppConstants;
import com.rainy.topbottomviewpager.utils.EffectUtil;
import com.rainy.topbottomviewpager.utils.FileUtils;
import com.rainy.topbottomviewpager.utils.ImageUtils;
import com.rainy.topbottomviewpager.utils.StringUtils;
import com.rainy.topbottomviewpager.utils.TimeUtils;
import com.rainy.topbottomviewpager.view.LabelSelector;
import com.rainy.topbottomviewpager.view.LabelView;
import com.rainy.topbottomviewpager.view.MyHighlightView;
import com.rainy.topbottomviewpager.view.MyImageViewDrawableOverlay;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import de.greenrobot.event.EventBus;
import it.sephiroth.android.library.widget.HListView;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

/**
 * 图片处理界面
 */
public class AddLableActivity extends CameraBaseActivity {

    //滤镜图片
    @InjectView(R.id.gpuimage)
    GPUImageView mGPUImageView;
    //绘图区域
    @InjectView(R.id.drawing_view_container)
    ViewGroup drawArea;
    //工具区
    @InjectView(R.id.list_tools)
    HListView bottomToolBar;
    @InjectView(R.id.text_btn)
    TextView labelBtn;
    @InjectView(R.id.toolbar_area)
    ViewGroup toolArea;
    private MyImageViewDrawableOverlay mImageView;
    private LabelSelector labelSelector;

    //当前图片
    private Bitmap currentBitmap;
    //用于预览的小图片
    private Bitmap smallImageBackgroud;
    //小白点标签
    private LabelView emptyLabelView;

    private List<LabelView> labels = new ArrayList<LabelView>();

    //标签区域
    private View commonLabelArea;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lable);
        ButterKnife.inject(this);
        EffectUtil.clear();
        initView();
        initEvent();
//        initStickerToolBar();


    }

    private void initView() {
        //添加标签选择器
        RelativeLayout.LayoutParams rparams = new RelativeLayout.LayoutParams(App.getApp().getScreenWidth(), App.getApp().getScreenWidth());
        labelSelector = new LabelSelector(this);
        labelSelector.setLayoutParams(rparams);
        drawArea.addView(labelSelector);
        labelSelector.hide();

        //初始化空白标签
        emptyLabelView = new LabelView(this);
        emptyLabelView.setEmpty();
        EffectUtil.addLabelEditable(mImageView,drawArea,emptyLabelView,mImageView.getWidth()/2,mImageView.getWidth()/2);
        emptyLabelView.setVisibility(View.INVISIBLE);

        //初始化推荐标签栏
        commonLabelArea = LayoutInflater.from(AddLableActivity.this).inflate(R.layout.view_label_bottom,null);
        commonLabelArea.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        toolArea.addView(commonLabelArea);
        commonLabelArea.setVisibility(View.GONE);

    }

    private void initEvent() {
        labelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomToolBar.setVisibility(View.GONE);
                labelSelector.showToTop();
                commonLabelArea.setVisibility(View.VISIBLE);
            }
        });
        labelSelector.setTxtClicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTextActivity.openTextEdit(AddLableActivity.this, "", 10, AppConstants.ACTION_EDIT_LABEL);
            }
        });
        labelSelector.setAddrClicked(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTextActivity.openTextEdit(AddLableActivity.this,"",10, AppConstants.ACTION_EDIT_LABEL_POI);
            }
        });

        mImageView.setOnDrawableEventListener(wpEditListener);
        mImageView.setSingleTapListener(new ImageViewTouch.OnImageViewTouchSingleTapListener(){

            @Override
            public void onSingleTapConfirmed() {
                emptyLabelView.updateLocation((int) mImageView.getmLastMotionScrollX(),
                        (int) mImageView.getmLastMotionScrollY());
                emptyLabelView.setVisibility(View.VISIBLE);
                labelSelector.showToTop();
                drawArea.postInvalidate();
            }
        });

        labelSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                labelSelector.hide();
                emptyLabelView.updateLocation((int) labelSelector.getmLastTouchX(),
                        (int) labelSelector.getmLastTouchY());
                emptyLabelView.setVisibility(View.VISIBLE);
            }
        });

        titleBar.setRightBtnOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savePicture();
            }
        });
    }

    //保存图片
    private void savePicture(){
        //加滤镜
        final Bitmap newBitmap = Bitmap.createBitmap(mImageView.getWidth(), mImageView.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(newBitmap);
        RectF dst = new RectF(0, 0, mImageView.getWidth(), mImageView.getHeight());
        try {
            cv.drawBitmap(mGPUImageView.capture(), null, dst, null);
        } catch (InterruptedException e) {
            e.printStackTrace();
            cv.drawBitmap(currentBitmap, null, dst, null);
        }
        //加贴纸水印
//        EffectUtil.applyOnSave(cv, mImageView);

        new SavePicToFileTask().execute(newBitmap);
    }

    private class SavePicToFileTask extends AsyncTask<Bitmap,Void,String> {
        Bitmap bitmap;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showProgressDialog("图片处理中...");
        }

        @Override
        protected String doInBackground(Bitmap... params) {
            String fileName = null;
            try {
                bitmap = params[0];

                String picName = TimeUtils.dtFormat(new Date(), "yyyyMMddHHmmss");
                fileName = ImageUtils.saveToFile(FileUtils.getInst().getPhotoSavedPath() + "/"+ picName, false, bitmap);

            } catch (Exception e) {
                e.printStackTrace();
                toast("图片处理错误，请退出相机并重试", Toast.LENGTH_LONG);
            }
            return fileName;
        }

        @Override
        protected void onPostExecute(String fileName) {
            super.onPostExecute(fileName);
            dismissProgressDialog();
            if (StringUtils.isEmpty(fileName)) {
                return;
            }

            //将照片信息保存至sharedPreference
            //保存标签信息
            List<TagItem> tagInfoList = new ArrayList<TagItem>();
            for (LabelView label : labels) {
                tagInfoList.add(label.getTagInfo());
            }

            //将图片信息通过EventBus发送到MainActivity
            FeedItem feedItem = new FeedItem(tagInfoList,fileName);
            EventBus.getDefault().post(feedItem);
            CameraManager.getInst().close();
        }
    }


    private MyImageViewDrawableOverlay.OnDrawableEventListener wpEditListener = new MyImageViewDrawableOverlay.OnDrawableEventListener(){

        @Override
        public void onFocusChange(MyHighlightView newFocus, MyHighlightView oldFocus) {

        }

        @Override
        public void onDown(MyHighlightView view) {

        }

        @Override
        public void onMove(MyHighlightView view) {

        }

        @Override
        public void onClick(MyHighlightView view) {
            labelSelector.hide();
        }

        @Override
        public void onClick(final LabelView label) {
            if (label.equals(emptyLabelView)) {
                return;
            }
            alert("温馨提示", "是否需要删除该标签！", "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EffectUtil.removeLabelEditable(mImageView, drawArea, label);
                    labels.remove(label);
                }
            }, "取消", null);
        }
    };

    //添加标签
    private void addLabel(TagItem tagItem) {
        labelSelector.hide();
        emptyLabelView.setVisibility(View.INVISIBLE);
        if (labels.size() >= 5) {
            alert("温馨提示", "您只能添加5个标签！", "确定", null, null, null, true);
        } else {
            int left = emptyLabelView.getLeft();
            int top = emptyLabelView.getTop();
            if (labels.size() == 0 && left == 0 && top == 0) {
                left = mImageView.getWidth() / 2 - 10;
                top = mImageView.getWidth() / 2;
            }
            LabelView label = new LabelView(AddLableActivity.this);
            label.init(tagItem);
            EffectUtil.addLabelEditable(mImageView, drawArea, label, left, top);
            labels.add(label);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        labelSelector.hide();
        super.onActivityResult(requestCode, resultCode, data);
        if (AppConstants.ACTION_EDIT_LABEL== requestCode && data != null) {
            String text = data.getStringExtra(AppConstants.PARAM_EDIT_TEXT);
            if(StringUtils.isNotEmpty(text)){
                TagItem tagItem = new TagItem(AppConstants.POST_TYPE_TAG,text);
                addLabel(tagItem);
            }
        }else if(AppConstants.ACTION_EDIT_LABEL_POI== requestCode && data != null){
            String text = data.getStringExtra(AppConstants.PARAM_EDIT_TEXT);
            if(StringUtils.isNotEmpty(text)){
                TagItem tagItem = new TagItem(AppConstants.POST_TYPE_POI,text);
                addLabel(tagItem);
            }
        }
    }



}
