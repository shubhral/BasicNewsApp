package app.news.agoda.com.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import javax.inject.Inject;

import app.news.agoda.com.R;
import app.news.agoda.com.util.AppUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import domain.news.agoda.com.model.NewsResponseDomain;

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<NewsResponseDomain> mNewsFeedResults;
    private NewsFeedClickListener mClickListener;

    @Inject
    public NewsListAdapter(Context context) {
        this.mContext = context;
    }

    public void setNewsFeedModel(List<NewsResponseDomain> results) {
        mNewsFeedResults = results;
    }

    public void setClickListener(NewsFeedClickListener clickListener) {
        this.mClickListener = clickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(this.mContext).inflate(R.layout.layout_news_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        NewsResponseDomain mediaEntity = mNewsFeedResults.get(position);
        viewHolder.newsTitle.setText(mediaEntity.getTitle());
        viewHolder.imageView.setImageURI(AppUtils.getImageURI(mediaEntity.getMultimedia()));
    }

    @Override
    public int getItemCount() {
        return mNewsFeedResults != null ? mNewsFeedResults.size() : 0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;
        @BindView(R.id.news_title)
        TextView newsTitle;
        @BindView(R.id.news_item_image)
        SimpleDraweeView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mView = itemView;
            this.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClickListener.listItemClicked(mNewsFeedResults.get(getAdapterPosition()));
                }
            });
        }
    }
}
