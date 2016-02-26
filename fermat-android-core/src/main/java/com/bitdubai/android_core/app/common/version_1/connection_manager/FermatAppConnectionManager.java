package com.bitdubai.android_core.app.common.version_1.connection_manager;

import android.app.Activity;

import com.bitdubai.fermat_android_api.layer.definition.wallet.interfaces.AppConnections;
import com.bitdubai.fermat_android_api.layer.definition.wallet.interfaces.FermatSession;
import com.bitdubai.fermat_dap_android_sub_app_asset_factory_bitdubai.app_connection.AssetFactoryFermatAppConnection;
import com.bitdubai.fermat_dap_android_sub_app_asset_issuer_community_bitdubai.app_connection.CommunityAssetIssuerFermatAppConnection;
import com.bitdubai.fermat_dap_android_sub_app_asset_issuer_identity_bitdubai.app_connection.AssetIssuerFermatAppConnection;
import com.bitdubai.fermat_dap_android_sub_app_asset_user_community_bitdubai.app_connection.CommunityAssetUserFermatAppConnection;
import com.bitdubai.fermat_dap_android_sub_app_asset_user_identity_bitdubai.app_connection.AssetUserFermatAppConnection;
import com.bitdubai.fermat_dap_android_sub_app_redeem_point_community_bitdubai.app_connection.CommunityRedeemPointFermatAppConnection;
import com.bitdubai.fermat_dap_android_sub_app_redeem_point_identity_bitdubai.app_connection.RedeemPointFermatAppConnection;
import com.bitdubai.fermat_dap_android_wallet_asset_issuer_bitdubai.app_connection.WalletAssetIssuerFermatAppConnection;
import com.bitdubai.fermat_dap_android_wallet_asset_user_bitdubai.app_connection.WalletAssetUserFermatAppConnection;
import com.bitdubai.fermat_dap_android_wallet_redeem_point_bitdubai.app_connection.WalletRedeemPointFermatAppConnection;
import com.bitbudai.fermat_cht_android_sub_app_chat_bitdubai.app_connection.ChatFermatAppConnection;
import com.bitdubai.reference_niche_wallet.bitcoin_wallet.app_connection.BitcoinWalletFermatAppConnection;
//import com.bitdubai.reference_wallet.bank_money_wallet.app_connection.BankMoneyWalletFermatAppConnection;
import com.bitdubai.reference_wallet.bank_money_wallet.app_connection.BankMoneyWalletFermatAppConnection;
import com.bitdubai.reference_wallet.cash_money_wallet.app_connection.CashMoneyWalletFermatAppConnection;
import com.bitdubai.reference_wallet.crypto_broker_wallet.app_connection.CryptoBrokerWalletFermatAppConnection;
import com.bitdubai.reference_wallet.crypto_customer_wallet.app_connection.CryptoCustomerWalletFermatAppConnection;
import com.bitdubai.sub_app.crypto_broker_community.app_connection.CryptoBrokerCommunityFermatAppConnection;
import com.bitdubai.sub_app.crypto_broker_identity.app_connection.CryptoBrokerIdentityFermatAppConnection;
import com.bitdubai.sub_app.crypto_customer_community.app_connection.CryptoCustomerCommunityFermatAppConnection;
import com.bitdubai.sub_app.crypto_customer_identity.app_connection.CryptoCustomerIdentityFermatAppConnection;
import com.bitdubai.sub_app.developer.app_connection.DeveloperFermatAppConnection;
import com.bitdubai.sub_app.intra_user_community.app_connection.CryptoWalletUserCommunityFermatAppConnection;
import com.bitdubai.sub_app.intra_user_identity.app_connection.CryptoWalletUserFermatAppConnection;
import com.bitdubai.sub_app.wallet_manager.app_connection.DesktopFermatAppConnection;
import com.bitdubai.sub_app.wallet_store.app_connection.WalletStoreFermatAppConnection;

/**
 * Created by Matias Furszyfer on 2015.12.09..
 */
public class FermatAppConnectionManager {



    private static AppConnections switchStatement(Activity activity,String publicKey,FermatSession fermatSession){
        AppConnections fermatAppConnection = null;

        switch (publicKey){
            //CCP WALLET
            case "reference_wallet":
                fermatAppConnection = new BitcoinWalletFermatAppConnection(activity);
                break;
            //CCP Sub Apps
            case "public_key_ccp_intra_user_identity":
                fermatAppConnection = new CryptoWalletUserFermatAppConnection(activity);
                break;
            case "public_key_intra_user_commmunity":
                fermatAppConnection = new CryptoWalletUserCommunityFermatAppConnection(activity);
                break;

            //DESKTOP
            case "main_desktop":
                fermatAppConnection = new DesktopFermatAppConnection(activity);
                break;

            //DAP WALLETS
            case "asset_issuer" :
                fermatAppConnection = new WalletAssetIssuerFermatAppConnection(activity);
                break;
            case "asset_user"   :
                fermatAppConnection = new WalletAssetUserFermatAppConnection(activity);
                break;
            case "redeem_point" :
                fermatAppConnection = new WalletRedeemPointFermatAppConnection(activity);
                break;
            //DAP Sub Apps
            case "public_key_dap_asset_issuer_identity":
                fermatAppConnection = new AssetIssuerFermatAppConnection(activity);
                break;
            case "public_key_dap_asset_user_identity":
                fermatAppConnection = new AssetUserFermatAppConnection(activity);
                break;
            case "public_key_dap_redeem_point_identity":
                fermatAppConnection = new RedeemPointFermatAppConnection(activity);
                break;
            case "public_key_dap_factory":
                fermatAppConnection = new AssetFactoryFermatAppConnection(activity);
                break;
            case "public_key_dap_issuer_community":
                fermatAppConnection = new CommunityAssetIssuerFermatAppConnection(activity);
                break;
            case "public_key_dap_user_community":
                fermatAppConnection = new CommunityAssetUserFermatAppConnection(activity);
                break;
            case "public_key_dap_redeem_point_community":
                fermatAppConnection = new CommunityRedeemPointFermatAppConnection(activity);
                break;

            //PIP Sub Apps
            case "public_key_pip_developer_sub_app":
                fermatAppConnection = new DeveloperFermatAppConnection(activity);
                break;

            //CBP WALLETS
            case "crypto_broker_wallet":
                fermatAppConnection = new CryptoBrokerWalletFermatAppConnection(activity);
                break;
            case "crypto_customer_wallet":
                fermatAppConnection = new CryptoCustomerWalletFermatAppConnection(activity);
                break;
            //CBP Sub Apps
            case "public_key_crypto_broker_community":
                fermatAppConnection = new CryptoBrokerCommunityFermatAppConnection(activity);
                break;
            case "public_key_crypto_customer_community":
                fermatAppConnection = new CryptoCustomerCommunityFermatAppConnection(activity);
                break;
            case "sub_app_crypto_broker_identity":
                fermatAppConnection = new CryptoBrokerIdentityFermatAppConnection(activity);
                break;
            case "sub_app_crypto_customer_identity":
                fermatAppConnection = new CryptoCustomerIdentityFermatAppConnection(activity);
                break;

            //CASH WALLET
            case "cash_wallet":
                fermatAppConnection = new CashMoneyWalletFermatAppConnection(activity, null);
                break;

            //BANKING WALLET
           case "banking_wallet":
               fermatAppConnection = new BankMoneyWalletFermatAppConnection(activity);
               break;

            // WPD Sub Apps
            case "public_key_store":
                fermatAppConnection = new WalletStoreFermatAppConnection(activity);
                break;

            // CHT Sub Apps
            case "public_key_cht_chat":
                fermatAppConnection = new ChatFermatAppConnection(activity);
                break;
        }

        return fermatAppConnection;
    }


    public static AppConnections getFermatAppConnection(
            String publicKey,
            Activity activity,
            FermatSession fermatSession) {
        AppConnections fermatAppConnection = switchStatement(activity,publicKey,fermatSession);
        fermatAppConnection.setFullyLoadedSession(fermatSession);
        return fermatAppConnection;
    }


    public static AppConnections getFermatAppConnection(String appPublicKey, Activity activity) {
        return switchStatement(activity,appPublicKey, null);
    }
}