package com.android.util;

import java.lang.ref.SoftReference;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.android.module.Location;
import com.android.module.Order;
import com.android.transport.IActivity;

public class AppUtil {
    private static List<SoftReference<IActivity>> taskList = new CopyOnWriteArrayList<SoftReference<IActivity>>();

    public static void add(IActivity activity) {
        SoftReference<IActivity> wr = new SoftReference<IActivity>(activity);
        taskList.add(wr);
    }

    public static void remove(IActivity activity) {
        if (taskList != null) {
            for (SoftReference<IActivity> sr : taskList) {
                if (sr.get() != null && sr.get() == activity) {
                    taskList.remove(sr);
                }
            }
        }
    }

    public static void onLocationChange(Location location) {
        for (SoftReference<IActivity> sr : taskList) {
            if (sr.get() != null) {
                sr.get().onLocationChange(location);
            }
        }
    }

    public static void onWebSocketDataChange(Order info) {
        for (SoftReference<IActivity> sr : taskList) {
            if (sr.get() != null) {
                sr.get().onWebSocketDataChange(info);
            }
        }
    }

    public static void onGpsStatusChanged(boolean isOpen) {
        for (SoftReference<IActivity> sr : taskList) {
            if (sr.get() != null) {
                sr.get().onGpsStatusChanged(isOpen);
            }
        }
    }
}
