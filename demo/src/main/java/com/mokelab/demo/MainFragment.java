package com.mokelab.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mokelab.demo.databinding.MainFragmentBinding;

/**
 * メイン画面
 */
public class MainFragment extends Fragment {

    private MainFragmentBinding binding;

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();

        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = MainFragmentBinding.inflate(inflater, container, false);

        this.binding.toolbar.inflateMenu(R.menu.menu_main);
        this.binding.toolbar.setOnMenuItemClickListener(this.menuListener);

        return this.binding.getRoot();
    }

    private final Toolbar.OnMenuItemClickListener menuListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
            case R.id.action_oss:
                showOSSPage();
                return true;
            }
            return true;
        }
    };

    private void showOSSPage() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        transaction.addToBackStack(null);
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.replace(getId(), MyOSSListFragment.newInstance());

        transaction.commit();
    }
}
