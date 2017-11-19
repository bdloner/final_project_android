package kmitl.project.bdloner.moneygrow.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;
import java.util.List;

import kmitl.project.bdloner.moneygrow.R;
import kmitl.project.bdloner.moneygrow.controller.myDbAdapter;
import kmitl.project.bdloner.moneygrow.init.CustomTextView;
import kmitl.project.bdloner.moneygrow.model.Wallet;
import kmitl.project.bdloner.moneygrow.controller.WalletAdapter;

public class WalletFragment extends Fragment {

    boolean isOpen = false;
    private FloatingActionButton fab_plus, fab_ex, fab_in;
    private CustomTextView fab_ex_text, fab_in_text;
    private Animation FabOpen, FabClose, FabRClockwisw, FabRanticlockwise;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Wallet> listItemWallet = new ArrayList<>();
    private CustomTextView empty;
    private myDbAdapter dbAdapter;
    private CardView card_view;

    private OnFragmentInteractionListener mListener;

    public WalletFragment() {

    }

    public static WalletFragment newInstance(String param1, String param2) {
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_wallet, container, false);

        fab_plus = v.findViewById(R.id.fab_plus);
        fab_ex = v.findViewById(R.id.fab_ex);
        fab_in = v.findViewById(R.id.fab_in);
        fab_ex_text = v.findViewById(R.id.fab_ex_text);
        fab_in_text = v.findViewById(R.id.fab_in_text);
        card_view = v.findViewById(R.id.cardView);

        recyclerView = v.findViewById(R.id.all_item_wallet);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        empty = v.findViewById(R.id.empty_wallet);
        createRecyclerView();

        FabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        FabRClockwisw = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
        FabRanticlockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anticlockwise);

        card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TotalWalletActivity.class);
                startActivity(intent);
            }
        });

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen) {
                    animationCloseOnClick();
                } else {
                    animationOpenOnClick();
                }
            }
        });

        fab_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationCloseOnClick();

                Intent intent = new Intent(getActivity(), CalculatorActivity.class);
                intent.putExtra("btnCatIn", 0);

                startActivity(intent);

            }
        });

        fab_ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animationCloseOnClick();

                Intent intent = new Intent(getActivity(), CalculatorActivity.class);
                intent.putExtra("btnCatIn", 1);
                startActivity(intent);
            }
        });

        return v;
    }

    public void animationCloseOnClick() {
        fab_in.startAnimation(FabClose);
        fab_ex.startAnimation(FabClose);
        fab_in_text.startAnimation(FabClose);
        fab_ex_text.startAnimation(FabClose);
        fab_plus.startAnimation(FabRanticlockwise);
        fab_ex.setClickable(false);
        fab_in.setClickable(false);
        isOpen = false;
    }

    public void animationOpenOnClick() {
        fab_in.startAnimation(FabOpen);
        fab_ex.startAnimation(FabOpen);
        fab_in_text.startAnimation(FabOpen);
        fab_ex_text.startAnimation(FabOpen);
        fab_plus.startAnimation(FabRClockwisw);
        fab_ex.setClickable(true);
        fab_in.setClickable(true);
        isOpen = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        createRecyclerView();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            createRecyclerView();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                final Wallet wallet = listItemWallet.get(item.getGroupId());
                String id = wallet.getWid();
                Intent intent = new Intent(getActivity(), EditWalletActivity.class);
                intent.putExtra("oldId", id);
                getActivity().startActivities(new Intent[]{intent});
                getActivity().stopService(intent);
                break;
            case 1:
                remove(item.getGroupId());
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void remove(int position) {
        final Wallet wallet = listItemWallet.get(position);

        listItemWallet.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, listItemWallet.size());

        String id = wallet.getWid();
        dbAdapter.deleteWallet(id);
        if (listItemWallet.size() == 0) {
            empty.setVisibility(View.VISIBLE);
        }
    }

    public void createRecyclerView() {
        dbAdapter = new myDbAdapter(getContext());
        List<List> datas = dbAdapter.getDataWallet();
        listItemWallet = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            List<String> eachWallet = datas.get(i);
            Wallet listWallet = new Wallet(
                    "" + eachWallet.get(4),// image       0 title
                    "" + eachWallet.get(0), // title      1 date
                    "" + eachWallet.get(1), // date          2 amount
                    "฿ " + eachWallet.get(2), // amount    3 note
                    "" + eachWallet.get(3), // note         4 image
                    "" + eachWallet.get(5) // wid
            );
            listItemWallet.add(listWallet);
        }

        if (listItemWallet.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            empty.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        adapter = new WalletAdapter(listItemWallet, getContext());
        recyclerView.setAdapter(adapter);

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
