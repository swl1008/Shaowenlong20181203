package swl.bwie.com.shaowenlong201812033;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class TitleBarView extends LinearLayout {
    private Context context;
    private EditText editText;
    private ImageView imageView;
    public TitleBarView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TitleBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }
    private onButtonClientListener onButtonClientListener;
    private void init(){
        View view = View.inflate(context,R.layout.title,null);
        editText = view.findViewById(R.id.edit_title);
        imageView = view.findViewById(R.id.seach_title);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onButtonClientListener != null){
                    onButtonClientListener.onButtonClick(editText.getText().toString());
                }
            }
        });
    }

    public interface onButtonClientListener{
        void onButtonClick(String str);
    }

    public void setOnButtonClientListener(onButtonClientListener onButtonClientListener) {
        this.onButtonClientListener = onButtonClientListener;
    }
}
