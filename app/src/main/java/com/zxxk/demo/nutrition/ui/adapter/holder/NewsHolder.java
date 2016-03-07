package com.zxxk.demo.nutrition.ui.adapter.holder;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zxxk.demo.nutrition.R;
import com.zxxk.demo.nutrition.entity.ArticlesEntity;
import com.zxxk.demo.nutrition.utils.DateUtil;

/**
 * User: Michael(michael.hms@outlook.com)
 * Date: 2016-03-07
 * Time: 10:48
 * Description:
 */
public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private DisplayImageOptions displayImageOptions;
    private CardView cardView;
    ImageView ivIcon;
    TextView tvTitle, tvTime, tvRead;
    private ArticlesEntity articlesEntity;

    public NewsHolder(View itemView) {
        super(itemView);
        this.displayImageOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.yygc_default_icon)
                .showImageForEmptyUri(R.mipmap.yygc_default_icon)
                .showImageOnFail(R.mipmap.yygc_default_icon)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
        cardView = (CardView) itemView.findViewById(R.id.news_card);
        tvTitle = (TextView) itemView.findViewById(R.id.info_title_tv);
        tvRead = (TextView) itemView.findViewById(R.id.info_read_tv);
        tvTime = (TextView) itemView.findViewById(R.id.info_time_tv);
        ivIcon = (ImageView) itemView.findViewById(R.id.info_icon_iv);

        cardView.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.news_card :

                break;
        }
    }

    public void bindNewsView(ArticlesEntity entity) {
        this.articlesEntity = entity;
        ImageLoader.getInstance().displayImage(articlesEntity.getPic(), ivIcon, displayImageOptions);
        SpannableString title = new SpannableString(articlesEntity.getTitle());

        String date = DateUtil.getCurrentDateBy(Long.valueOf(articlesEntity.getCensor_time()), ".");
        date = date.split(" ")[0];
        SpannableString time = new SpannableString("发布日期：" + date);
        SpannableString read = new SpannableString("浏览：" + articlesEntity.getHits());
        title.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0, articlesEntity
                        .getTitle().length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        read.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0,
                ("浏览：").length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        time.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), 0,
                ("发布日期：").length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTitle.setText(title);

        time.setSpan(new ForegroundColorSpan(Color.parseColor("#62b914")), 5,
                ("发布日期：" + date).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvTime.setText(time);
        read.setSpan(new ForegroundColorSpan(Color.parseColor("#62b914")), 3,
                ("浏览：" + articlesEntity.getHits()).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvRead.setText(read);
    }

}