package com.dcbazar.np.homeModule.homeFragment.adaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.dcbazar.np.R;
import com.dcbazar.np.customviews.CapitalizedTextView;
import com.dcbazar.np.helpers.AppLog;
import com.dcbazar.np.homeModule.homeFragment.syncmodels.SurveyResponseModel;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Amir on 20/04/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewAdapter> implements Filterable {
    private LayoutInflater inflater;
    Context context;
    List<SurveyResponseModel> originalsurvey;
    List<SurveyResponseModel> filtersurvey;
    HomeFilter homeFilter;
    OnClickListener onClickListener;

    public HomeAdapter(Context context, List<SurveyResponseModel> survey, OnClickListener onClickListener) {
        this.context = context;
        this.originalsurvey = survey;
        this.filtersurvey = survey;
        inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @Override
    public HomeViewAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_home, parent, false);
        HomeViewAdapter homeViewAdapter = new HomeViewAdapter(view);
        return homeViewAdapter;
    }

    @Override
    public void onBindViewHolder(final HomeViewAdapter holder, final int position) {

        if (filtersurvey != null) {
            if (filtersurvey.get(position).getBackgroundImage() != null) {
                AppLog.d(filtersurvey.get(position).getBackgroundImage());
                Glide.with(context).load(filtersurvey.get(position).getBackgroundImage()).listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
//                        holder.progressBarSurveyList.setVisibility(View.VISIBLE);
                        if (e instanceof UnknownHostException) {
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
//                        holder.progressBarSurveyList.setVisibility(View.GONE);
                        return false;
                    }
                })
                        .centerCrop()
//                        .placeholder(R.drawable.default_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.ivBackground);

                /*Picasso.with(context)
                        .load(filtersurvey.get(position).getBackgroundImage())
                        .fit()
                        // .placeholder(R.drawable.user_placeholder)
                        // .error(R.drawable.user_placeholder_error)
                        .into(holder.ivBackground);*/

                holder.tvTitle.setText(filtersurvey.get(position).getName());

                //use when required
                holder.ivBackground.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickListener.onItemClicked(v, filtersurvey.get(position), filtersurvey.get(position).getId());
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        if (filtersurvey != null) {
            return filtersurvey.size();
        } else {
            return 0;
        }

    }

    @Override
    public Filter getFilter() {

        if (homeFilter == null) {
            homeFilter = new HomeFilter();
        }
        return homeFilter;
    }

    class HomeViewAdapter extends RecyclerView.ViewHolder {

        ImageView ivBackground;
        CapitalizedTextView tvTitle;
//        ProgressBar progressBarSurveyList;

        public HomeViewAdapter(View itemView) {
            super(itemView);
            ivBackground = (ImageView) itemView.findViewById(R.id.iv_item_background);
            tvTitle = (CapitalizedTextView) itemView.findViewById(R.id.tv_item_title);
//            progressBarSurveyList = (ProgressBar) itemView.findViewById(R.id.home_progress);
        }
    }

    class HomeFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            AppLog.d(constraint.length() + "");
            String filterString = constraint.toString().toUpperCase();
            int count = originalsurvey.size();
            ArrayList<SurveyResponseModel> nList = new ArrayList<>(count);
            //Filtering
            for (int i = 0; i < count; i++) {
                if (originalsurvey.get(i).getName().toUpperCase().startsWith(filterString)) {
                    SurveyResponseModel surveyModel = originalsurvey.get(i);
                    nList.add(surveyModel);
                }
            }
            results.count = nList.size();
            results.values = nList;
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                filtersurvey = (ArrayList<SurveyResponseModel>) results.values;
            }
            notifyDataSetChanged();

        }
    }

    public interface OnClickListener {
        void onItemClicked(View v, SurveyResponseModel surveyResponseModel, int id);
    }
}
