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
import android.widget.TextView;

import java.net.PortUnreachableException;

public class ProfileInfo extends MainActivity {

    private TextView name, email, address, city, state, zipcode, mobile;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_profile_info);
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.activity_profile_info, null, true);
        //drawer.removeAllViews();
        drawer.removeViewAt(0);
        drawer.addView(contentView, 0);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (TextView) contentView.findViewById(R.id.name);
        email = (TextView) contentView.findViewById(R.id.email);
        address = (TextView) contentView.findViewById(R.id.address);
        city = (TextView) contentView.findViewById(R.id.city);
        state = (TextView) contentView.findViewById(R.id.state);
        zipcode = (TextView) contentView.findViewById(R.id.zipcode);
        mobile = (TextView) contentView.findViewById(R.id.mobile);
        edit = (Button) contentView.findViewById(R.id.editBtn);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileInfo.this, SignIn.class);
                intent.putExtra("class", ProfileInfo.this.getResources().getString(R.string.title_activity_profile_info));
                startActivity(intent);
            }
        });

        displayData();

    }

    private void displayData(){
        if (GlobalClass.pref == null){
            GlobalClass.initSharedPref(this);
        }else {
            name.setText(GlobalClass.retrieveString(this.getResources().getString(R.string.name)));
            email.setText(GlobalClass.retrieveString(this.getResources().getString(R.string.email_login)));
            address.setText(GlobalClass.retrieveString(this.getResources().getString(R.string.address)));
            city.setText(GlobalClass.retrieveString(this.getResources().getString(R.string.city)));
            state.setText(GlobalClass.retrieveString(this.getResources().getString(R.string.state)));
            zipcode.setText(GlobalClass.retrieveString(this.getResources().getString(R.string.zipcode)));
            mobile.setText(GlobalClass.retrieveString(this.getResources().getString(R.string.mobile)));
        }
    }

}
