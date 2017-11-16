package kmitl.project.bdloner.moneygrow;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public WalletAdapter(List<Wallet> listItemWallet, Context context) {
        this.listItemWallet = listItemWallet;
        this.context = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public CustomTextView catTitleCard, dateCard, descCard, amountCard;
        public ConstraintLayout cardItem;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            cardItem = itemView.findViewById(R.id.card_item);

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

        holder.catTitleCard.setText(wallet.getCat_name_wallet());
        holder.dateCard.setText(wallet.getDate_wallet());
        holder.descCard.setText(wallet.getNote_wallet());
        holder.amountCard.setText(wallet.getAmount_wallet());

        /*String txtAmount = goal.getAmount_goal().replace(",", "");
        String txtStart = goal.getStart_goal().replace(",", "");
        */

        /*holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                menu.add(holder.getAdapterPosition(), 0, 0, "เพิ่มเงิน");
                menu.add(holder.getAdapterPosition(), 1, 0, "แก้ไขรายการ");
                menu.add(holder.getAdapterPosition(), 2, 0, "ลบรายการ");
            }
        });*/
    }

    @Override
    public int getItemCount() {return listItemWallet.size();}

    public void removeAt(int position) {
        listItemWallet.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listItemWallet.size());
    }
}
