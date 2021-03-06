package com.bitdubai.sub_app.crypto_broker_community.fragmentFactory;

import com.bitdubai.fermat_android_api.engine.FermatFragmentFactory;
import com.bitdubai.fermat_android_api.layer.definition.wallet.AbstractFermatFragment;
import com.bitdubai.fermat_android_api.layer.definition.wallet.exceptions.FragmentNotFoundException;
import com.bitdubai.fermat_pip_api.layer.network_service.subapp_resources.SubAppResourcesProviderManager;
import com.bitdubai.sub_app.crypto_broker_community.fragments.ConnectionNotificationsFragment;
import com.bitdubai.sub_app.crypto_broker_community.fragments.ConnectionOtherProfileFragment;
import com.bitdubai.sub_app.crypto_broker_community.fragments.ConnectionsFragment;
import com.bitdubai.sub_app.crypto_broker_community.fragments.ConnectionsListFragment;
import com.bitdubai.sub_app.crypto_broker_community.fragments.ConnectionsWorldFragment;
import com.bitdubai.sub_app.crypto_broker_community.session.CryptoBrokerCommunitySubAppSession;

/**
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 16/12/2015.
 *
 * @author lnacosta
 * @version 1.0.0
 */
public class CryptoBrokerCommunityFragmentFactory extends FermatFragmentFactory<CryptoBrokerCommunitySubAppSession, SubAppResourcesProviderManager, CryptoBrokerCommunityFragmentsEnumType> {


    @Override
    public AbstractFermatFragment getFermatFragment(CryptoBrokerCommunityFragmentsEnumType fragments) throws FragmentNotFoundException {
        AbstractFermatFragment currentFragment = null;

        switch (fragments) {
            case CWP_WALLET_STORE_ALL_FRAGMENT:
                currentFragment = ConnectionsWorldFragment.newInstance();
                break;
            case CBP_SUB_APP_CRYPTO_BROKER_COMMUNITY_CONNECTIONS:
                currentFragment = ConnectionsFragment.newInstance();
                break;
            case CBP_SUB_APP_CRYPTO_BROKER_COMMUNITY_CONNECTION_DETAIL:
                currentFragment = null;
                break;
            case CBP_SUB_APP_CRYPTO_BROKER_COMMUNITY_CONNECTION_NOTIFICATIONS:
                currentFragment = ConnectionNotificationsFragment.newInstance();
                break;
            case CBP_SUB_APP_CRYPTO_BROKER_COMMUNITY_CONNECTION_OTHER_PROFILE:
                currentFragment = ConnectionOtherProfileFragment.newInstance();
                break;
            case CBP_SUB_APP_CRYPTO_BROKER_COMMUNITY_CONNECTION_WORLD:
                currentFragment = ConnectionsWorldFragment.newInstance();
                break;
            case CBP_SUB_APP_CRYPTO_BROKER_COMMUNITY_CONNECTION_FRIEND_LIST:
                currentFragment = ConnectionsListFragment.newInstance();
                break;
            default:
                throw new FragmentNotFoundException(
                        fragments.toString(),
                        "Switch failed"
                );
        }
        return currentFragment;
    }

    @Override
    public CryptoBrokerCommunityFragmentsEnumType getFermatFragmentEnumType(String key) {
        return CryptoBrokerCommunityFragmentsEnumType.getValue(key);
    }

}
