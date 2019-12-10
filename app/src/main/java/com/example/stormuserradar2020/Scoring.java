package com.example.stormuserradar2020;

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
    private TextView t;
    private static int num = 0;

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

        // *** Declare and instantiate buttons ***

        // Button for adding
        final Button scoreMore = getActivity().findViewById(R.id.btnMore);
        scoreMore.setTag(11);
        scoreMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment(11);
            }
        });

        // Button for subtracting
        final Button scoreLess = getActivity().findViewById(R.id.btnLess);
        scoreLess.setTag(11);
        scoreLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrement(11);
            }
        });

        // Score output Textview
        final TextView numScored = getActivity().findViewById(R.id.txtNumScored);
        numScored.setTag(11);

        // Inflate the layout for this fragment
        return view;
    }

    /**
     * Increment counter using btnMore Button
     * @param fieldID Button id
     */
    public void increment(int fieldID) {
        switch (fieldID) {
            case 11:
                if (num < 10) {
                    ++num;
                    t = (TextView) getActivity().findViewById(R.id.txtNumScored);
                    t.setText(Integer.toString(num));
                }
        }
    }

    /**
     * Decrement counter using btnMore Button
     * @param fieldID Button id
     */
    public void decrement(int fieldID) {
        switch (fieldID) {
            case 11:
                if (num < 10) {
                    --num;
                    t = (TextView) getActivity().findViewById(R.id.txtNumScored);
                    t.setText(Integer.toString(num));
                }
        }
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
