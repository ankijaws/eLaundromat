package jawale.ankita.elaundromat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.gsm.GsmCellLocation;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewActivity extends MainActivity implements View.OnClickListener {

    private TextView numberOfClothes, washType, washSelector,
            dryerOption, specialInstructions, washCost, dryCost, TotalCost, laundryName;
    private Button confirmBtn;
    private String intentExtra = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_review);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_review, null, true);
        //drawer.removeAllViews();
        drawer.removeViewAt(0);
        drawer.addView(contentView, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReviewActivity.this,ProfileInfo.class);
                startActivity(intent);
            }
        });

        Bundle extras = getIntent().getExtras();
        intentExtra = extras.getString("class");

        init(contentView);
        setValues();

        if (!intentExtra.equalsIgnoreCase("")){
            confirmBtn.performClick();
        }

    }

    private void init(View rootView){
        laundryName = (TextView) rootView.findViewById(R.id.laundryName);
        numberOfClothes = (TextView) rootView.findViewById(R.id.numOfClothes);
        washType = (TextView) rootView.findViewById(R.id.washType);
        washSelector = (TextView) rootView.findViewById(R.id.washSelector);
        dryerOption = (TextView) rootView.findViewById(R.id.dryerOption);
        specialInstructions = (TextView) rootView.findViewById(R.id.spclInst);
        washCost = (TextView) rootView.findViewById(R.id.washCost);
        dryCost = (TextView) rootView.findViewById(R.id.dryCost);
        TotalCost = (TextView) rootView.findViewById(R.id.totalCost);
        confirmBtn = (Button) rootView.findViewById(R.id.confirm_btn);
        confirmBtn.setOnClickListener(this);

    }

    private void setValues(){
        laundryName.setText(GlobalClass.location);
        numberOfClothes.setText(GlobalClass.numberrOfClothes);
        washType.setText(GlobalClass.washType);
        washSelector.setText(GlobalClass.washSelector);
        dryerOption.setText(GlobalClass.dryerType);
        specialInstructions.setText(GlobalClass.specialInstruction);
        calculateWashCost();
        calculateDryCost();
        dryCost.setText("$ "+GlobalClass.dryCost);
        washCost.setText("$ "+GlobalClass.washCost);
        TotalCost.setText("$ "+calculateTotalCost());
    }

    private double calculateTotalCost(){
        double totalCost = 0.0;
        try{
            totalCost = GlobalClass.washCost + GlobalClass.dryCost;
        }catch (Exception e){
            e.printStackTrace();
        }
        return totalCost;
    }

    private void calculateWashCost(){
        if (Integer.parseInt(GlobalClass.numberrOfClothes) <= 20){
            GlobalClass.washCost = 2.50;
        }else if(Integer.parseInt(GlobalClass.numberrOfClothes) > 20 && Integer.parseInt(GlobalClass.numberrOfClothes) <= 40){
            GlobalClass.washCost = 4.75;
        }else if(Integer.parseInt(GlobalClass.numberrOfClothes) > 40 && Integer.parseInt(GlobalClass.numberrOfClothes) <= 80){
            GlobalClass.washCost = 8.75;
        }
    }

    private void calculateDryCost(){
        try {
            int time = Integer.parseInt(GlobalClass.dryerTime.toString().trim());
            double costInCents = (time / 8)*25;
            double costInDollars = costInCents / 100;
            GlobalClass.dryCost = costInDollars;
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.confirm_btn){
            if(GlobalClass.isLoggedIn) {
                if (GlobalClass.pref == null) {
                    GlobalClass.initSharedPref(this);
                }
                GlobalClass.storeBoolean("isLoggedIn",true);
                saveInSharedPref();
                Toast.makeText(ReviewActivity.this, "Order Submitted Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ReviewActivity.this, MainActivity.class);
                startActivity(intent);
            }else{
                GlobalClass.storeBoolean("isLoggedIn",false);
                Intent i = new Intent (ReviewActivity.this, Login.class);
                i.putExtra("class",ReviewActivity.this.getResources().getString(R.string.title_activity_review));
                startActivity(i);
            }
        }
    }

    private void saveInSharedPref(){

        GlobalClass.storeString(this.getResources().getString(R.string.laundry_name),GlobalClass.location);
        GlobalClass.storeString(this.getResources().getString(R.string.number_of_clothes),GlobalClass.numberrOfClothes);
        GlobalClass.storeString(this.getResources().getString(R.string.wash_type),GlobalClass.washType);
        GlobalClass.storeString(this.getResources().getString(R.string.wash_selector),GlobalClass.washSelector);
        GlobalClass.storeString(this.getResources().getString(R.string.dryer_type),GlobalClass.dryerType);
        GlobalClass.storeString(this.getResources().getString(R.string.spcl_inst),GlobalClass.specialInstruction);
        GlobalClass.storeString(this.getResources().getString(R.string.washer_cost),GlobalClass.washCost+"");
        GlobalClass.storeString(this.getResources().getString(R.string.dryer_cost),GlobalClass.dryCost+"");
        GlobalClass.storeString(this.getResources().getString(R.string.total_cost),GlobalClass.totalCOst+"");
        SimpleDateFormat s = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
        String format = s.format(new Date());
        GlobalClass.storeString(this.getResources().getString(R.string.time_stamp),format);

    }
}
