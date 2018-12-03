package swl.bwie.com.shaowenlong201812033;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class WaterGroupNameLayout extends TextView {
    public WaterGroupNameLayout(Context context) {
        super(context);
    }

    public WaterGroupNameLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.WeekFlowLayout);
        int color = array.getColor(R.styleable.WeekFlowLayout_textColor,Color.BLUE);
        setTextColor(color);
        array.recycle();
    }
}
