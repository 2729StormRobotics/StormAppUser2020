package org.stormroboticsnj.stormuserradar2020;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import org.stormroboticsnj.stormuserradar2020.Auto;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Teleop.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Teleop#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Teleop extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private int numPCsTotal = 0;
    private OnFragmentInteractionListener mListener;

    public Teleop() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment teleop.
     */
    // TODO: Rename and change types and number of parameters
    public static Teleop newInstance(String param1, String param2) {
        Teleop fragment = new Teleop();
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


        View view = inflater.inflate(R.layout.fragment_teleop, container, false);

        /* Find layout elements */
        final TextView numScoredLVL1 = view.findViewById(R.id.txtNumScoredLVL1); // Text View for number of power cells scored in bottom port
        final TextView numScoredLVL2 = view.findViewById(R.id.txtNumScoredLVL2); // Text View for number of power cells scored in outer port
        final TextView numScoredLVL3 = view.findViewById(R.id.txtNumScoredLVL3); // Text View for number of power cells scored in inner port

        final Button scoreMoreLVL1 = view.findViewById(R.id.btnMoreLVL1); // Button for incrementing bottom port score
        final Button scoreMoreLVL2 = view.findViewById(R.id.btnMoreLVL2); // Button for incrementing outer port score
        final Button scoreMoreLVL3 = view.findViewById(R.id.btnMoreLVL3); // Button for incrementing inner port score

        final Button scoreLessLVl1 = view.findViewById(R.id.btnLessLVL1); // Button for decrementing bottom port score
        final Button scoreLessLVL2 = view.findViewById(R.id.btnLessLVL2); // Button for decrementing outer port score
        final Button scoreLessLVL3 = view.findViewById(R.id.btnLessLVL3); // Button for decrementing inner port score

        final CheckBox rotationControl = view.findViewById(R.id.cboRC); // Checkbox for rotating control panel wheel
        final CheckBox positionControl = view.findViewById(R.id.cboPC); // Checkbox for wheel position

        final MainActivity act = (MainActivity) getActivity(); // Call MainActivity object so that you can call methods from that class
        final Auto auto = (Auto) getChildFragmentManager().findFragmentById(R.id.auto);
        numScoredLVL1.setText(String.valueOf(act.getaPowerCell1()));
        numScoredLVL2.setText(String.valueOf(act.getaPowerCell2()));
        numScoredLVL3.setText(String.valueOf(act.getaPowerCell3()));
        // Setting incrementing listeners for incrementing scores to their respective buttons
        scoreMoreLVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.inctPowerCell1(); // Increment bottom port score
                numScoredLVL1.setText(String.valueOf(act.gettPowerCell1())); // Display updated bottom port number output
                numPCsTotal = act.getTotalPCsAutoAndTeleop();
                if ((act.getTotalPCsAutoAndTeleop()) >= 29) {
                    rotationControl.setVisibility(View.VISIBLE);
                }
                if ((act.getTotalPCsAutoAndTeleop()) >= 49) {
                    positionControl.setVisibility(View.VISIBLE);
                }
            }
        });

        scoreMoreLVL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.inctPowerCell2(); // Increment outer port score
                numScoredLVL2.setText(String.valueOf(act.gettPowerCell2())); // Display updated outer port number output
                numPCsTotal = act.getTotalPCsAutoAndTeleop();                if ((act.getTotalPCsAutoAndTeleop()) >= 29) {
                    rotationControl.setVisibility(View.VISIBLE);
                }
                if ((act.getTotalPCsAutoAndTeleop()) >= 49) {
                    positionControl.setVisibility(View.VISIBLE);
                }
            }
        });

        scoreMoreLVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.inctPowerCell3(); // Increment inner port score
                numScoredLVL3.setText(String.valueOf(act.gettPowerCell3())); // Display updated inner port number output
                numPCsTotal = act.getTotalPCsAutoAndTeleop();                if ((act.getTotalPCsAutoAndTeleop()) >= 29) {
                    rotationControl.setVisibility(View.VISIBLE);
                }
                if ((act.getTotalPCsAutoAndTeleop()) >= 49) {
                    positionControl.setVisibility(View.VISIBLE);
                }
            }
        });

        // Setting decrementing listeners for decrementing scores to their respective buttons
        scoreLessLVl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.dectPowerCell1(); // Decrement bottom port score
                numScoredLVL1.setText(String.valueOf(act.gettPowerCell1())); // Display updated inner port number output
                numPCsTotal = act.getTotalPCsAutoAndTeleop();                if ((act.getTotalPCsAutoAndTeleop()) < 29) {
                    rotationControl.setVisibility(View.GONE);
                }
                if ((act.getTotalPCsAutoAndTeleop()) < 49) {
                    positionControl.setVisibility(View.GONE);
                }
            }
        });

        // Button for decrementing LVL2 score
        scoreLessLVL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.dectPowerCell2(); // Decrement bottom port score
                numScoredLVL2.setText(String.valueOf(act.gettPowerCell2())); // Display updated inner port number output
                numPCsTotal = act.getTotalPCsAutoAndTeleop();                if ((act.getTotalPCsAutoAndTeleop()) < 29) {
                    rotationControl.setVisibility(View.GONE);
                }
                if ((act.getTotalPCsAutoAndTeleop()) < 49) {
                    positionControl.setVisibility(View.GONE);
                }
            }
        });

        // Button for decrementing LVL3 score
        scoreLessLVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.dectPowerCell3(); // Decrement bottom port score
                numScoredLVL3.setText(String.valueOf(act.gettPowerCell3())); // Display updated inner port number output
                numPCsTotal = act.getTotalPCsAutoAndTeleop();                if ((act.getTotalPCsAutoAndTeleop()) < 29) {
                    rotationControl.setVisibility(View.GONE);
                }
                if ((act.getTotalPCsAutoAndTeleop()) < 49) {
                    positionControl.setVisibility(View.GONE);
                }
            }
        });

/*
        rotationControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rotationControl.isChecked()) {

                }
            }
        });*/

        // Inflate the layout for this fragment
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
