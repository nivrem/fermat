package com.bitdubai.fermat_cbp_plugin.layer.business_transaction.customer_offline_payment.developer.bitdubai.version_1.database.CustomerOfflinePaymentBusinessTransactionDaoTest;

import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseFactory;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_cbp_plugin.layer.business_transaction.customer_offline_payment.developer.bitdubai.version_1.database.CustomerOfflinePaymentBusinessTransactionDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


import java.util.UUID;


/**
 * Created by alexander jimenez (alex_jimenez76@hotmail.com) on 01/02/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class initializeTest {

    @Mock
    private PluginDatabaseSystem mockPluginDatabaseSystem;
    @Mock
    private Database mockDatabase;

    private UUID testId;
    private CustomerOfflinePaymentBusinessTransactionDao customerOfflinePaymentBusinessTransactionDao;


    @Test
    public void TestInitialize_Should_Run_Once() throws Exception{
        testId = UUID.randomUUID();
        customerOfflinePaymentBusinessTransactionDao = new CustomerOfflinePaymentBusinessTransactionDao(mockPluginDatabaseSystem,testId,mockDatabase);
        customerOfflinePaymentBusinessTransactionDao.initialize();
    }

    @Test(expected = Exception.class)
    public void TestCreateDatabase_Should_Return_Exception() throws Exception{
        customerOfflinePaymentBusinessTransactionDao = new CustomerOfflinePaymentBusinessTransactionDao(null,testId,mockDatabase);
        customerOfflinePaymentBusinessTransactionDao.initialize();
    }
}