package com.bitdubai.android_core.layer._2_os.android.developer.bitdubai.version_1;

import android.content.Context;
import com.bitdubai.android_fermat_pip_addon.layer_2_os.location_system.version_1.DeviceLocationOsAddonRoot;
import com.bitdubai.fermat_api.layer._2_os.LocationSystemOs;
import com.bitdubai.fermat_api.layer._2_os.location_system.LocationManager;

/**
 * Created by toshiba on 21/05/2015.
 */
public class AndroidOsLocationSystem implements LocationSystemOs {
    /**
     * LocationSystemOs interface member variables.
     */


    LocationManager locationManager;

    LocationSystemOs locationSystemOs;

    Context context;

    /**
     * Constructor
     */

    public AndroidOsLocationSystem() {

        locationSystemOs =  new DeviceLocationOsAddonRoot();
        this.locationManager = locationSystemOs.getLocationSystem();


    }
    /**
     * LocationSystemOs interface implementation.
     */

    @Override
    public LocationManager getLocationSystem()
    {
        return this.locationManager;
    }

    @Override
    public void setContext(Object context) {

        this.context = (Context) context;
        this.locationManager.setContext(this.context);



    }
}

