package com.bitdubai.sub_app.crypto_broker_community.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import com.bitdubai.fermat_android_api.layer.definition.wallet.utils.ImagesUtils;
import com.bitdubai.fermat_android_api.ui.adapters.FermatAdapter;
import com.bitdubai.fermat_cbp_api.layer.sub_app_module.crypto_broker_community.interfaces.CryptoBrokerCommunityInformation;
import com.bitdubai.sub_app.crypto_broker_community.R;
import com.bitdubai.sub_app.crypto_broker_community.holders.AppNotificationsHolder;

import java.util.List;

/**
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 18/12/2015.
 *
 * @author lnacosta
 * @version 1.0.0
 */
public class AppNotificationAdapter extends FermatAdapter<CryptoBrokerCommunityInformation, AppNotificationsHolder> {

    public AppNotificationAdapter(Context context, List<CryptoBrokerCommunityInformation> lst) {
        super(context, lst);
    }

    @Override
    protected AppNotificationsHolder createHolder(View itemView, int type) {
        return new AppNotificationsHolder(itemView);
    }

    @Override
    protected int getCardViewResource() {
        return R.layout.row_connection_notification;
    }

    @Override
    protected void bindHolder(AppNotificationsHolder holder, CryptoBrokerCommunityInformation data, int position) {
        holder.userName.setText(data.getAlias());
        Bitmap bitmap;
        if (data.getImage().length > 0) {
            bitmap = BitmapFactory.decodeByteArray(data.getImage(), 0, data.getImage().length);
        } else {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.profile_image);
        }
        bitmap = Bitmap.createScaledBitmap(bitmap, 40,40, true);
        holder.userAvatar.setImageDrawable(ImagesUtils.getRoundedBitmap(context.getResources(), bitmap));
    }

    public int getSize() {
        if (dataSet != null)
            return dataSet.size();
        return 0;
    }
}
