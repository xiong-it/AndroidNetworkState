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
     * @param type 网络类型
     */
    void onNetworkStateChanged(NetworkTypeEnum type);
}
