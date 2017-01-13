package com.ittianyu.mvp.lcee;

//import com.hannesdorfmann.mosby.mvp.R;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by yu on 2016/11/28.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Lcee {
    //@android.support.annotation.IdRes
    public @android.support.annotation.IdRes int loadingViewId() default 0;
    public @android.support.annotation.IdRes int contentViewId() default 0;
    public @android.support.annotation.IdRes int errorViewId() default 0;
    public @android.support.annotation.IdRes int emptyViewId() default 0;
}
