package com.bitdubai.fermat_core.layer._11_network_service.crypto_addresses;

import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_api.layer._11_network_service.CantStartSubsystemException;
import com.bitdubai.fermat_api.layer._11_network_service.NetworkSubsystem;
import com.bitdubai.fermat_dmp_plugin.layer._11_network_service.crypto_addresses.developer.bitdubai.DeveloperBitDubai;

/**
 * Created by loui on 20/02/15.
 */
public class CryptoAddressesSubsystem implements NetworkSubsystem {

    Plugin plugin;

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public void start() throws CantStartSubsystemException {
        /**
         * I will choose from the different Developers available which implementation to use. Right now there is only
         * one, so it is not difficult to choose.
         */

        try {
            DeveloperBitDubai developerBitDubai = new DeveloperBitDubai();
            plugin = developerBitDubai.getPlugin();
        }
        catch (Exception e)
        {
            System.err.println("Exception: " + e.getMessage());
            throw new CantStartSubsystemException();
        }
    }

}