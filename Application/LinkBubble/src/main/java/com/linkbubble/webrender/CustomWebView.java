/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package com.linkbubble.webrender;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.webkit.WebView;


public class CustomWebView extends WebView {
    private OnScrollChangedCallback mOnScrollChangedCallback;
    public boolean mInterceptScrollChangeCalls = false;
    public boolean mCopyPasteContextMenuCreated = false;

    public CustomWebView(Context context) {
        super(context);
    }

    public CustomWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int newX, int newY, int oldX, int oldY)
    {
        super.onScrollChanged(newX, newY, oldX, oldY);
        //Log.d("My", "newX = " + newX + ", newY = " + newY + ", oldX = " + oldX + ", oldY = " + oldY);
        if ((mInterceptScrollChangeCalls || 0 == newY) && mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(newY, oldY);
        }
    }

    public void setOnScrollChangedCallback(final OnScrollChangedCallback onScrollChangedCallback)
    {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }


    public interface OnScrollChangedCallback
    {
        void onScroll(int newY, int oldY);
    }
}
