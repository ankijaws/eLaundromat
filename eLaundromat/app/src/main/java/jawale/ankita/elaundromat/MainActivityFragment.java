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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    protected LinearLayout layout;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        final Spinner location_spinner = (Spinner)rootView.findViewById(R.id.location_spinner);

        ArrayList<String> location_text = new ArrayList<String>();
        location_text.add("West Side Laundromat, Jersey City");
        location_text.add("Sip Avenue Laundromat, Jersey City");
        location_text.add("Ritz Laundromat, Jersey City");
        location_text.add("Bubbles and Suds Laundromat, Jersey City");
        location_text.add("American Laundromat, Jersey City");
        location_text.add("Bubble Laundromat, Jersey City");
        location_text.add("Lucky Laundromat, Jersey Avenue, Jersey City");

        GlobalClass.context = getActivity().getApplicationContext();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(GlobalClass.context, R.layout.spinner_item, location_text);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location_spinner.setAdapter(dataAdapter);

        Button continue_btn = (Button) rootView.findViewById(R.id.btn_continue);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GlobalClass.location = location_spinner.getSelectedItem().toString().trim();
                Intent intent = new Intent(GlobalClass.context,LaundryDetailsActivity.class);
                startActivity(intent);
            }
        });


        return rootView;
    }

}
