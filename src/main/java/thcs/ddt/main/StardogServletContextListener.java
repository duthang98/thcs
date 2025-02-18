package thcs.ddt.main;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.complexible.stardog.Stardog;

/**
 * Start/Stop the Stardog embedded server on application startup/shutdown
 */
public class StardogServletContextListener implements ServletContextListener {
	private static Stardog stardog = null;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		if (Boolean.parseBoolean(sce.getServletContext().getInitParameter("stardog.useEmbeddedServer"))) {
			stardog = Stardog.builder().create();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		if (stardog != null) {
			stardog.shutdown();
		}
	}
}
