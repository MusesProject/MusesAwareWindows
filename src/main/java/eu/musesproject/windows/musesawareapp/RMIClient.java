package eu.musesproject.windows.musesawareapp;

/*
 * #%L
 * windows_client
 * %%
 * Copyright (C) 2013 - 2015 Sweden Connectivity
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import eu.musesproject.awareappinterface.AwareAction;
import eu.musesproject.awareappinterface.IMusesService;
import eu.musesproject.awareappinterface.IMusesServiceCallback;
import eu.musesproject.awareappinterface.Properties;

public class RMIClient extends UnicastRemoteObject implements Serializable,IMusesServiceCallback{

	private static final long serialVersionUID = -3246255795851849310L;

	public RMIClient() throws RemoteException{
		super(0);
	}

	public void sendActionToMuses(String string) {
		try {
			IMusesService rmi = (IMusesService)Naming.lookup("//localhost/RMI");
					
			Properties openAssetProperties = new Properties();
			openAssetProperties.setId(1);
			openAssetProperties.setProperty("path:/sdcard/aware_app_remote_files/MUSES_beer_competition.pdf");
			
			rmi.sendUserAction(new AwareAction("open_asset",1), openAssetProperties);
			
			Properties openConfAssetProperties = new Properties();
			openConfAssetProperties.setId(2);
			openConfAssetProperties.setProperty("resourceType:CONFIDENTIAL");
			
			rmi.sendUserAction(new AwareAction("wrongaction",1), openConfAssetProperties);
			
			rmi.registerCallback(this);
			
		} catch (AccessException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
	
	public void onAccept(String response) throws RemoteException {
		System.out.println("Response is: " + response);
	}

	public void onDeny(String response) throws RemoteException {
		System.out.println("Response is: " + response);
	}

}
