package kmitl.project.bdloner.moneygrow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class GoalFragment extends Fragment {

    private FloatingActionButton fab_plus;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Goal> listItemGoal = new ArrayList<>();
    private CustomTextView empty;
    private myDbAdapter dbAdapter;
    private OnFragmentInteractionListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goal, container, false);

        recyclerView = v.findViewById(R.id.all_item_goal);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        empty = v.findViewById(R.id.empty_goal);
        createRecyclerView();

        fab_plus = v.findViewById(R.id.fab_plus);

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), AddGoalActivity.class);
                startActivity(in);
            }
        });

        return v;
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
        if(isVisibleToUser){
            createRecyclerView();
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case 0:
                final Goal goal = listItemGoal.get(item.getGroupId());
                String id = goal.getCid();
                Intent intent = new Intent(getActivity(), AddMoneyGoal.class);
                intent.putExtra("oldId",id);
                getActivity().startActivities(new Intent[]{intent});
                getActivity().stopService(intent);
                break;
            case 1:

                break;
            case 2:
                remove(item.getGroupId());
                break;
        }

        return super.onContextItemSelected(item);
    }

    public void remove(int position){
        final Goal goal= listItemGoal.get(position);

        listItemGoal.remove(position);
        adapter.notifyItemRemoved(position);
        adapter.notifyItemRangeChanged(position, listItemGoal.size());

        String id = goal.getCid();
        dbAdapter.delete(id);
        if(listItemGoal.size()==0){
            empty.setVisibility(View.VISIBLE);
        }
    }

    public void createRecyclerView(){
        dbAdapter = new myDbAdapter(getContext());
        List<List> datas = dbAdapter.getData();
        listItemGoal = new ArrayList<>();
        for (int i=0; i<datas.size();i++){
            List<String> eachGoal = datas.get(i);
            Goal listGoal = new Goal(
                    ""+ eachGoal.get(0),// title
                    ""+ eachGoal.get(1),
                    ""+ eachGoal.get(2), // amount
                    ""+ eachGoal.get(3), // desc
                    "วันบรรลุเป้าหมาย: "+ eachGoal.get(4), // date
                    ""+ eachGoal.get(5) // cid
            );
            listItemGoal.add(listGoal);
        }

        if (listItemGoal.size()==0){
            empty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        } else {
            empty.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }

        adapter = new GoalAdapter(listItemGoal,getContext());
        recyclerView.setAdapter(adapter);

    }

    public GoalFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static GoalFragment newInstance(String param1, String param2) {
        GoalFragment fragment = new GoalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
