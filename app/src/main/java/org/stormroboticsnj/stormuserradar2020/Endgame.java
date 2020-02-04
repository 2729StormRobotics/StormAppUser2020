package org.stormroboticsnj.stormuserradar2020;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Endgame.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Endgame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Endgame extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Endgame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Endgame.
     */
    // TODO: Rename and change types and number of parameters
    public static Endgame newInstance(String param1, String param2) {
        Endgame fragment = new Endgame();
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
        View view = inflater.inflate(R.layout.fragment_endgame, container, false);

        /* find elements */
        final Button buttonSubmit = view.findViewById(R.id.btnSubmit);    // Button for submitting the data after recording
        final Button btnELessLVL1 = view.findViewById(R.id.btnELessLVL1); // Button for decrementing bottom port score
        final Button btnELessLVL2 = view.findViewById(R.id.btnELessLVL2); // Button for decrementing outer port score
        final Button btnELessLVL3 = view.findViewById(R.id.btnELessLVL3); // Button for decrementing inner port score
        final Button btnEMoreLVL1 = view.findViewById(R.id.btnEMoreLVL1); // Button for incrementing bottom port score
        final Button btnEMoreLVL2 = view.findViewById(R.id.btnEMoreLVL2); // Button for incrementing outer port score
        final Button btnEMoreLVL3 = view.findViewById(R.id.btnEMoreLVL3); // Button for incrementing bottom port score

        // finding the text view
        final TextView txtScoredLVL1 = view.findViewById(R.id.txtEScoredLVL1); // Text View for number of power cells scored in bottom port
        final TextView txtScoredLVL2 = view.findViewById(R.id.txtEScoredLVL2); // Text View for number of power cells scored in outer port
        final TextView txtScoredLVL3 = view.findViewById(R.id.txtEScoredLVL3); // Text View for number of power cells scored in inner port

        // Radio buttons in a radio group
        final RadioButton park = view.findViewById(R.id.rdoPark); // Radio button for parking
        final RadioButton hang = view.findViewById(R.id.rdoHang);
        final RadioButton levelHang = view.findViewById(R.id.rdoLevelHang);

        //getting main actvity
        final MainActivity act = (MainActivity) getActivity();

        //making lvl one numbers go down
        txtScoredLVL1.setText(String.valueOf(act.getePowerCell1()));
        txtScoredLVL2.setText(String.valueOf(act.getePowerCell2()));
        txtScoredLVL3.setText(String.valueOf(act.getePowerCell3()));

        btnELessLVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.decePowerCell1();
                txtScoredLVL1.setText(String.valueOf(act.getePowerCell1()));
            }
        });
        //making lvl2 go down
        btnELessLVL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.decePowerCell2();
                txtScoredLVL2.setText(String.valueOf(act.getePowerCell2()));
            }
        });
        //maing lvl3 go down
        btnELessLVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.decePowerCell3();
                txtScoredLVL3.setText(String.valueOf(act.getePowerCell3()));
            }
        });
        //making lvl1 go up
        btnEMoreLVL1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.incePowerCell1();
                txtScoredLVL1.setText(String.valueOf(act.getePowerCell1()));
            }
        });
        //making lvl2 go up
        btnEMoreLVL2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.incePowerCell2();
                txtScoredLVL2.setText(String.valueOf(act.getePowerCell2()));
            }
        });
        //making lvl3 go up
        btnEMoreLVL3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.incePowerCell3();
                txtScoredLVL3.setText(String.valueOf(act.getePowerCell3()));
            }
        });

        park.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.setEndgameOutcome("P");
            }
        });
        hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.setEndgameOutcome("H");
            }
        });
        levelHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.setEndgameOutcome("L");
            }
        });

        //having the submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                act.submit();
            }
        });

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
