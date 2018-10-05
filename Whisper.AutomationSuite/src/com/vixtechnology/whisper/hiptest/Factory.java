package com.vixtechnology.whisper.hiptest;

import com.vixtechnology.whisper.hiptest.*;
import com.vixtechnology.whip.framework.Log;

public class Factory
{
    static public Actionwords createActionwords() {
        Actionwords retval = null;
 //       if(0 < System.getenv("VIX_WHIP_DEPLOYMENT_ENVIRONMENT").length()) {
            retval = new ActionwordsImplementation();
 //       } else {
/*            Log.info("VIX_WHIP_DEPLOYMENT_ENVIRONMENT undefined");
            Log.info("Dry run - all tests which require Actionwords support");
            Log.info("will fail with a NullPointerException.");
        }
*/			
        return retval;
    }
}