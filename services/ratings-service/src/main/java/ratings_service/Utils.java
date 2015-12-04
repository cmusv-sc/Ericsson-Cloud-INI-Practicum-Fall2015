package ratings_service;

import org.apache.log4j.Logger;

public class Utils {
	public static void trace_log(String endpoint, String at, String to, String traceID, Class<?> context){
		Logger logger = Logger.getLogger(context);
    	StringBuilder str = new StringBuilder();
    	str.append("EricssonTrace:");
    	str.append("[traceID="+traceID+"] ");
    	str.append("[endpoint="+endpoint+"] ");
    	str.append("[timestamp="+System.currentTimeMillis()+"] ");
    	str.append("[S_at="+at+"] ");
    	str.append("[S_out="+to+"]");
    	logger.info(str.toString());
    }
}
