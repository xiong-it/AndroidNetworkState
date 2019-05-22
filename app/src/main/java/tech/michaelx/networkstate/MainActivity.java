package tech.michaelx.networkstate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tech.michaelx.networkstatelib.NetworkStateManager;
import tech.michaelx.networkstatelib.NetworkStateObserver;

public class MainActivity extends AppCompatActivity implements NetworkStateObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 当前组件创建时注册网络观察者
        NetworkStateManager.getDefault(this).register(this);
    }

    /**
     * 网络发生变化
     *
     * @param netType 网络类型
     */
    @Override
    public void onNetworkStateChanged(int netType) {
        // TODO
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 当前组件销毁时注销网络观察
        NetworkStateManager.getDefault(this).unRegister(this);
        // 因为这是主界面，需要清理网络观察者
        NetworkStateManager.getDefault(this).clear(this);
    }
}
