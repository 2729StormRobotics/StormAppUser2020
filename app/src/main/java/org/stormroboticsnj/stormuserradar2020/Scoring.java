package org.stormroboticsnj.stormuserradar2020;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Scoring.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Scoring#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Scoring extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Scoring() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment scoring.
     */
    // TODO: Rename and change types and number of parameters
    public static Scoring newInstance(String param1, String param2) {
        Scoring fragment = new Scoring();
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
        View view = inflater.inflate(R.layout.fragment_scoring, container, false);

        /* Find layout elements */
        final TextView numScored = view.findViewById(R.id.txtNumScored);
        final TextView numScored2 = view.findViewById(R.id.txtNumScored2);
        final Button scoreMore = view.findViewById(R.id.btnMore);
        final Button scoreLess = view.findViewById(R.id.btnLess);
        final Button scoreMore2 = view.findViewById(R.id.btnMore2);
        final Button scoreLess2 = view.findViewById(R.id.btnLess2);
        final MainActivity act = (MainActivity) getActivity();
        // Button for adding
        scoreMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.incScore();
                numScored.setText(String.valueOf(act.getScore()));
            }
        });

        // Button for subtracting
        scoreLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.decScore();
                numScored.setText(String.valueOf(act.getScore()));
            }
        });

        scoreMore2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.incScoreTwo();
                numScored2.setText(String.valueOf(act.getScoreTwo()));
            }
        });

        scoreLess2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.decScoreTwo();
                numScored2.setText(String.valueOf(act.getScoreTwo()));
            }
        });
        // Inflate the layout for this fragment
        return view;
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
