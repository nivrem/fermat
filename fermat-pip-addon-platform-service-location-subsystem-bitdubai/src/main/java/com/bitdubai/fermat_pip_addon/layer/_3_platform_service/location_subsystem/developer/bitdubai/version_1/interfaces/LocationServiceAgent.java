package com.bitdubai.fermat_pip_addon.layer._3_platform_service.location_subsystem.developer.bitdubai.version_1.interfaces;

import com.bitdubai.fermat_pip_addon.layer._3_platform_service.location_subsystem.developer.bitdubai.version_1.exception.LocationServiceException;

/**
 * Created by firuzzz on 5/12/15.
 */
public interface LocationServiceAgent {
    void start() throws LocationServiceException;

    void stop();
}
