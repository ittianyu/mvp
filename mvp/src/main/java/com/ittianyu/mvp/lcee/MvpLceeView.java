package com.ittianyu.mvp.lcee;

import com.hannesdorfmann.mosby.mvp.lce.MvpLceView;

/**
 * Created by yu on 2016/11/29.
 */

public interface MvpLceeView<M> extends MvpLceView<M> {
    /**
     * Show the empty view.
     *
     * <b>The empty view default id = R.id.emptyView</b>
     */
    void showEmpty();
}
