package jkuatcs.com.qccharts;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
For UI/UX purposes
 */

import android.support.constraint.motion.MotionLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

//Samples selection button
import android.widget.Button;

//For our texts
import android.widget.EditText;
import android.widget.TextView;

//Text processing
import android.text.TextUtils;
import android.widget.Toast;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import jkuatcs.com.qccharts.adapter.RecyclerViewAdapter;
import jkuatcs.com.qccharts.models.QData;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /*
    Holding several motion layouts. Each has its instance defined here
     */
    private MotionLayout full_custom_q_motion_layout,launch_only_splash_screen_motion_layout,
    main_motion_layout_container, type_selection_motion_layout;

    //Holding our q's image data
    private ImageView q_tail,q_round;

    //Buttons
    private Button samples_main_select_btn,samples_txt_req__ok_btn, mean_values_main_select_btn, mean_edit_ok_btn,
    start_button, mean_start_button, data_entry_btn_next, home_btn, plot_btn;

    //EditTexts
    private EditText sample_size_num_edit,mean_sample_number_edit,data_entry_edit;

    //Textviews
    private TextView input_sample_size_show, input_sample_number_show, mean_input_sample_number_show,
    sample_left_indicate_show, sample_no_indicate_show;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    //Know the context of samples_btn click
    private int samples_btn_click_context = 0;

    //Know the context of mean btn clicks
    private int mean_btn_click_context = 0;

    //Sample size and number values
    private int sample_size, sample_number;
    private int counter = 0;
    private int progressIndicator = 1;

    //Help me process data the right way
    private static boolean isMean;

    private boolean isComplete = false;

    //Arraylist holding my data
    ArrayList<QData> qDataList = new ArrayList<>();

    //Keyboard gymnastics
    private View view;
    private InputMethodManager imm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Get our widget references
         */

        //Start with motion layout instances
        full_custom_q_motion_layout = findViewById(R.id.full_custom_q_motion_layout);
        launch_only_splash_screen_motion_layout = findViewById(R.id.launch_only_splash_screen_motion_layout);
        main_motion_layout_container = findViewById(R.id.main_motion_layout_container);
        type_selection_motion_layout = findViewById(R.id.type_selection_motion_layout);

        //Image view instances, for q
        q_tail = findViewById(R.id.q_tail);
        q_round = findViewById(R.id.q_round);

        //Edittext instance
        sample_size_num_edit = findViewById(R.id.sample_size_edit);
        mean_sample_number_edit = findViewById(R.id.mean_sample_number_edit);
        data_entry_edit = findViewById(R.id.data_entry_edit);

        //Textview instances
        input_sample_size_show = findViewById(R.id.input_sample_size_show);
        input_sample_number_show = findViewById(R.id.input_sample_number_show);
        mean_input_sample_number_show = findViewById(R.id.mean_input_sample_number_show);
        sample_left_indicate_show = findViewById(R.id.sample_left_indicate_show);
        sample_no_indicate_show = findViewById(R.id.sample_no_indicate_show);

        //textColor
        sample_left_indicate_show.setTextColor(getResources().getColor(R.color.colorAccent));
        sample_no_indicate_show.setTextColor(getResources().getColor(R.color.colorAccent));

        //Button instances
        samples_main_select_btn = findViewById(R.id.samples_main_select_btn);
        samples_txt_req__ok_btn = findViewById(R.id.samples_txt_req__ok_btn);
        mean_values_main_select_btn = findViewById(R.id.mean_values_main_select_btn);
        mean_edit_ok_btn = findViewById(R.id.mean_edit_ok_btn);
        start_button = findViewById(R.id.start_btn);
        mean_start_button = findViewById(R.id.mean_start_btn);
        data_entry_btn_next = findViewById(R.id.data_entry_btn_next);
        home_btn = findViewById(R.id.home_btn);
        plot_btn = findViewById(R.id.plot_btn);

        //recycler view
        recyclerView = findViewById(R.id.recyclerView);

        //set up my recycler view adapter
        recyclerViewAdapter = new RecyclerViewAdapter(this, qDataList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set its listener to this
        samples_main_select_btn.setOnClickListener(this);
        samples_txt_req__ok_btn.setOnClickListener(this);
        mean_values_main_select_btn.setOnClickListener(this);
        mean_edit_ok_btn.setOnClickListener(this);
        start_button.setOnClickListener(this);
        mean_start_button.setOnClickListener(this);
        data_entry_btn_next.setOnClickListener(this);
        home_btn.setOnClickListener(this);
        plot_btn.setOnClickListener(this);

        //Keyboard gymnastics
        imm = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);

        //Kill clickability of our start buttons/any other hidden buttons, till in state where they are shown
        start_button.setClickable(false);
        mean_start_button.setClickable(false);

        /*
        Call our splash screen custom q player
         */
        playSplashScreenCustomQ();
    }

    private void playSplashScreenCustomQ(){

        /*
        Call our transition
         */
        //Specify for full custom q
        full_custom_q_motion_layout.setTransition(R.id.custom_q_all_group_form_start,R.id.custom_q_all_group_form_end);
        full_custom_q_motion_layout.transitionToEnd();
        launch_only_splash_screen_motion_layout.transitionToEnd();

        /*
        Set up listener that will call the remaining splash screen animation to start
         */
        full_custom_q_motion_layout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {

                //change our q image assets
                q_tail.setImageResource(R.drawable.q_tail_grey);
                q_round.setImageResource(R.drawable.q_round_grey);
                full_custom_q_motion_layout.setTransition(R.id.custom_q_whole_transit_up_full_start,R.id.custom_q_whole_transit_up_full_end);
                main_motion_layout_container.transitionToEnd();
                full_custom_q_motion_layout.transitionToEnd();
                //Finish my transitions
                //closeTransition();
            }
        });
    }

    private void closeTransition(){


    }

    //Handling button clicks
    public void onClick(View v){

        //Use switch - case to determine button clicked
        switch(v.getId()){

            case R.id.samples_main_select_btn:

                //Get to know the context of click
                switch (samples_btn_click_context){

                    case 0:

                        //Transitioning to state 1 - keying in sample size
                        //Increment to new state
                        samples_btn_click_context++;

                        //Since first time to click on this button, kill clicks on other buttons in view
                        mean_values_main_select_btn.setClickable(false);

                        //Now, getting into transition scenes for samples. Load that
                        //type_selection_motion_layout.loadLayoutDescription(R.xml.type_selection_layout_scenes);

                        //change text in button to back
                        samples_main_select_btn.setText(R.string.back);

                        //set motion layout transition
                        type_selection_motion_layout.setTransition(R.id.samples_state_1_start,R.id.samples_state_1_end);
                        type_selection_motion_layout.transitionToEnd();

                        //Kill focus/clickability of mean values btn

                        break;

                    case 1:

                        //Decrement to lower context - since it is being used as back
                        samples_btn_click_context--;

                        //Since now moving to original state, reactivate clicks on other buttons
                        mean_values_main_select_btn.setClickable(true);

                        //set motion layout transition
                        type_selection_motion_layout.setTransition(R.id.samples_state_1_end,R.id.samples_state_1_start);
                        type_selection_motion_layout.transitionToEnd();

                        //Clear errors in edit text
                        sample_size_num_edit.setError(null);

                        //Hide my keyboard
                        hideMyKeyBoard();

                        //Smoothening purposes. Need a transition listener
                        type_selection_motion_layout.setTransitionListener(new MotionLayout.TransitionListener() {
                            @Override
                            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

                            }

                            @Override
                            public void onTransitionCompleted(MotionLayout motionLayout, int i) {

                                if (samples_btn_click_context == 0){
                                    //change text in button to samples_request, since in case 1
                                    samples_main_select_btn.setText(R.string.samples_request_main);
                                }
                            }
                        });

                        //return clickability of mean button

                        break;

                    case 2:

                        //Now, moving to state 1 from state 2
                        //decrement to lower context
                        samples_btn_click_context--;

                        //Set correct hint
                        sample_size_num_edit.setHint(R.string.sample_size_hint);

                        //set motion layout transition
                        type_selection_motion_layout.setTransition(R.id.samples_state_2_end,R.id.samples_state_2_start);
                        type_selection_motion_layout.transitionToEnd();

                        break;

                    case 3:

                        //Now moving to state 2 from state 3
                        samples_btn_click_context--;

                        //activate clicking of next btn
                        samples_txt_req__ok_btn.setClickable(true);

                        //start now not clickable
                        start_button.setClickable(false);

                        //set correct hint
                        sample_size_num_edit.setHint(R.string.sample_number_hint);
                        sample_size_num_edit.getText().clear();

                        //set motion layout transition
                        type_selection_motion_layout.setTransition(R.id.samples_state_3_end, R.id.samples_state_3_start);
                        type_selection_motion_layout.transitionToEnd();

                        break;


                }

                break;

            case R.id.samples_txt_req__ok_btn:

                //Handle what would happen if this button is clicked

                //Error handling
                if (TextUtils.isEmpty(sample_size_num_edit.getText().toString())){

                    //set error
                    sample_size_num_edit.setError("Please key in a value");
                } else if (Integer.valueOf(sample_size_num_edit.getText().toString()) < 2 && samples_btn_click_context == 1) {

                    sample_size_num_edit.setError("Sample size should be at least 2");
                }else {

                    //update context(moving to new motion layout state
                    samples_btn_click_context++;

                    //check our context, to know what we are saving, and what transition we need to make
                    if (samples_btn_click_context == 2){

                        //Saving sample size
                        sample_size = Integer.valueOf(sample_size_num_edit.getText().toString());

                        //Important error handling. Sample size should be at least 2

                        //Showing value keyed in
                        input_sample_size_show.setText(String.valueOf(sample_size));

                        //Clearing the editText
                        sample_size_num_edit.setHint(R.string.sample_number_hint);
                        sample_size_num_edit.getText().clear();

                        //keyboard gymnastics
                        hideMyKeyBoard();

                        //Doing a state 2 transition, start to end (forward)

                        //Set motion layout transition
                        type_selection_motion_layout.setTransition(R.id.samples_state_2_start,R.id.samples_state_2_end);
                        type_selection_motion_layout.transitionToEnd();

                    } else{

                        //saving sample number. Doing a state 3 transition
                        sample_number = Integer.valueOf(sample_size_num_edit.getText().toString());

                        //showing valued keyed in
                        input_sample_number_show.setText(String.valueOf(sample_number));

                        //making the ok button not clickable
                        samples_txt_req__ok_btn.setClickable(false);

                        //Start now clickable
                        start_button.setClickable(true);

                        //keyboard gymnastics
                        hideMyKeyBoard();

                        //Set motion layout transition
                        type_selection_motion_layout.setTransition(R.id.samples_state_3_start,R.id.samples_state_3_end);
                        type_selection_motion_layout.transitionToEnd();
                    }
                }

                break;

            case R.id.mean_values_main_select_btn:

                //Know the context of the click, then determine the transition to effect
                switch (mean_btn_click_context){

                    case 0:

                        //Start of our states transition. Moving to state 1 - keying in sample number
                        mean_btn_click_context++;

                        //Kill clicks on irrelevant button (just samples_main_button)
                        samples_main_select_btn.setClickable(false);

                        //Load the appropriate layout description
                        //ype_selection_motion_layout.loadLayoutDescription(R.xml.type_selection_layout_scenes_mean);

                        //Change button text to back
                        mean_values_main_select_btn.setText(R.string.back);

                        //set our transition
                        type_selection_motion_layout.setTransition(R.id.mean_sample_state_1_start, R.id.mean_sample_state_1_end);
                        type_selection_motion_layout.transitionToEnd();

                        break;

                    case 1:

                        //Moving back to state 0 - original. No selection made.
                        mean_btn_click_context--;

                        //reactivate clicks
                        samples_main_select_btn.setClickable(true);

                        //clear any error in the edit text
                        mean_sample_number_edit.setError(null);

                        //hide our keyboard
                        hideMyKeyBoard();

                        //set and run our transition
                        type_selection_motion_layout.setTransition(R.id.mean_sample_state_1_end,R.id.mean_sample_state_1_start);

                        type_selection_motion_layout.setTransitionListener(new MotionLayout.TransitionListener() {
                            @Override
                            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {

                            }

                            @Override
                            public void onTransitionCompleted(MotionLayout motionLayout, int i) {

                                if (mean_btn_click_context == 0){

                                    //Change focus button text
                                    mean_values_main_select_btn.setText(R.string.mean_values_request_main);
                                }
                            }
                        });

                        type_selection_motion_layout.transitionToEnd();

                        break;

                    case 2:

                        //moving back to state 1 - inputting sample size value
                        mean_btn_click_context--;

                        //Keyboard was hidden when moving from state 1 to 2. No need to hide here

                        //Make this button mean_edit_ok_btn clickable, but start not clickable
                        mean_edit_ok_btn.setClickable(true);
                        mean_start_button.setClickable(false);


                        //set our reverse transition
                        type_selection_motion_layout.setTransition(R.id.mean_sample_state_2_end,R.id.mean_sample_state_2_start);
                        type_selection_motion_layout.transitionToEnd();

                        break;

                }

                break;

            case R.id.mean_edit_ok_btn:

                //A bit of error handling
                if (TextUtils.isEmpty(mean_sample_number_edit.getText().toString())){

                    //Set error
                    mean_sample_number_edit.setError("Please key in a value");
                } else{

                    //Update our mean_btn context - now at 2
                    mean_btn_click_context++;

                    //Make this button no longer clickable
                    mean_edit_ok_btn.setClickable(false);

                    //make start clickable
                    mean_start_button.setClickable(true);

                    //save the sample number value
                    sample_number = Integer.valueOf(mean_sample_number_edit.getText().toString());

                    //set the text in the mean_input_sample_number_show widget
                    mean_input_sample_number_show.setText(String.valueOf(sample_number));

                    //hide keyboard
                    hideMyKeyBoard();

                    //Do a transition to state 2 end
                    type_selection_motion_layout.setTransition(R.id.mean_sample_state_2_start,R.id.mean_sample_state_2_end);
                    type_selection_motion_layout.transitionToEnd();
                }

                break;

            case R.id.start_btn:

                //Set correct string resources
                data_entry_edit.setHint(R.string.req_sample_hint);


                //Do a transition of main_motion_layout to sweep this container for type selection away.
                //Then fade in container for keying in comma separated values, with validation for each
                main_motion_layout_container.setTransition(R.id.main_motion_layout_reveal_start,R.id.main_motion_layout_reveal_end);
                main_motion_layout_container.transitionToEnd();

                //Now set correct isMean status, to help single computing function
                isMean = false;
                isComplete = false;

                //Set the counter value to the keyed in sample number value. Mutually exclusive by
                //layout flow, so will never hold for mean, from this line of code
                counter = sample_number;

                //Show appropriate text in our widgets
                sample_left_indicate_show.setText(String.valueOf(counter));
                sample_no_indicate_show.setText(String.valueOf(progressIndicator));

                break;

            case R.id.mean_start_btn:

                data_entry_edit.setHint(R.string.req_mean_hint);

                main_motion_layout_container.setTransition(R.id.main_motion_layout_reveal_start,R.id.main_motion_layout_reveal_end);
                main_motion_layout_container.transitionToEnd();

                isMean = true;
                isComplete = false;

                counter = sample_number;

                //sample size fixed to 2, x bar and r
                sample_size = 2;

                sample_left_indicate_show.setText(String.valueOf(counter));
                sample_no_indicate_show.setText(String.valueOf(progressIndicator));

                break;

            case R.id.data_entry_btn_next:

                //Do its gymnastics here. Entry validation depending on sample size
                //Seen, using transitions, in xml, can specify if clickable or not. Nice

                //Call function to compute entry
                computeEntry(isMean ? 0 : 1);

                break;

            case R.id.home_btn:

                //remove edit text errors, clear recycler view data. Set appropriate progress value
                data_entry_edit.setError(null);
                qDataList.clear();
                recyclerViewAdapter.clearData();
                progressIndicator = 1;


                //reverse main motion layout transition
                main_motion_layout_container.setTransition(R.id.main_motion_layout_reveal_end,R.id.main_motion_layout_reveal_start);
                main_motion_layout_container.transitionToEnd();

                break;

            case R.id.plot_btn:

                //Do activity switch here, for plotting purposes, ensuring we set the reentrace state right
                //How it left is how it will enter? Won't have to set layouts within, first, to last state?
                //The magic? The damn activity remembers the state!! Cause of lifecycle..hahahaa!!! Reenter at onResume,
                //not onCreate!! Boom!!

                if (isComplete){

                    Intent intent = new Intent(this, ChartActivity.class);
                    startActivity(intent);
                } else{

                    data_entry_edit.setError("Please key in all values then plot");
                }

                break;


        }
    }

    private void computeEntry(int mode){

        //Compute based on whether we have means or not.
        //Case 1, we don't have means. Looping sample number times. Go on decrementing counter based on it.
        if (counter > 0){

            //A valid input we can save
            //Get the edittext entry
            String data = data_entry_edit.getText().toString();

            //save the data if string is correct size
            int response = isRightSize(data);
            if (response == 0){

                //save my string, in my node
                //Saving dependent on mode (mean or samples?)
                //Will always be mutually exclusive for an entire cycle
                switch (mode){

                    case 0:
                        //saving for mean values, xbar and r
                        QData qData = new QData(getXBarR(data).get(0),getXBarR(data).get(1));
                        qDataList.add(qData);

                        break;

                    case 1:
                        //saving sample values
                        QData qDataS = new QData(data);
                        qDataList.add(qDataS);
                }

                //populate to recycler view
                recyclerViewAdapter.updateData();

                //decrement counter, passing one value
                counter--;

                //increment progressIndicator
                progressIndicator++;

                //remove text in there
                data_entry_edit.setText(null);

                //Show progress in appropriate widgets
                sample_no_indicate_show.setText(String.valueOf(progressIndicator));
                sample_left_indicate_show.setText(String.valueOf(counter));

                if (progressIndicator == (sample_number + 1))
                {

                    sample_no_indicate_show.setText("complete");
                    isComplete = true;
                }


            }else if (response == 1){

                //set error in edit text
                data_entry_edit.setError("You have excess values");
            } else {

                data_entry_edit.setError("You have few values");
            }

        } else {

            //tell them data is full. Try shrinking edit text, hiding button focus, showing button plot. So premature now
            Toast.makeText(this,"You have keyed in all data",Toast.LENGTH_LONG).show();
            isComplete = true;
        }

    }

    private int isRightSize(String data){

        ArrayList<Double> all = new ArrayList<>();
        for(String s: data.split(",")){

            if(!s.isEmpty()){

                all.add(Double.valueOf(s));
            }
        }

        if (all.size() > sample_size )
            return 1;

        if (all.size() < sample_size)
            return -1;

        return 0;

    }

    private ArrayList<Double> getXBarR(String data){

        ArrayList<Double> xBarR = new ArrayList<>();

        for(String s: data.split(",")){

            if (!s.isEmpty()){

                xBarR.add(Double.valueOf(s));
            }
        }

        return xBarR;
    }

    public static boolean isMeanComputing(){

        return isMean;
    }

    private void hideMyKeyBoard(){

        //Keyboard gymnastics
        view = this.getCurrentFocus();

        if (view == null){
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(),0);

    }


}
