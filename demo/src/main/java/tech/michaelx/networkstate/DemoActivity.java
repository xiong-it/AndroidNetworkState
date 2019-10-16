package tech.michaelx.networkstate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import tech.michaelx.networkstatelib.NetworkStateWatcher;
import tech.michaelx.networkstatelib.NetworkStateObserver;
import tech.michaelx.networkstatelib.NetworkTypeEnum;

public class DemoActivity extends AppCompatActivity implements NetworkStateObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 当前组件创建时注册网络观察者
        NetworkStateWatcher.getDefault(this).register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 当前组件销毁时注销网络观察
        NetworkStateWatcher.getDefault(this).unRegister(this);
        // 因为这是主界面，需要清理网络观察者
        NetworkStateWatcher.getDefault(this).stopWatch(this);
    }

    /**
     * 网络发生变化
     *
     * @param type 网络类型
     */
    @Override
    public void onNetworkStateChanged(NetworkTypeEnum type) {
        Toast.makeText(this, "NetworkStateChanged>>>" + type.type, Toast.LENGTH_SHORT).show();
        switch (type) {
            case NETWORK_4G:
                Toast.makeText(this, "NetworkStateChanged>>>4G", Toast.LENGTH_SHORT).show();
                break;

            case NETWORK_WIFI:
                Toast.makeText(this, "NetworkStateChanged>>>WIFI", Toast.LENGTH_SHORT).show();
                break;

            case NETWORK_NO:
                Toast.makeText(this, "NetworkStateChanged>>>无网络", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
