package org.stormroboticsnj.mainactivity_fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.stormroboticsnj.MainActivity;
import org.stormroboticsnj.stormuserradar2020.R;


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

    private long lastPauseTime;

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

        final Button buttonSubmit = view.findViewById(R.id.btnSubmit);
        final Button btnELessLVL1 = view.findViewById(R.id.btnELess1);
        final Button btnELessLVL2 = view.findViewById(R.id.btnELess2);
        final Button btnELessLVL3 = view.findViewById(R.id.btnELess3);
        final Button btnEMoreLVL1 = view.findViewById(R.id.btnEMore1);
        final Button btnEMoreLVL2 = view.findViewById(R.id.btnEMore2);
        final Button btnEMoreLVL3 = view.findViewById(R.id.btnEMore3);

        //finding the text view
        final TextView txtScoredLVL1 = view.findViewById(R.id.txtPCE1);
        final TextView txtScoredLVL2 = view.findViewById(R.id.txtPCE2);
        final TextView txtScoredLVL3 = view.findViewById(R.id.txtPCE3);


        final RadioButton park = view.findViewById(R.id.rdoPark);
        final RadioButton hang = view.findViewById(R.id.rdoHang);
        final RadioButton levelHang = view.findViewById(R.id.rdoLevelHang);
        final RadioButton none = view.findViewById(R.id.rdoNone);

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
        none.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                act.setEndgameOutcome("N");
            }
        });

        //having the submit button
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(act.getBoth())new AlertDialog.Builder(getContext()).setMessage(R.string.other).setNeutralButton("cool",(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        act.submit();
                    }
                })).show();
                else act.submit();


            }
        });

        //setup chronometer
        final ToggleButton tb = view.findViewById(R.id.climbButton);
        final Chronometer cm = view.findViewById(R.id.climbTime);
        final Button resetButton = view.findViewById(R.id.climbReset);

        final ScaleAnimation scaleAnimation = new ScaleAnimation(0.7f, 1.0f, 0.7f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.7f);
        scaleAnimation.setDuration(500);
        BounceInterpolator bounceInterpolator = new BounceInterpolator();
        scaleAnimation.setInterpolator(bounceInterpolator);
        tb.setAnimation(scaleAnimation);

        cm.setBase(SystemClock.elapsedRealtime()); //setup stopwatch

        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                buttonView.startAnimation(scaleAnimation);
                if (isChecked) {
                    /*  when the stopwatch is started, parse the current text to see what the last
                        paused time was, and start counting from there
                     */
                    int stoppedMilliseconds = 0;
                    String chronoText = cm.getText().toString();
                    String[] array = chronoText.split(":"); //split at colons
                    if (array.length == 2) {
                        stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 1000 //convert to milliseconds
                                + Integer.parseInt(array[1]) * 1000;
                    } else if (array.length == 3) {
                        stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 1000
                                + Integer.parseInt(array[1]) * 60 * 1000
                                + Integer.parseInt(array[2]) * 1000;
                    }
                    tb.setTextColor(Color.rgb(204, 0, 0));
                    cm.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
                    cm.start();
                } else {
                    /* save and stop the stopwatch */
                    lastPauseTime = SystemClock.elapsedRealtime();
                    tb.setTextColor(Color.rgb(34, 34, 34));
                    cm.stop();

                    // update mainactivity
                    String time = cm.getText().toString();
                    String[] arr = time.split(":");
                    int stoppedSeconds = 0;
                    if (arr.length == 2) {
                        stoppedSeconds = Integer.parseInt(arr[0]) * 60 //convert to milliseconds
                                + Integer.parseInt(arr[1]);
                    } else if (arr.length == 3) {
                        stoppedSeconds = Integer.parseInt(arr[0]) * 60 * 60
                                + Integer.parseInt(arr[1]) * 60
                                + Integer.parseInt(arr[2]);
                    }
                    act.setClimbSecs(stoppedSeconds);

                }
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cm.stop();
                cm.setBase(SystemClock.elapsedRealtime());
                act.setClimbSecs(0);
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
