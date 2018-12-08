package swl.bwie.com.mvp181205.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import swl.bwie.com.mvp181205.R;
import swl.bwie.com.mvp181205.bean.NewsBean;

public class MyBaseAdapter extends BaseAdapter {
    Context context;
    List<NewsBean.DataBean> dataBeans;

    public MyBaseAdapter(Context context) {
        this.context = context;
        dataBeans = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return dataBeans.size();
    }

    @Override
    public NewsBean.DataBean getItem(int position) {
        return dataBeans.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if(convertView==null){
            vh = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_list, null);
            vh.item_title = convertView.findViewById(R.id.item_title);
            vh.item_data = convertView.findViewById(R.id.item_data);
            vh.item_img = convertView.findViewById(R.id.item_img);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        vh.item_title.setText(dataBeans.get(position).getTitle());
        vh.item_data.setText(dataBeans.get(position).getDate());
        ImageLoader.getInstance().displayImage(dataBeans.get(position).getThumbnail_pic_s(),vh.item_img);
        return convertView;
    }

    public void setData(List<NewsBean.DataBean> data1) {
        this.dataBeans = data1;
        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView item_title,item_data;
        ImageView item_img;
    }
}
