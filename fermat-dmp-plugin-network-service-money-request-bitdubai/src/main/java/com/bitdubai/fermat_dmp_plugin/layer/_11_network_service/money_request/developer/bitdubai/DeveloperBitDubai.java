package com.bitdubai.fermat_dmp_plugin.layer._11_network_service.money_request.developer.bitdubai;

import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_api.PluginDeveloper;
import com.bitdubai.fermat_api.layer._1_definition.enums.CryptoCurrency;
import com.bitdubai.fermat_api.layer._1_definition.enums.TimeFrequency;
import com.bitdubai.fermat_api.layer._6_license.PluginLicensor;
import com.bitdubai.fermat_dmp_plugin.layer._11_network_service.money_request.developer.bitdubai.version_1.MoneyRequestNetworkServicePluginRoot;

/**
 * Created by loui on 23/02/15.
 */
public class DeveloperBitDubai implements PluginDeveloper, PluginLicensor {
    
    Plugin plugin;
    
    @Override
    public Plugin getPlugin(){
        return plugin;        
    }
    
    public DeveloperBitDubai(){
    
        /**
         * I will Choose from the different versions of my implementation which one to start. NOw there is only one, so it is easy to choose.
         */
        
        plugin = new MoneyRequestNetworkServicePluginRoot();

    }
    @Override
    public int getAmountToPay() {
        return 100;
    }

    @Override
    public CryptoCurrency getCryptoCurrency() {
        return CryptoCurrency.BITCOIN;
    }

    @Override
    public String getAddress() {
        return "13gpMizSNvQCbJzAPyGCUnfUGqFD8ryzcv";
    }

    @Override
    public TimeFrequency getTimePeriod() {
        return TimeFrequency.MONTHLY;
    }
}

