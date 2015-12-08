package com.zyk.collapsibletextview;

import android.widget.RelativeLayout;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 可折叠显示文字
 * 该类使用RelativeLayout封装了TextView和ImageView
 * 对TextView/ImageView的设置将使用getTextView()/getImageView()
 * 得到TextView/ImageView对象后使用代码设置。
 * 切记  其中对TextView的setText(..)和setMaxLines(..)必须调用该类的方法
 *
 * @author 朱玉坤
 */
public class CollapsibleTextView extends RelativeLayout {

    private Context mContext;
    private TextView mTextView;
    private ImageView mImageView;
    private int mMaxLines = 3;
    private boolean isFirst = true;
    private boolean imagestatus = true;
    private Bitmap downBitmap, upBitmap;

    public CollapsibleTextView(Context context, AttributeSet attrs,
                               int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CollapsibleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CollapsibleTextView(Context context) {
        super(context);
        init(context);
    }

    public TextView getTextView() {
        return mTextView;
    }

    public ImageView getImageView() {
        return mImageView;
    }

    private void init(Context context) {
        mContext = context;
        mTextView = new TextView(context);
        mTextView.setId(1);
        mTextView.setEllipsize(TextUtils.TruncateAt.END);
        mTextView.setMaxLines(3);
        RelativeLayout.LayoutParams lp1 = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        lp1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        mTextView.setLayoutParams(lp1);
        this.addView(mTextView);

        mImageView = new ImageView(context);
        mImageView.setId(2);

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imagestatus) {
                    if (upBitmap != null) {
                        mImageView.setImageBitmap(upBitmap);
                    }
                    mTextView.setEllipsize(null); // 展开
                    mTextView.setSingleLine(false);
                } else {
                    if (downBitmap != null) {
                        mImageView.setImageBitmap(downBitmap);
                        ;
                    }
                    mTextView.setEllipsize(TextUtils.TruncateAt.END); // 收缩
                    mTextView.setMaxLines(mMaxLines);
                }
                imagestatus = !imagestatus;
            }
        });
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp2.addRule(RelativeLayout.BELOW, mTextView.getId());
        mImageView.setLayoutParams(lp2);
        this.addView(mImageView);
        mImageView.setVisibility(View.GONE);

    }


    public void setText(CharSequence text) {
        isFirst = true;
        mTextView.setText(text);
        //通过设置MaxLines比设置的值+1来判断内容是否超过了设置的最大行
        //暂没想到更好的办法
        mTextView.setMaxLines(mMaxLines + 1);
        ViewTreeObserver vto = mTextView.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (isFirst) {
                    isFirst = false;
                    int count = mTextView.getLineCount();
                    if (count >= (mMaxLines + 1)) {
                        mImageView.setVisibility(View.VISIBLE);
                    }
//					System.out.println("count:"+count+"  mMaxLines:"+mMaxLines);
                    mTextView.setMaxLines(mMaxLines);
                }
                return true;
            }
        });
    }

    /**
     * 设置最大行数，如果TextView的显示内容超过设置的最大行则会显示显示更多按钮
     *
     * @param maxlines
     */
    public void setMaxLines(int maxlines) {
        mTextView.setMaxLines(maxlines);
        mMaxLines = maxlines;
    }

    /**
     * 设置下拉和收起时的图片
     *
     * @param downBitmap 下拉时的图片
     * @param upBitmap   收起时的图片
     */
    public void setImage(Bitmap downBitmap, Bitmap upBitmap) {
        if (downBitmap == null || upBitmap == null) {
            return;
        }
        this.downBitmap = downBitmap;
        this.upBitmap = upBitmap;
        mImageView.setImageBitmap(downBitmap);
    }

    /**
     * 设置显示更多和收起时的图片
     *
     * @param down 显示更多时的图片
     * @param up   收起时的图片
     */
    public void setImage(int down, int up) {
        if (down == 0 || up == 0) {
            return;
        }
        this.downBitmap = BitmapFactory.decodeResource(mContext.getResources(), down);
        this.upBitmap = BitmapFactory.decodeResource(mContext.getResources(), up);
        mImageView.setImageBitmap(downBitmap);
    }

}
