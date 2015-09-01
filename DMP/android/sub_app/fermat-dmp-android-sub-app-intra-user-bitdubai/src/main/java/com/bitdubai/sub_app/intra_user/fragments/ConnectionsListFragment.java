package com.bitdubai.sub_app.intra_user.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.bitdubai.fermat_android_api.layer.definition.wallet.FermatFragment;
import com.bitdubai.fermat_android_api.ui.adapters.FermatAdapter;
import com.bitdubai.fermat_android_api.ui.enums.FermatRefreshTypes;
import com.bitdubai.fermat_android_api.ui.fragments.FermatListFragment;
import com.bitdubai.fermat_android_api.ui.interfaces.FermatListItemListeners;
import com.bitdubai.fermat_api.layer.dmp_engine.sub_app_runtime.enums.SubApps;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces.IntraUserInformation;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces.IntraUserModuleManager;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.UnexpectedSubAppExceptionSeverity;
import com.bitdubai.sub_app.intra_user.common.adapters.IntraUserConnectionsAdapter;
import com.bitdubai.sub_app.intra_user.common.models.IntraUserConnectionListItem;
import com.bitdubai.sub_app.intra_user.common.models.WalletStoreListItem;
import com.bitdubai.sub_app.intra_user.session.IntraUserSubAppSession;
import com.bitdubai.sub_app.intra_user.util.CommonLogger;
import com.intra_user.bitdubai.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matias Furszyfer on 2015.08.31..
 */

public class ConnectionsListFragment extends FermatListFragment<IntraUserConnectionListItem> implements FermatListItemListeners<IntraUserConnectionListItem>, SearchView.OnQueryTextListener {

    IntraUserModuleManager intraUserModuleManager;
    private ErrorManager errorManager;
    private ArrayList<IntraUserConnectionListItem> intraUserItemList;

    private SearchView mSearchView;

    public static ConnectionsListFragment newInstance(){
        ConnectionsListFragment fragment = new ConnectionsListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            // setting up  module
            intraUserModuleManager = ((IntraUserSubAppSession) subAppsSession).getIntraUserModuleManager();
            errorManager = subAppsSession.getErrorManager();
            intraUserItemList = getMoreDataAsync(FermatRefreshTypes.NEW, 0); // get init data

        } catch (Exception ex) {
            CommonLogger.exception(TAG, ex.getMessage(), ex);
        }
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflater.inflate(R.menu.wallet_store_activity_wallet_menu, menu);
       // inflater.inflate(R.menu.wallet_store_activity_wallet_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        //MenuItem menuItem = new SearchView(getActivity());

        MenuItem searchItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setShowAsAction(searchItem, MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_ALWAYS);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        mSearchView.setOnQueryTextListener(this);



        //mSearchView.setIconifiedByDefault(false);

    }

    /**
     * Determine if this fragment use menu
     *
     * @return true if this fragment has menu, otherwise false
     */
    @Override
    protected boolean hasMenu() {
        return false;
    }

    /**
     * Get layout resource
     *
     * @return int layout resource Ex: R.layout.fragment_view
     */
    @Override
    protected int getLayoutResource() {
        return R.layout.intra_user_conecction_list;
    }

    @Override
    protected int getSwipeRefreshLayoutId() {
        return R.id.swipe_refresh;
    }

    @Override
    protected int getRecyclerLayoutId() {
        return R.id.connections_recycler_view;
    }

    @Override
    protected boolean recyclerHasFixedSize() {
        return true;
    }


    @Override
    public ArrayList<IntraUserConnectionListItem> getMoreDataAsync(FermatRefreshTypes refreshType, int pos) {
        ArrayList<IntraUserConnectionListItem> data=null;

        try {
            List<IntraUserInformation> lstIntraUser = intraUserModuleManager.getAllIntraUsers();
            //List<WalletStoreCatalogueItem> catalogueItems = catalogue.getWalletCatalogue(0, 0);

            data = new ArrayList<>();
            for (IntraUserInformation intraUserInformation : lstIntraUser) {
                IntraUserConnectionListItem item = new IntraUserConnectionListItem(intraUserInformation.getName(),null,intraUserInformation.getProfileImage(),"connected");
                data.add(item);
            }

        } catch (Exception e) {
            errorManager.reportUnexpectedSubAppException(SubApps.CWP_WALLET_STORE,
                    UnexpectedSubAppExceptionSeverity.DISABLES_SOME_FUNCTIONALITY_WITHIN_THIS_FRAGMENT, e);

            data = IntraUserConnectionListItem.getTestData(getResources());
        }
        data = IntraUserConnectionListItem.getTestData(getResources());

        return data;
    }

    /**
     * implement this function to handle the result object through dynamic array
     *
     * @param result array of native object (handle result field with result[0], result[1],... result[n]
     */
    @Override
    public void onPostExecute(Object... result) {
        isRefreshing = false;
        if (isAttached) {
            swipeRefreshLayout.setRefreshing(false);
            if (result != null && result.length > 0) {
                intraUserItemList = (ArrayList) result[0];
                if (adapter != null)
                    adapter.changeDataSet(intraUserItemList);
            }
        }
    }

    /**
     * Implement this function to handle errors during the execution of any fermat worker instance
     *
     * @param ex Throwable object
     */
    @Override
    public void onErrorOccurred(Exception ex) {
        isRefreshing = false;
        if (isAttached) {
            swipeRefreshLayout.setRefreshing(false);
            CommonLogger.exception(TAG, ex.getMessage(), ex);
        }
    }

    @Override
    public FermatAdapter getAdapter() {
        if (adapter == null) {
            adapter = new IntraUserConnectionsAdapter(getActivity(), intraUserItemList);
            adapter.setFermatListEventListener(this); // setting up event listeners
        }
        return adapter;
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }


    /**
     * onItem click listener event
     *
     * @param data
     * @param position
     */
    @Override
    public void onItemClickListener(IntraUserConnectionListItem data, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Hubo un problema");
        builder.setMessage("No se pudieron obtener los detalles de la wallet seleccionada");
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    /**
     * On Long item Click Listener
     *
     * @param data
     * @param position
     */
    @Override
    public void onLongItemClickListener(IntraUserConnectionListItem data, int position) {

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        Toast.makeText(getActivity(), "Probando busqueda completa", Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }
}