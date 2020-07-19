package thcs.ddt.main;

import javax.servlet.ServletContextListener;
import javax.validation.constraints.NotNull;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MainApplication.class);
	}

	@NotNull
	@Bean
	ServletListenerRegistrationBean<ServletContextListener> myServletListener() {
		ServletListenerRegistrationBean<ServletContextListener> srb =
						new ServletListenerRegistrationBean<>();
		srb.setListener(new StardogServletContextListener());
		return srb;
	}

}
