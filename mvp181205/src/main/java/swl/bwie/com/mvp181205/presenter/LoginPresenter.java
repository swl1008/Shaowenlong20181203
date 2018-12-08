package swl.bwie.com.mvp181205.presenter;

import swl.bwie.com.mvp181205.model.IModel;
import swl.bwie.com.mvp181205.model.LoginModel;
import swl.bwie.com.mvp181205.view.IView;

public class LoginPresenter {
    private IView Miview;
    private LoginModel model;

    public LoginPresenter(IView miview) {
        Miview = miview;
        model = new LoginModel();
    }

    public void login(String username,String pwd){
        model.login(username, pwd, new IModel() {
            @Override
            public void success(Object data) {
                Miview.showData(data);
            }
        });
    }

    public void show(){
        model.show(new IModel() {
            @Override
            public void success(Object data) {
                Miview.showData(data);
            }

        });
    }


    public void onDetach(){
        if (model!=null){
            model = null;
        }
        if (Miview!=null){
            Miview = null;
        }
    }
}
