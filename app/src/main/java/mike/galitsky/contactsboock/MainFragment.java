package mike.galitsky.contactsboock;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import java.util.List;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


public class MainFragment extends Fragment {

    private List<Contact> contacts;
    private static  final String EXTRA_ITEMS = "mike.galitsky.myshop.EXTRA.ITEMS";

    public MainFragment() {

    }


    public static MainFragment newInstance(List<Contact> contacts) {
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);

    }


}
