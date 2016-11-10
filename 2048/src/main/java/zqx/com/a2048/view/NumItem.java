package zqx.com.a2048.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import zqx.com.a2048.R;

/**
 * Created by Administrator on 2016/11/10.
 */

public class NumItem extends TextView {

    int Score;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public NumItem(Context context, int Score) {
        super(context);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(lp);
        this.Score = Score;
        setTextColor(Color.WHITE);
        setTextSize(40f);
        setBackGround();
        setGravity(Gravity.CENTER);
        setText(this.Score);
    }

    private void setBackGround() {
        switch (Score) {
            case 1:
                setBackgroundResource(R.color.c1);
                break;
            case 2:
                setBackgroundResource(R.color.c2);
                break;
            case 4:
                setBackgroundResource(R.color.c4);
                break;
            case 8:
                setBackgroundResource(R.color.c8);
                break;
            case 16:
                setBackgroundResource(R.color.c16);
                break;
            case 32:
                setBackgroundResource(R.color.c32);
                break;
            case 64:
                setBackgroundResource(R.color.c64);
                break;
            case 128:
                setBackgroundResource(R.color.c128);
                break;
            case 256:
                setBackgroundResource(R.color.c256);
                break;
            case 512:
                setBackgroundResource(R.color.c512);
                break;
            case 1024:
                setBackgroundResource(R.color.c1024);
                break;
            case 2048:
                setBackgroundResource(R.color.c2048);
                break;
            case 4096:
                setBackgroundResource(R.color.c4096);
                break;
            case 8192:
                setBackgroundResource(R.color.c8192);
                break;
            case 16384:
                setBackgroundResource(R.color.c16384);
                break;
            case 32768:
                setBackgroundResource(R.color.c32768);
                break;
            case 65536:
                setBackgroundResource(R.color.c65536);
                break;
            case 131072:
                setBackgroundResource(R.color.c131072);
                break;
            default:
                setBackgroundResource(R.color.cunknown);
        }
    }

}
