package jawale.ankita.elaundromat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LaundryDetailsActivity extends MainActivity implements View.OnClickListener{

    private EditText numberOfClothesET;
    private RadioGroup washTypeRG, washSelectorRG;
    private RadioButton whiteWash, colorWash, delicateWash;
    private RadioButton quickWash, normalWash, heavyWash;
    private Button continue_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_laundry_details);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_laundry_details, null, true);
        drawer.removeViewAt(0);
        drawer.addView(contentView, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LaundryDetailsActivity.this,ProfileInfo.class);
                startActivity(intent);
            }
        });

        init(contentView);
    }

    private void init(View rootView){
        numberOfClothesET = (EditText) rootView.findViewById(R.id.numOfClothes);

        washTypeRG = (RadioGroup) rootView.findViewById(R.id.wash_type_radio_group);
        whiteWash = (RadioButton) rootView.findViewById(R.id.white_wash);
        colorWash = (RadioButton) rootView.findViewById(R.id.color_wash);
        delicateWash = (RadioButton) rootView.findViewById(R.id.delicate_wash);

        washSelectorRG = (RadioGroup) rootView.findViewById(R.id.washSelectorRG);
        quickWash = (RadioButton) rootView.findViewById(R.id.quickWash);
        normalWash = (RadioButton) rootView.findViewById(R.id.normalWash);
        heavyWash = (RadioButton) rootView.findViewById(R.id.heavyWash);

        continue_btn = (Button) rootView.findViewById(R.id.btn_continue);
        continue_btn.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(View v) {
        validate();
    }

    private void validate(){
        if(numberOfClothesET.getText().toString().trim().isEmpty()){
            Toast.makeText(LaundryDetailsActivity.this,"Enter the number of clothes to proceed",Toast.LENGTH_SHORT).show();
        }else{
            GlobalClass.numberrOfClothes = numberOfClothesET.getText().toString().trim();
            setWashTypeRB(washTypeRG.getCheckedRadioButtonId());
            setWashSelectorRB(washSelectorRG.getCheckedRadioButtonId());
            Intent i = new Intent(LaundryDetailsActivity.this,LaundryDetailsAcitvity2.class);
            startActivity(i);
        }
    }

    private void setWashTypeRB(int radioBtnId){
        switch (radioBtnId){
            case R.id.white_wash:
                GlobalClass.washType = "White Wash";
                break;
            case R.id.color_wash:
                GlobalClass.washType = "Color Wash";
                break;
            case R.id.delicate_wash:
                GlobalClass.washType = "Delicate Wash";
                break;
        }
    }

    private void setWashSelectorRB(int radioBtnId){
        switch (radioBtnId){
            case R.id.quickWash:
                GlobalClass.washSelector = "Quick Wash";
                break;
            case R.id.normalWash:
                GlobalClass.washSelector = "Normal Wash";
                break;
            case R.id.heavyWash:
                GlobalClass.washSelector = "Heavy Wash";
                break;
        }
    }
}
