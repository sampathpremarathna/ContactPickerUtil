package com.mobitel.nalaka.piccon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.cordova.api.PluginResult;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * User: nalakap
 * Date: 8/15/13
 * Time: 5:33 PM
 * Project :ContactPicker
 * Company : Mobitel(Pvt)Ltd
 */
public class ContactPick extends CordovaPlugin {
    /*
    start cord for contact pick activity
     */
    final int CONTACT_RESULT_START = 23;
    /*
    CallbackContext for sending result
     */
    CallbackContext resultHolder;

    @Override
    public boolean execute(String action, String rawArgs, CallbackContext callbackContext) throws JSONException {
        /*
         * require to request as 'pick'
         */
        if (action.equals("pick")) {
            this.startContactActivity(callbackContext);
            PluginResult pluginResult = new PluginResult(PluginResult.Status.NO_RESULT);
            pluginResult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginResult);
            return true;
        }
        return false;
    }

    /*
    start ContactResult Activity and assign him to get user required contact with him
     */
    public void startContactActivity(CallbackContext callbackContext) {
        resultHolder = callbackContext;
        Intent in = new Intent(cordova.getActivity().getApplicationContext(), ContactResult.class);
        cordova.startActivityForResult(ContactPick.this, in, CONTACT_RESULT_START);

    }


    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        /*
        after came back from ContactResult check whether returned results are not null and
        then send result to javaScript
        then send result to javaScript
        then send result to javaScript
         */
        if (reqCode == CONTACT_RESULT_START & resultCode == Activity.RESULT_OK) {
            String finalName ;
            String finalMobile ;

            Bundle result = data.getExtras();
            finalName = result.getString("name") == null ? "" : result.getString("name");
            finalMobile = result.getString("number") == null ? "" : result.getString("number");

            try {
                JSONObject j = new JSONObject();
                j.put("name", finalName);
                j.put("number", finalMobile);
                resultHolder.success(j);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}