package tech.michaelx.networkstatelib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MichaelX
 * @version 1.0
 * @since 2019/5/22
 */
public final class NetworkStateManager {
    private List<WeakReference<NetworkStateObserver>> mObservers;
    private static NetworkStateManager sManager;
    private static BroadcastReceiver sReceiver;

    public static NetworkStateManager getDefault(Context context) {
        if (sManager == null) {
            synchronized (NetworkStateManager.class) {
                sManager = new NetworkStateManager();
                registerReceiver(context);
            }
        }
        return sManager;
    }

    private static void registerReceiver(Context context) {
        if (context == null) {
            return;
        }
        sReceiver = new NetworkStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.getApplicationContext().registerReceiver(sReceiver, filter);
    }

    /**
     * 注册观察者
     *
     * @param observer 观察者
     */
    public void register(NetworkStateObserver observer) {
        if (mObservers == null) {
            synchronized (NetworkStateManager.class) {
                mObservers = new ArrayList<>();
            }
        }
        mObservers.add(new WeakReference<>(observer));
    }

    /**
     * 注销观察者
     *
     * @param observer 观察者
     */
    public void unRegister(NetworkStateObserver observer) {
        if (mObservers == null || mObservers.isEmpty()) {
            return;
        }
        for (WeakReference<NetworkStateObserver> reference : mObservers) {
            if (reference.get() == observer && observer != null) {
                mObservers.remove(reference);
            }
        }
    }

    /**
     * 通知观察者网络变化
     */
    void post(Context context) {
        if (mObservers == null || mObservers.isEmpty()) {
            return;
        }
        for (WeakReference<NetworkStateObserver> observer : mObservers) {
            if (observer.get() != null) {
                observer.get().onNetworkStateChanged(NetworkUtils.getNetworkType(context));
            }
        }
    }

    /**
     * 清理观察者
     *
     * @param context
     */
    public void clear(Context context) {
        if (mObservers != null) {
            mObservers.clear();
            mObservers = null;
        }
        unRegisterReceiver(context);
    }

    /**
     * 注销广播接收者
     *
     * @param context
     */
    private void unRegisterReceiver(Context context) {
        if (context != null) {
            context.getApplicationContext().unregisterReceiver(sReceiver);
        }
    }
}
