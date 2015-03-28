/**
 * 
 */
package mx.gob.segob.nsjp.web.base.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author LuisMG
 *
 */
public class ManejadorSesion implements HttpSessionListener {

	
	private static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();  
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		   sessions.put(se.getSession().getId(), se.getSession());

	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		 sessions.remove(se.getSession().getId());


	}
	
	public static boolean invalidate(String sessionId) {
        HttpSession session = sessions.get(sessionId);

        if (session != null) {
            session.invalidate();
            return true;
        } else {
            return false;
        }
    }


}
