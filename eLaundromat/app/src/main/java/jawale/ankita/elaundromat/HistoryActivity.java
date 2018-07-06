package jawale.ankita.elaundromat;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Formatter;

public class HistoryActivity extends MainActivity {

    private TextView name, date, numberofClothes, washType, washSelector, dryerType, totalCOst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_history);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_history, null, true);
        //drawer.removeAllViews();
        drawer.removeViewAt(0);
        drawer.addView(contentView, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        name = (TextView) contentView.findViewById(R.id.name);
        date = (TextView) contentView.findViewById(R.id.timestamp);
        numberofClothes = (TextView) contentView.findViewById(R.id.numberOfClothes);
        washType = (TextView) contentView.findViewById(R.id.washType);
        washSelector = (TextView) contentView.findViewById(R.id.washSelector);
        dryerType = (TextView) contentView.findViewById(R.id.dryerType);
        totalCOst = (TextView) contentView.findViewById(R.id.totalCost);

        displayValues();

    }

    private void displayValues(){
        if (GlobalClass.pref == null){
            GlobalClass.initSharedPref(HistoryActivity.this);
        }else {
            name.setText(GlobalClass.retrieveString(HistoryActivity.this.getString(R.string.laundry_name)));
            String dateTxt = GlobalClass.retrieveString(HistoryActivity.this.getString(R.string.time_stamp));
            //SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
            try{
                date.setText(dateTxt);
            }catch (Exception e){
                e.printStackTrace();
                date.setText(dateTxt);
            }
            washType.setText(GlobalClass.retrieveString(HistoryActivity.this.getString(R.string.wash_type)));
            washSelector.setText(GlobalClass.retrieveString(HistoryActivity.this.getString(R.string.wash_selector)));
            dryerType.setText(GlobalClass.retrieveString(HistoryActivity.this.getString(R.string.dryer_type)));
            totalCOst.setText(GlobalClass.retrieveString(HistoryActivity.this.getString(R.string.total_cost)));
            numberofClothes.setText(GlobalClass.retrieveString(HistoryActivity.this.getString(R.string.number_of_clothes)));

        }
    }

}
