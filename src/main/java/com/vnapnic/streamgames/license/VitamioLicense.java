package com.vnapnic.streamgames.license;

import android.content.Context;

import com.vnapnic.streamgames.R;
import de.psdev.licensesdialog.licenses.License;

public class VitamioLicense extends License {

    @Override
    public String getName() {
        return "Vitamio License";
    }

    @Override
    public String getSummaryText(final Context context) {
        return getContent(context, R.raw.vitamio_summary);
    }

    @Override
    public String getFullText(final Context context) {
        return getContent(context, R.raw.vitamio_full);
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String getUrl() {
        return "http://www.vitamio.org";
    }
}