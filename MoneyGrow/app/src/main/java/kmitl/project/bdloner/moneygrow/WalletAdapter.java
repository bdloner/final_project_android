package kmitl.project.bdloner.moneygrow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by BDLoneR on 16/11/2560.
 */

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.ViewHolder> {

    private List<Wallet> listItemWallet;
    private Context context;
    private myDbAdapter dbAdapter;
    private int type = 0;
//    public int type;

    /*public WalletAdapter(int type)
    {
        this.type = type;
    }*/

    public WalletAdapter(List<Wallet> listItemWallet, Context context) {
        this.listItemWallet = listItemWallet;
        this.context = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageIcon;
        public CustomTextView catTitleCard, dateCard, descCard, amountCard;
        public ConstraintLayout cardItem;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            cardItem = itemView.findViewById(R.id.card_item);

            imageIcon = itemView.findViewById(R.id.image_icon);
            catTitleCard = itemView.findViewById(R.id.cat_title_card);
            dateCard = itemView.findViewById(R.id.date_card);
            descCard = itemView.findViewById(R.id.desc_card);
            amountCard = itemView.findViewById(R.id.amount_card);

        }

    }

    @Override
    public WalletAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_wallet, parent, false);
        dbAdapter = new myDbAdapter(parent.getContext());

        return new WalletAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(final WalletAdapter.ViewHolder holder, final int position) {

        final Wallet wallet = listItemWallet.get(position);
        int income = 0, expenses = 0;

        holder.imageIcon.setImageResource(Integer.parseInt(wallet.getImage_id_wallet()));
        holder.catTitleCard.setText(wallet.getCat_name_wallet());

        check(position);

        if(type == 0){
            holder.amountCard.setTextColor(Color.parseColor("#FF009C1D"));

            String in = wallet.getAmount_wallet();
            in = in.replace("฿ ", "");

            SharedPreferences sp = context.getSharedPreferences("PREF_NAME", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("income", in);
            editor.commit();

            /*Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("income", income);
            context.startActivity(intent);*/

        } else {
            holder.amountCard.setTextColor(Color.parseColor("#FFD1131C"));

            String ex = wallet.getAmount_wallet();
            ex = ex.replace("฿ ", "");

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("expenses", ex);
            editor.commit();

            /*Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("expenses", expenses);
            *//*context.startActivity(intent);*/
        }


        /*if(value == 0){
            holder.amountCard.setTextColor(Color.parseColor("#FF009C1D"));
        } else {
            holder.amountCard.setTextColor(Color.parseColor("#FFD1131C"));
        }*/

        /*if (wallet.getCat_name_wallet().equals("เงินเดือน")){
            holder.amountCard.setTextColor(Color.parseColor("#FF009C1D"));
        }*/

        String strDateCard = wallet.getDate_wallet();
        String[] parts = strDateCard.split(", ");
        String partEEE = parts[0]; // ex. วันอาทิตย์
        String partDDD = parts[1]; // ex. 20 พศจิกายน 2017

        holder.dateCard.setText(partDDD);
        holder.amountCard.setText(wallet.getAmount_wallet());
        holder.descCard.setText(wallet.getNote_wallet());

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "แก้ไขรายการ");
                menu.add(holder.getAdapterPosition(), 1, 0, "ลบรายการ");
            }
        });


    }
    @Override
    public int getItemCount() {return listItemWallet.size();}

    public void removeAt(int position) {
        listItemWallet.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listItemWallet.size());
    }

    private void check(int position){

        final Wallet wallet = listItemWallet.get(position);

        if (wallet.getCat_name_wallet().equals("เงินเดือน") || wallet.getCat_name_wallet().equals("ยอดขาย") ||
                wallet.getCat_name_wallet().equals("ดอกเบี้ย") || wallet.getCat_name_wallet().equals("การออม") ||
                wallet.getCat_name_wallet().equals("ทุนการศึกษา") || wallet.getCat_name_wallet().equals("เงินรางวัล") ||
                wallet.getCat_name_wallet().equals("เกษียณ") || wallet.getCat_name_wallet().equals("อื่นๆ")){
            type = 0;
        } else {
            type = 1;
        }
    }
}
