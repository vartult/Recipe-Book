package com.cellfishpool.util;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class VerticalSpacingItemDecor extends RecyclerView.ItemDecoration{

        private final int verticalSpaceHeight;

        public VerticalSpacingItemDecor(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {

            outRect.top = verticalSpaceHeight;
        }
    }
