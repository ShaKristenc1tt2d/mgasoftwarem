package com.idsmanager.nativeappdemo.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/12/28.
 */

public class AppActivities {
    //通过一个List来暂存活动
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        if(activities.size()>0) {
            for (Activity activity : activities) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    }
}
