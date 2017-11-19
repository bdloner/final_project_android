package kmitl.project.bdloner.moneygrow.controller;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import kmitl.project.bdloner.moneygrow.init.CustomTextView;
import kmitl.project.bdloner.moneygrow.R;
import kmitl.project.bdloner.moneygrow.Screenshot;
import kmitl.project.bdloner.moneygrow.model.Goal;

public class GoalAdapter extends RecyclerView.Adapter<GoalAdapter.ViewHolder> {

    private List<Goal> listItemGoal;
    private Context context;
    private myDbAdapter dbAdapter;
    private Dialog dialog;

    public GoalAdapter(List<Goal> listItemGoal, Context context) {
        this.listItemGoal = listItemGoal;
        this.context = context;

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

        holder.imageIcon.setBackgroundResource(Integer.parseInt(goal.getImg_id()));
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

        if (result >= 100) {

            result_percent = String.valueOf(100);

            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.custom_dialog_share);
            dialog.show();

            CustomTextView tv_message = dialog.findViewById(R.id.txt_congra);
            ImageView imgCongra = dialog.findViewById(R.id.img_congra);

            tv_message.setText("ตอนนี้คุณสามารถซื้อ " + goal.getTitle_goal() + " ได้แล้ว");
            imgCongra.setBackgroundResource(Integer.parseInt(goal.getImg_id()));

            Button bt_yes = dialog.findViewById(R.id.btnYes);
            Button bt_no = dialog.findViewById(R.id.btnNo);

            bt_yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    shareGoal();
                    removeAt(position);
                    dialog.dismiss();
                }
            });
            bt_no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeAt(position);
                    dialog.dismiss();
                }
            });
        }

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "เพิ่มเงิน");
                menu.add(holder.getAdapterPosition(), 1, 0, "ลบรายการ");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItemGoal.size();
    }

    public void removeAt(int position) {
        final Goal goal = listItemGoal.get(position);

        listItemGoal.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listItemGoal.size());

        String id = goal.getCid();
        dbAdapter.delete(id);

    }

    public void shareGoal() {

        View view = dialog.getWindow().getDecorView().getRootView();

        Bitmap b = Screenshot.takescreenshot(view);
        saveBitmap(b);
        File imagePath = new File(context.getCacheDir(), "images");
        File newFile = new File(imagePath, "image.png");
        Uri contentUri = FileProvider.getUriForFile(context, "kmitl.project.bdloner.moneygrow.fileprovider", newFile);
        if (contentUri != null) {
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            shareIntent.setDataAndType(contentUri, context.getContentResolver().getType(contentUri));
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            context.startActivity(Intent.createChooser(shareIntent, "Choose an app"));
        }
    }

    private void saveBitmap(Bitmap bitmap) {
        try {
            File cachePath = new File(context.getCacheDir(), "images");
            cachePath.mkdirs();
            FileOutputStream stream = new FileOutputStream(cachePath + "/image.png");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleGoal, startGoal, amountGoal, descGoal, dateGoal, percentGoal;
        public ConstraintLayout cardItem;
        public ProgressBar progress;
        public ImageView imageIcon;


        public ViewHolder(View itemView) {
            super(itemView);

            context = itemView.getContext();

            cardItem = itemView.findViewById(R.id.cardItem);

            imageIcon = itemView.findViewById(R.id.image_icon);
            titleGoal = itemView.findViewById(R.id.txt_title);
            startGoal = itemView.findViewById(R.id.start_amount);
            amountGoal = itemView.findViewById(R.id.txt_amount);
            descGoal = itemView.findViewById(R.id.txt_desc);
            dateGoal = itemView.findViewById(R.id.txt_date);
            progress = itemView.findViewById(R.id.progressBarGoal);

            percentGoal = itemView.findViewById(R.id.percent_goal);

        }

    }
}