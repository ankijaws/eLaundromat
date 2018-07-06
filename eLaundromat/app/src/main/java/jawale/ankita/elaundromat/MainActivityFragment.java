package jawale.ankita.elaundromat;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements Spinner.OnItemClickListener {


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        Spinner location_spinner = (Spinner)rootView.findViewById(R.id.location_spinner);

        ArrayList<String> location_text = new ArrayList<String>();
        location_text.add("West Side Laundromat, Jersey City");
        location_text.add("Sip Avenue Laundromat, Jersey City");
        location_text.add("Ritz Laundromat, Jersey City");
        location_text.add("Bubbles and Suds Laundromat, Jersey City");
        location_text.add("American Laundromat, Jersey City");
        location_text.add("Bubble Laundromat, Jersey City");
        location_text.add("Lucky Laundromat, Jersey Avenue, Jersey City");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(GlobalClass.context, android.R.layout.simple_spinner_item, location_text);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location_spinner.setAdapter(dataAdapter);
        location_spinner.setOnItemClickListener(this);

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectCity(position);
        Intent intent = new Intent(GlobalClass.context,LaundryDetailsActivity.class);
        startActivity(intent);

    }

    private void selectCity(int position){
        switch(position){
            case 1:
                GlobalClass.location = "West Side Laundromat, Jersey City";
                break;
            case 2:
                GlobalClass.location = "Sip Avenue Laundromat, Jersey City";
                break;
            case 3:
                GlobalClass.location = "Ritz Laundromat, Jersey City";
                break;
            case 4:
                GlobalClass.location = "Bubbles and Suds Laundromat, Jersey City";
                break;
            case 5:
                GlobalClass.location = "American Laundromat, Jersey City";
                break;
            case 6:
                GlobalClass.location = "Bubble Laundromat, Jersey City";
                break;
            case 7:
                GlobalClass.location = "Lucky Laundromat, Jersey Avenue, Jersey City";
                break;
        }
    }
}
