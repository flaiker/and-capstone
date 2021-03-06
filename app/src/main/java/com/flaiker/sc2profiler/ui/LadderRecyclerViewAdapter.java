package com.flaiker.sc2profiler.ui;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flaiker.sc2profiler.R;
import com.flaiker.sc2profiler.models.Ranking;
import com.flaiker.sc2profiler.ui.LadderFragment.OnListFragmentInteractionListener;

/**
 * Recycler view adapter for the ladder list in {@link LadderFragment}
 */
public class LadderRecyclerViewAdapter
        extends RecyclerView.Adapter<LadderRecyclerViewAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private Cursor mCursor;

    public LadderRecyclerViewAdapter(OnListFragmentInteractionListener listener) {
        mListener = listener;
    }

    void swapCursor(Cursor cursor) {
        mCursor = cursor;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ladder_rank, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mCursor.moveToPosition(position);
        Ranking item = Ranking.ofCursor(mCursor);
        holder.mItem = item;
        holder.mRankingTextView.setText(String.valueOf(position + 1));
        holder.mPlayerTextView.setText((!item.clanTag.equals("") ? "[" + item.clanTag + "] " : "") +
                item.displayName);
        holder.mRaceImageView.setImageResource(holder.mItem.race.iconId);
    }

    @Override
    public int getItemCount() {
        return mCursor != null ? mCursor.getCount() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final TextView mPlayerTextView;
        public final TextView mRankingTextView;
        public final ImageView mRaceImageView;
        public Ranking mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mPlayerTextView = (TextView) view.findViewById(R.id.ladder_player);
            mRankingTextView = (TextView) view.findViewById(R.id.ladder_rank);
            mRaceImageView = (ImageView) view.findViewById(R.id.ladder_race_icon);
            view.setOnClickListener(this);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mPlayerTextView.getText() + "'";
        }

        @Override
        public void onClick(View v) {
            mListener.onListFragmentInteraction(mItem);
        }
    }
}
