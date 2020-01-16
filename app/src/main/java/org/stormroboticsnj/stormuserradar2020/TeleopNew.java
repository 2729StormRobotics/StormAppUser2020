package org.stormroboticsnj.stormuserradar2020;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeleopNew.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeleopNew#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeleopNew extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TeleopNew() {
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
    public static TeleopNew newInstance(String param1, String param2) {
        TeleopNew fragment = new TeleopNew();
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

        // Setting incrementing listeners for incrementing scores to their respective buttons
        scoreMoreLVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.inctPowerCell1(); // Increment bottom port score
                numScoredLVL1.setText(String.valueOf(act.gettPowerCell1())); // Display updated bottom port number output
            }
        });

        scoreMoreLVL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.inctPowerCell2(); // Increment outer port score
                numScoredLVL2.setText(String.valueOf(act.gettPowerCell2())); // Display updated outer port number output
            }
        });

        scoreMoreLVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.inctPowerCell3(); // Increment inner port score
                numScoredLVL3.setText(String.valueOf(act.gettPowerCell3())); // Display updated inner port number output
            }
        });

        // Setting decrementing listeners for decrementing scores to their respective buttons
        scoreLessLVl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.dectPowerCell1(); // Decrement bottom port score
                numScoredLVL1.setText(String.valueOf(act.gettPowerCell1())); // Display updated inner port number output
            }
        });

        // Button for decrementing LVL2 score
        scoreLessLVL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.dectPowerCell2(); // Decrement bottom port score
                numScoredLVL2.setText(String.valueOf(act.gettPowerCell2())); // Display updated inner port number output
            }
        });

        // Button for decrementing LVL3 score
        scoreLessLVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.dectPowerCell3(); // Decrement bottom port score
                numScoredLVL3.setText(String.valueOf(act.gettPowerCell3())); // Display updated inner port number output
            }
        });


       // boolean checked = ((CheckBox) view).isChecked();

//        switch(view.getId()) {
//            case R.id.cboRC:
//            case R.id.cboPC:
//                if (checked){
//                    // Rotation/Position control stage is complete
//                } else {
//                    // Rotation/Position control stage is not complete
//                }
//                break;
//        }


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teleop, container, false);
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
