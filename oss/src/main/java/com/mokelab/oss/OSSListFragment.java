package com.mokelab.oss;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * OSSListFragment shows OSS list
 */

public abstract class OSSListFragment extends ListFragment {
    public static final String TITLE_TEXT_COLOR = "titleTextColor";

    private int titleTextColor = Color.WHITE;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            this.titleTextColor = args.getInt(TITLE_TEXT_COLOR, Color.WHITE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.com_mokelab_oss_list_fragment, container, false);

        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(titleTextColor);
        Drawable navigationIcon = toolbar.getNavigationIcon();
        if (navigationIcon != null) {
            navigationIcon.setColorFilter(titleTextColor, PorterDuff.Mode.MULTIPLY);
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStackImmediate();
            }
        });

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<OSSLibrary> libraries = getLibraries();
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, libraries));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        OSSLibrary library = (OSSLibrary) l.getItemAtPosition(position);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.addToBackStack("ossList");
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(getId(), OSSFragment.newInstance(library, titleTextColor));

        transaction.commit();
    }

    abstract protected List<OSSLibrary> getLibraries();
}
