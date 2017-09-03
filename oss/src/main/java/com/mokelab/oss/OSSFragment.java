package com.mokelab.oss;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * OSSFragment shows OSS license
 */

public class OSSFragment extends Fragment {
    private static final String ARGS_ITEM = "item";
    private static final String ARGS_TITLE_TEXT_COLOR = "titleTextColor";
    private static final int ID_LOADER = 1;

    private OSSLibrary library;
    private int titleTextColor;

    private TextView bodyText;

    static OSSFragment newInstance(OSSLibrary library, int titleTextColor) {
        OSSFragment fragment = new OSSFragment();

        Bundle args = new Bundle();
        args.putParcelable(ARGS_ITEM, library);
        args.putInt(ARGS_TITLE_TEXT_COLOR, titleTextColor);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        this.library = args.getParcelable(ARGS_ITEM);
        this.titleTextColor = args.getInt(ARGS_TITLE_TEXT_COLOR);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.com_mokelab_oss_fragment, container, false);

        Toolbar toolbar = root.findViewById(R.id.toolbar);
        toolbar.setTitle(this.library.getName());
        toolbar.setTitleTextColor(this.titleTextColor);
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

        this.bodyText = root.findViewById(android.R.id.text1);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LoaderManager manager = getLoaderManager();
        Bundle args = new Bundle();
        args.putParcelable(ARGS_ITEM, this.library);

        manager.initLoader(ID_LOADER, args, this.callbacks);
    }

    private final LoaderManager.LoaderCallbacks<String> callbacks = new LoaderManager.LoaderCallbacks<String>() {
        @Override
        public Loader<String> onCreateLoader(int id, Bundle args) {
            OSSLibrary library = args.getParcelable(ARGS_ITEM);
            assert library != null;

            LicenseLoader loader = new LicenseLoader(getActivity(), library.getFileNameInAsset());
            loader.forceLoad();
            return loader;
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String data) {
            if (bodyText == null) { return; }
            bodyText.setText(data);
        }

        @Override
        public void onLoaderReset(Loader<String> loader) {

        }
    };
}
