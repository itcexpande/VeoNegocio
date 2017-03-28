package com.expandenegocio.veonegocio.activities.activitis;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by jesus on 28/03/2017.
 */

public class UserRestClientUso {

    public void getPublicTimeline() throws JSONException {
        UserRestClient.get("statuses/public_timeline.json", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                JSONObject firstEvent = null;
                try {
                    //    firstEvent = timeline.get(0);
                    String tweetText = firstEvent.getString("text");
                } catch (JSONException e) {
                    e.printStackTrace();
                }


                // Do something with the response
                //   System.out.println(tweetText);
            }
        });
    }
}