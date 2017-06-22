package qunter.newtecdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import qunter.newtecdemo.R;
import qunter.newtecdemo.entity.NewsEntity;

/**
 * Created by Administrator on 2017/6/21.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int TOP = 0x00;
    private final int NORMAL = 0x01;


    private List<NewsEntity.StoriesBean> stotyEntity;
    private Context context;
    private RecyclerView recyclerView;
    private OnItemClickListener onItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView itemTitle;
        @BindView(R.id.item_image)
        ImageView itemImage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            itemImage = (ImageView) view.findViewById(R.id.item_image);
            itemTitle = (TextView) view.findViewById(R.id.item_title);
        }
    }

    static class ViewHolderTop extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView itemTitle;

        public ViewHolderTop(View view) {
            super(view);
            ButterKnife.bind(this,view);
            itemTitle = (TextView) view.findViewById(R.id.item_title);
        }
    }

    public NewsAdapter(Context context, List<NewsEntity.StoriesBean> stotyEntity, RecyclerView recyclerView) {
        this.context = context;
        this.stotyEntity = stotyEntity;
        this.recyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TOP) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top, parent, false);
            return new ViewHolderTop(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        holder.setIsRecyclable(false);
        if (holder instanceof ViewHolderTop) {
            final NewsEntity.StoriesBean story = stotyEntity.get(position);
            ((ViewHolderTop) holder).itemTitle.setText(story.getTitle());
        } else {
            final NewsEntity.StoriesBean story = stotyEntity.get(position);
            ((ViewHolder) holder).itemTitle.setText(story.getTitle());
            if (story.getImages() != null) {
                Glide.with(context).load(story.getImages().get(0)).into(((ViewHolder) holder).itemImage);
            } else {
                ((ViewHolder) holder).itemImage.setVisibility(View.GONE);
            }

        }
        if (onItemClickListener != null) {
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TOP;
        } else {
            return NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return stotyEntity.size();
    }

    /**
     * 点击事件接口
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * 设置点击事件方法
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}

