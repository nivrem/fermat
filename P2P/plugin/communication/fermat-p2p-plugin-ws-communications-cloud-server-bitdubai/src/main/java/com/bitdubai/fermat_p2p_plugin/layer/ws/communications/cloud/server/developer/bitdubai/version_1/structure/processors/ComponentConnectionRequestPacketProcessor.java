/*
 * @#ComponentConnectionRequestPacketProcesor.java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_p2p_plugin.layer.ws.communications.cloud.server.developer.bitdubai.version_1.structure.processors;

import com.bitdubai.fermat_api.layer.all_definition.crypto.asymmetric.AsymmectricCryptography;
import com.bitdubai.fermat_api.layer.all_definition.crypto.asymmetric.ECCKeyPair;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.contents.FermatPacketCommunicationFactory;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.commons.contents.FermatPacketEncoder;
import com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.components.PlatformComponentProfile;
import com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.contents.FermatPacket;
import com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.enums.AttNamesConstants;
import com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.enums.FermatPacketType;
import com.bitdubai.fermat_p2p_plugin.layer.ws.communications.cloud.server.developer.bitdubai.version_1.structure.vpn.WsCommunicationVPNServer;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.java_websocket.WebSocket;

import java.net.URI;
import java.util.List;

/**
 * The Class <code>com.bitdubai.fermat_p2p_plugin.layer.ws.communications.cloud.server.developer.bitdubai.version_1.structure.processors.ComponentConnectionRequestPacketProcessor</code>
 * <p/>
 * Created by Roberto Requena - (rart3001@gmail.com) on 12/09/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class ComponentConnectionRequestPacketProcessor extends FermatPacketProcessor {

    /**
     * (no-javadoc)
     * @see FermatPacketProcessor#processingPackage(WebSocket, FermatPacket, ECCKeyPair)
     */
    @Override
    public void processingPackage(WebSocket clientConnection, FermatPacket receiveFermatPacket, ECCKeyPair serverIdentity) {


        System.out.println(" --------------------------------------------------------------------- ");
        System.out.println("ComponentConnectionRequestPacketProcessor - Starting processingPackage");

        /*
         * Get the packet content from the message content and decrypt
         */
        String packetContentJsonStringRepresentation = AsymmectricCryptography.decryptMessagePrivateKey(receiveFermatPacket.getMessageContent(), serverIdentity.getPrivateKey());
        System.out.println("ComponentConnectionRequestPacketProcessor - packetContentJsonStringRepresentation = "+packetContentJsonStringRepresentation);

        /*
         * Construct the json object
         */
        Gson gson = new Gson();

        /*
         * Get the list
         */
        List<PlatformComponentProfile> participantsList = gson.fromJson(packetContentJsonStringRepresentation, new TypeToken<List<PlatformComponentProfile>>(){}.getType());

        //Create a new vpn
        WsCommunicationVPNServer vpnServer = getWsCommunicationCloudServer().getWsCommunicationVpnServerManagerAgent().createNewWsCommunicationVPNServer(participantsList);

        PlatformComponentProfile peer1 = participantsList.get(0);
        PlatformComponentProfile peer2 = participantsList.get((participantsList.size() -1));

        constructRespondPacket(gson, vpnServer, peer1, peer2);
        constructRespondPacket(gson, vpnServer, peer2, peer1);

        //if no running
        if (!getWsCommunicationCloudServer().getWsCommunicationVpnServerManagerAgent().isRunning()){

            //Start the agent
            getWsCommunicationCloudServer().getWsCommunicationVpnServerManagerAgent().start();
        }


    }


    /**
     * Construct Respond Packet
     *
     * @param gson
     * @param vpnServer
     * @param platformComponentProfileDestination
     * @param remote
     */
    private void constructRespondPacket(Gson gson, WsCommunicationVPNServer vpnServer, PlatformComponentProfile platformComponentProfileDestination, PlatformComponentProfile remote){

        /*
         * Get json representation for the filters
         */
        JsonObject packetContent = new JsonObject();
        packetContent.addProperty(AttNamesConstants.JSON_ATT_NAME_VPN_URI,                          vpnServer.getUriConnection().toString());
        packetContent.addProperty(AttNamesConstants.JSON_ATT_NAME_VPN_SERVER_IDENTITY,              vpnServer.getVpnServerIdentityPublicKey());
        packetContent.addProperty(AttNamesConstants.JSON_ATT_NAME_NETWORK_SERVICE_TYPE,             platformComponentProfileDestination.getNetworkServiceType().toString());
        packetContent.addProperty(AttNamesConstants.JSON_ATT_NAME_REMOTE_PARTICIPANT_IDENTITY_VPN,  remote.getIdentityPublicKey());

        /*
         * Create the respond packet
         */
        FermatPacket fermatPacketRespond = FermatPacketCommunicationFactory.constructFermatPacketEncryptedAndSinged(platformComponentProfileDestination.getCommunicationCloudClientIdentity(),                                                                                        //Sender
                                                                                                                    getWsCommunicationCloudServer().getServerIdentityByClientCache().get(platformComponentProfileDestination.getCommunicationCloudClientIdentity()).getPublicKey(),   //Destination
                                                                                                                    gson.toJson(packetContent),                                                                                                                                        //packet Content
                                                                                                                    FermatPacketType.COMPONENT_CONNECTION_RESPOND,                                                                                                                     //Packet type
                                                                                                                    getWsCommunicationCloudServer().getServerIdentityByClientCache().get(platformComponentProfileDestination.getCommunicationCloudClientIdentity()).getPrivateKey()); //Sender private key
        /*
         * get the client connection destination
         */
        WebSocket clientConnectionDestination = getWsCommunicationCloudServer().getPendingRegisterClientConnectionsCache().get(fermatPacketRespond.getDestination());

        /*
         * Send the packet
         */
        clientConnectionDestination.send(FermatPacketEncoder.encode(fermatPacketRespond));

    }




    /**
     * (no-javadoc)
     * @see FermatPacketProcessor#getFermatPacketType()
     */
    @Override
    public FermatPacketType getFermatPacketType() {
        return FermatPacketType.COMPONENT_CONNECTION_REQUEST;
    }
}