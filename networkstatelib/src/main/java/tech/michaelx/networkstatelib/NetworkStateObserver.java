package tech.michaelx.networkstatelib;

/**
 * @author MichaelX
 * @version 1.0
 * @since 2019/5/22
 */
public interface NetworkStateObserver {

    /**
     * 网络发生变化
     *
     * @param netType 网络类型
     */
    void onNetworkStateChanged(int netType);
}
