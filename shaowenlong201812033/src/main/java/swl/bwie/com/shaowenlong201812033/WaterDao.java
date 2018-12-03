package swl.bwie.com.shaowenlong201812033;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class WaterDao {
    private static WaterDao instance;
    private Context context;
    private final SqliteHelper helper;
    private final SQLiteDatabase database;

    public WaterDao(Context context) {
        this.context = context;
        helper = new SqliteHelper(context);
        database = helper.getReadableDatabase();
    }

    public static WaterDao getInstance(Context context) {
        if (instance == null){
            instance = new WaterDao(context);
        }
        return instance;
    }
    //添加
    public void add(String name,String uuid){
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("uuid",uuid);
        database.insert("waters",null,values);
    }
    //单个删除
    public void delete(String uuid){
        database.delete("waters","uuid = ?",new String[]{uuid});
    }
    //全部删除
    public void delAll(){
        database.delete("waters",null,null);
    }
    //查询
    public List<WaterBean> select(){
        List<WaterBean> list = new ArrayList<>();
        Cursor query = database.query("waters", null, null, null, null, null, null);
        if (query != null){
            while(query.moveToNext()){
                String name = query.getString(query.getColumnIndex("name"));
                String uuid = query.getString(query.getColumnIndex("uuid"));
                WaterBean waterBean = new WaterBean(name,uuid);
                list.add(waterBean);
            }
        }
        return list;
    }
}
