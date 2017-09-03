package com.mokelab.oss;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.content.AsyncTaskLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Loader for License file
 */

class LicenseLoader extends AsyncTaskLoader<String> {
    private final String fileName;

    LicenseLoader(Context context, String fileName) {
        super(context);
        this.fileName = fileName;
    }

    @Override
    public String loadInBackground() {
        AssetManager manager = getContext().getAssets();

        InputStream in = null;
        InputStreamReader sr = null;
        BufferedReader br = null;
        try {
            in = manager.open(this.fileName);
            sr = new InputStreamReader(in);
            br = new BufferedReader(sr);

            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString();
        } catch (IOException e) {
            return "IO Exception " + e.getMessage();
        } finally {
            if (br != null) try { br.close(); } catch (IOException e2) { /* */ }
            if (sr != null) try { sr.close(); } catch (IOException e2) { /* */ }
            if (in != null) try { in.close(); } catch (IOException e2) { /* */ }
        }
    }
}
