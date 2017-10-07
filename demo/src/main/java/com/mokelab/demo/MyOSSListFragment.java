package com.mokelab.demo;

import com.mokelab.oss.OSSLibrary;
import com.mokelab.oss.OSSListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class MyOSSListFragment extends OSSListFragment {

    public static MyOSSListFragment newInstance() {
        return new MyOSSListFragment();
    }

    @Override
    protected List<OSSLibrary> getLibraries() {
        List<OSSLibrary> list = new ArrayList<>();
        list.add(new OSSLibrary("glide", "glide.txt"));
        list.add(new OSSLibrary("OSS-Page", "oss_page.txt"));

        return list;
    }
}
