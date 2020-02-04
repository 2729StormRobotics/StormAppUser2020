package org.stormroboticsnj.mainactivity_fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.stormroboticsnj.MainActivity;
import org.stormroboticsnj.stormuserradar2020.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Auto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Auto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Auto extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Auto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Auto.
     */
    // TODO: Rename and change types and number of parameters
    public static Auto newInstance(String param1, String param2) {
        Auto fragment = new Auto();
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
        View view = inflater.inflate(R.layout.fragment_auto, container, false);

        //Create buttons
        final Button lessBottomBtn = view.findViewById(R.id.btnALess1); // Button to decrement power cell score bottom port for auto
        final Button moreBottomBtn = view.findViewById(R.id.btnAMore1); // Button to increment power cell score bottom port for auto
        final Button lessOuterBtn = view.findViewById(R.id.btnALess2); // Button to decrement power cell score outer port for auto
        final Button moreOuterBtn = view.findViewById(R.id.btnAMore2); // Button to increment power cell score outer port for auto
        final Button lessInnerBtn = view.findViewById(R.id.btnALess3); // Button to decrement power cell score inner port for auto
        final Button moreInnerBtn = view.findViewById(R.id.btnAMore3); // Button to increment power cell score inner port for auto
        final Button lessIntakeBtn = view.findViewById(R.id.abtnLessIntake); // Button to decrement power cell intake for auto
        final Button moreIntakeBtn = view.findViewById(R.id.abtnMoreIntake); // Button to increment power cell intake for auto

        //Create TextViews
        final TextView bottomScoreTxt = view.findViewById(R.id.txtPCA1); // TextView bottom port score
        final TextView outerScoreTxt = view.findViewById(R.id.txtPCA2);
        final TextView innerScoreTxt = view.findViewById(R.id.txtPCA3);
        final TextView intakeScoreTxt = view.findViewById(R.id.atxtNumScoredIntake);

        // Create Main Activity
        final MainActivity act = (MainActivity) getActivity();
        bottomScoreTxt.setText(String.valueOf(act.getaPowerCell1()));
        outerScoreTxt.setText(String.valueOf(act.getaPowerCell2()));
        innerScoreTxt.setText(String.valueOf(act.getaPowerCell3()));
        intakeScoreTxt.setText(String.valueOf(act.getaPowerCellPickup()));

        //Create the listeners, which make the buttons functional. That is, they increment/decrement
        //the score in the appropriate textview.
        moreBottomBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                act.incaPowerCell1();
                bottomScoreTxt.setText(String.valueOf(act.getaPowerCell1()));
            }
        });
        lessBottomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.decaPowerCell1();
                bottomScoreTxt.setText(String.valueOf(act.getaPowerCell1()));
            }
        });
        moreOuterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.incaPowerCell2();
                outerScoreTxt.setText(String.valueOf(act.getaPowerCell2()));
            }
        });
        lessOuterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.decaPowerCell2();
                outerScoreTxt.setText(String.valueOf(act.getaPowerCell2()));
            }
        });
        moreInnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.incaPowerCell3();
                innerScoreTxt.setText(String.valueOf(act.getaPowerCell3()));
            }
        });
        lessInnerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.decaPowerCell3();
                innerScoreTxt.setText(String.valueOf(act.getaPowerCell3()));
            }
        });
        moreIntakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.incaPowerCellPickup();
                intakeScoreTxt.setText(String.valueOf(act.getaPowerCellPickup()));
            }
        });
        lessIntakeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.decaPowerCellPickup();
                intakeScoreTxt.setText(String.valueOf(act.getaPowerCellPickup()));
            }
        });



        return view;
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
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
