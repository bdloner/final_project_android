package kmitl.project.bdloner.moneygrow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder>{

    private List<Goal> listItemGoal;
    private Context context;
    private myDbAdapter dbAdapter;

    public GoalAdapter(List<Goal> listItemGoal, Context context) {
        this.listItemGoal = listItemGoal;
        this.context = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView titleGoal, startGoal, amountGoal, descGoal, dateGoal, percentGoal;
        public ConstraintLayout cardItem;
        public ProgressBar progress;


        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            cardItem = itemView.findViewById(R.id.cardItem);

            titleGoal = itemView.findViewById(R.id.txt_title);
            startGoal = itemView.findViewById(R.id.start_amount);
            amountGoal = itemView.findViewById(R.id.txt_amount);
            descGoal = itemView.findViewById(R.id.txt_desc);
            dateGoal = itemView.findViewById(R.id.txt_date);
            progress = itemView.findViewById(R.id.progressBarGoal);

            percentGoal = itemView.findViewById(R.id.percent_goal);

        }

    }

    @Override
    public GoalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_goal, parent, false);
        dbAdapter = new myDbAdapter(parent.getContext());
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final GoalAdapter.ViewHolder holder, final int position) {

        final Goal goal = listItemGoal.get(position);

        holder.titleGoal.setText(goal.getTitle_goal());
        holder.startGoal.setText(goal.getStart_goal());
        holder.amountGoal.setText(goal.getAmount_goal());
        holder.descGoal.setText(goal.getDescription_goal());
        holder.dateGoal.setText(goal.getDate_goal());

        String txtAmount = goal.getAmount_goal().replace(",", "");
        String txtStart = goal.getStart_goal().replace(",", "");
        Double result = Double.parseDouble(txtStart) / Double.parseDouble(txtAmount) * 100;

        String result_percent = String.format("%.2f", result);
        holder.percentGoal.setText("(" + String.valueOf(result_percent) + "%)");
        holder.progress.setProgress((int) Math.floor(result));

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "เพิ่มเงิน");
                menu.add(holder.getAdapterPosition(), 1, 0, "แก้ไขรายการ");
                menu.add(holder.getAdapterPosition(), 2, 0, "ลบรายการ");
            }
        });

        /*holder.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = goal.getCid();
                Intent intent = new Intent(context, AddMoneyGoal.class);
                intent.putExtra("oldId",id);
                context.startActivities(new Intent[]{intent});
                context.stopService(intent);
            }
        });*/

        /*holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MessageForDev  messageForDev = new MessageForDev();
                String id = listEvent.getEventId();
                int cnt = databaseAdapter.delete(id);
                messageForDev.Log("POS : "+ cnt);
                removeAt(position);
            }
        });*/

        /*holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = listEvent.getEventId();
                Intent intent;
                intent =  new Intent(context, EditEventActivity.class);
                intent.putExtra("oldId",id);
                context.startActivities(new Intent[]{intent});
                context.stopService(intent);

            }
        });
        holder.infoLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("RecyclerAdapeter", listEvent.getEventId());
                Intent intent;
                intent = new Intent(context, ViewEventActivity.class);
                intent.putExtra("id", listEvent.getEventId());
                context.startActivities(new Intent[]{intent});
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return listItemGoal.size();
    }

    public void removeAt(int position) {
        listItemGoal.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listItemGoal.size());
    }
}