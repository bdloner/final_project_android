package kmitl.project.bdloner.moneygrow;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WalletFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WalletFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WalletFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private FloatingActionButton fab_plus, fab_ex, fab_in;
    private CustomTextView fab_ex_text, fab_in_text;
    private Animation FabOpen, FabClose, FabRClockwisw, FabRanticlockwise;
    boolean isOpen = false;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public WalletFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WalletFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WalletFragment newInstance(String param1, String param2) {
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v =inflater.inflate(R.layout.fragment_wallet, container, false);

        fab_plus = (FloatingActionButton) v.findViewById(R.id.fab_plus);
        fab_ex = (FloatingActionButton) v.findViewById(R.id.fab_ex);
        fab_in = (FloatingActionButton) v.findViewById(R.id.fab_in);
        fab_ex_text = (CustomTextView) v.findViewById(R.id.fab_ex_text);
        fab_in_text = (CustomTextView) v.findViewById(R.id.fab_in_text);

        FabOpen = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
        FabRClockwisw = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clockwise);
        FabRanticlockwise = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anticlockwise);

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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            /*Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
