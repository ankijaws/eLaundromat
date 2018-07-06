package jawale.ankita.elaundromat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LaundryDetailsAcitvity2 extends MainActivity implements View.OnClickListener {

//, RadioButton.OnCheckedChangeListener {

    private RadioGroup dryerRG, dryerTypeRG;
    private EditText specialInstruction, timeET;
    private Button continue_btn;
    private LinearLayout dryerOptionLayout;
    private RadioButton yesRB, noRB;
    private RadioButton permanentPressRB, whiteClothesDryerRB, delicateDryRB;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_laundry_details_acitvity2);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_laundry_details_acitvity2, null, true);
        //drawer.removeAllViews();
        drawer.removeViewAt(0);
        drawer.addView(contentView, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaundryDetailsAcitvity2.this, ProfileInfo.class);
                startActivity(intent);
            }
        });

        init(contentView);
        //checkDeryerRG(dryerRG.getCheckedRadioButtonId());
    }

    private void init(View rootView) {
        //dryerRG = (RadioGroup) rootView.findViewById(R.id.dryingRG);
        //yesRB = (RadioButton) rootView.findViewById(R.id.yesDryer);
        //noRB = (RadioButton) rootView.findViewById(R.id.noDryer);
        dryerOptionLayout = (LinearLayout) rootView.findViewById(R.id.dryerOptionLayout);
        dryerTypeRG = (RadioGroup) rootView.findViewById(R.id.dryerTypeRG);
        permanentPressRB = (RadioButton) rootView.findViewById(R.id.permanentPress);
        whiteClothesDryerRB = (RadioButton) rootView.findViewById(R.id.whiteDryer);
        delicateDryRB = (RadioButton) rootView.findViewById(R.id.delicateDryer);
        specialInstruction = (EditText) rootView.findViewById(R.id.specialNote);
        continue_btn = (Button) rootView.findViewById(R.id.btn_continue);
        imgView = (ImageView) rootView.findViewById(R.id.info);
        imgView.setOnClickListener(this);
        continue_btn.setOnClickListener(this);
        timeET = (EditText) rootView.findViewById(R.id.timeET);
        /*dryerRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yesDryer){
                    dryerOptionLayout.setVisibility(View.VISIBLE);
                }else{
                    dryerOptionLayout.setVisibility(View.GONE);
                }
            }
        });
        yesRB.setOnCheckedChangeListener(this);
        noRB.setOnCheckedChangeListener(this);*/
    }

    private void checkDryerRG(int selectedRadioBtnId) {
        switch (selectedRadioBtnId) {
            case R.id.yesDryer:
                dryerOptionLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.noDryer:
                dryerOptionLayout.setVisibility(View.GONE);
                break;
            default:
                dryerOptionLayout.setVisibility(View.VISIBLE);
                break;
        }
    }


    @Override
    public void onClick(View v) {
        /*if (dryerRG.getCheckedRadioButtonId() == (R.id.yesDryer)) {
            GlobalClass.dryerSelected = true;
            setDryerOption(dryerTypeRG.getCheckedRadioButtonId());
        } else {
            GlobalClass.dryerSelected = false;
        }*/
        if (v.getId() == R.id.btn_continue) {
            String timeTxt = timeET.getText().toString().trim();
            double timeInt = Double.parseDouble(timeTxt);

            if (timeTxt.isEmpty()){
                Toast.makeText(LaundryDetailsAcitvity2.this, "Enter the time for dryer", Toast.LENGTH_SHORT).show();
            }else if(timeInt % 8 != 0){
                Toast.makeText(LaundryDetailsAcitvity2.this, "Time should be in multiple of 8. As Dryer costs 25cents for 8 mins and accepts 25 cents coins only", Toast.LENGTH_SHORT).show();
            }
            else {
                setDryerOption(dryerTypeRG.getCheckedRadioButtonId());
                GlobalClass.specialInstruction = specialInstruction.getText().toString().trim();
                GlobalClass.dryerTime = timeET.getText().toString().trim();

                Intent intent = new Intent(LaundryDetailsAcitvity2.this, ReviewActivity.class);
                intent.putExtra("class","");
                startActivity(intent);
            }
        }else if(v.getId() == R.id.info){
            Toast.makeText(LaundryDetailsAcitvity2.this, LaundryDetailsAcitvity2.this.getResources().getString(R.string.dry_cost_info), Toast.LENGTH_SHORT).show();
        }

    }

    private void setDryerOption(int dryerTypeRadioBtnId) {
        switch (dryerTypeRadioBtnId) {
            case R.id.permanentPress:
                GlobalClass.dryerType = "Permanent Press";
                break;
            case R.id.whiteDryer:
                GlobalClass.dryerType = "White Clothes Dryer";
                break;
            case R.id.delicateDryer:
                GlobalClass.dryerType = "Delicate Dry";
                break;
        }
    }

   /* @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(yesRB.isChecked()){
            dryerOptionLayout.setVisibility(View.VISIBLE);
        }else if(noRB.isChecked()){
            dryerOptionLayout.setVisibility(View.GONE);
        }
    }*/
}
