package swl.bwie.com.mvp181205.model;

import swl.bwie.com.mvp181205.bean.NewsBean;
import swl.bwie.com.mvp181205.bean.UserBean;
import swl.bwie.com.mvp181205.utils.NetUtil;

public class LoginModel {

    IModel iModel;

    public void login(String username, String pwd, final IModel iModel){
        NetUtil.getRequest("http://www.xieast.com/api/user/login.php?username="+username+"&password="+pwd,
                UserBean.class, new NetUtil.CallBack<UserBean>() {
                    @Override
                    public void getdata(UserBean userBean) {
                        int code = userBean.getCode();
                        if(code==100){
                            iModel.success(userBean.getMsg());
                        }else if(code==101){
                            iModel.success(userBean.getMsg());
                        }else if(code==102){
                            iModel.success(userBean.getMsg());
                        }else if(code==103){
                            iModel.success(userBean.getMsg());
                        }
                    }
                });
    }

    public void show(final IModel iModel){
        NetUtil.getRequest("http://www.xieast.com/api/news/news.php",
                NewsBean.class, new NetUtil.CallBack<NewsBean>() {
                    @Override
                    public void getdata(NewsBean newsBean) {
                        iModel.success(newsBean);
                    }

                });
    }
}
