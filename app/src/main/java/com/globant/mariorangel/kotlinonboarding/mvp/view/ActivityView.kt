package com.globant.mariorangel.kotlinonboarding.mvp.view

import android.app.Activity
import android.content.Context
import java.lang.ref.WeakReference

@Suppress("DEPRECATION")
open class ActivityView(activity: Activity) {
    private val activityRef: WeakReference<Activity> = WeakReference(activity)

    val activity: Activity?
        get() = activityRef.get()

    val context: Context?
        get() = activity

    val fragmentManager: android.app.FragmentManager?
        get() {
            val activity = activity
            return activity?.fragmentManager
        }

}