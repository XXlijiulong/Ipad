package com.example.a15828.ipad;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        ////为 RecyclerView 填充数据
        RecyclerView newsTitleRecyclerView = (RecyclerView) view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout) != null) {
            isTwoPane = true; // 可以找到news_content_layout布局时，为双页模式
        } else {
            isTwoPane = false; // 找不到news_content_layout布局时，为单页模式
        }
    }
    private void addNews(List<News> list, int image, String title, String content) {
        News news = new News(title, content);
        List<Integer> images = new ArrayList<>();
        images.add(image);
        news.setImages(images);
        list.add(news);
    }

    private List<News> getNews() {
        List<News> newsList = new ArrayList<>();
//        for (int i = news1; i <= 50; i++) {
//            News news = new News(R.drawable.apple_pic);
//            news.setTitle("This is news title " + i);
//
//            news.setContent(getRandomLengthContent("This is news content " + i + ". "));
//            newsList.add(news);
//        }
        addNews(newsList,R.drawable.news5,"等下。时光请等一下","时隔14年后，奥林匹克再次进入北京时间。");
        addNews(newsList,R.drawable.news6,"小明","时隔14年后，奥林匹克再次进入北京时间。");
        addNews(newsList,R.drawable.news7,"三宿零食铺","时隔14年后，奥林匹克再次进入北京时间。");
        return newsList;
    }

    /**
     * 随机生成新闻内容的长度，以保证每条新闻的内容差距比较大
     * @param content
     * @return
     */
    private String getRandomLengthContent(String content) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(content);
        }
        return builder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

        private List<News> mNewsList;

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView image;
            TextView newsTitleText;
            public ViewHolder(View view) {
                super(view);
                image = (ImageView) itemView.findViewById(R.id.news_image);
                newsTitleText = (TextView) view.findViewById(R.id.news_title);
            }
        }

        public NewsAdapter(List<News> newsList) {
            mNewsList = newsList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = mNewsList.get(holder.getAdapterPosition());
                    Integer firstImageResId = news.getImages().get(0);
                    if (isTwoPane) {//双页模式，则直接刷新 NewsContentFragment 中的内容
                        NewsContentFragment newsContentFragment = (NewsContentFragment)
                                getFragmentManager()
                                        .findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(firstImageResId,news.getTitle(), news.getContent());
                    } else {//单页模式，直接启动 NewsContentActivity 活动
                        NewsContentActivity.actionStart(getActivity(),firstImageResId,news.getTitle(), news.getContent());
                    }
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            News news = mNewsList.get(position);
            holder.image.setImageResource(news.getImages().get(0));
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }
    }

}
