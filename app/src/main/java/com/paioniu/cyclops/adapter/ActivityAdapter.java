package com.paioniu.cyclops.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.paioniu.cyclops.R;
import com.paioniu.cyclops.bean.Activity;
import java.util.List;

/**
 * Here be dragons
 * Created by Ezio on 2018/1/22 下午3:30
 */



public class ActivityAdapter extends BaseQuickAdapter<Activity, BaseViewHolder> {
    public ActivityAdapter( List data) {
        super(R.layout.item_click_view, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Activity activity) {
        helper.setText(R.id.tv, activity.getName());
        helper.setText(R.id.tv1, activity.getVenueName());
        // 加载网络图片
        //Glide.with(mContext).load(activity.getPoster()).crossFade().into((ImageView) helper.getView(R.id.imageView));
    }
}


