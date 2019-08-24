package me.rzknairb.oeconomicis.views.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.rzknairb.domain.entities.History;
import me.rzknairb.oeconomicis.R;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {

    private Context context;
    private List<History> list;

    public HistoryAdapter(Context context, List<History> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_history, parent, false);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        History history = list.get(position);

        holder.amount.setText(String.valueOf(history.getAmount()));
        holder.category.setText(history.getCategory_name());
        holder.description.setText(history.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<History> histories) {
        this.list = histories;
        notifyDataSetChanged();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_history_category)
        TextView category;
        @BindView(R.id.item_history_description)
        TextView description;
        @BindView(R.id.item_history_amount)
        TextView amount;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
