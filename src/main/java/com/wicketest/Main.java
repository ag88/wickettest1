package com.wicketest;

import java.lang.management.ManagementFactory;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.management.MBeanServer;
import javax.servlet.ServletException;
import javax.websocket.DeploymentException;

import org.apache.wicket.protocol.ws.javax.WicketServerEndpointConfig;
import org.eclipse.jetty.server.AsyncRequestLogWriter;
import org.eclipse.jetty.server.CustomRequestLog;
import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.RequestLog;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Main {

	static Logger log = LoggerFactory.getLogger(Main.class);
	
	int port = 8080;
	int secport = 8443;

	String wicketconfig = "deployment";
	
	int connectIdleTimeout = 1000 * 30;

	boolean accesslogenabled = false;
	
	boolean accesslogappend = true;
	
	String accesslogfilename = "access.log";
	
	int maxThreads = 10;
	int minThreads = 1;
	int threadsIdleTimeout = 120;
	
	public Main() {
	}
	
	public void run(String[] args) {
		if (args.length > 0)
			port = Integer.valueOf(args[0]);

		System.setProperty("wicket.configuration", "development");
		//System.setProperty("wicket.configuration", wicketconfig);
		
		QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, threadsIdleTimeout);

		Server server = new Server(threadPool);

		HttpConfiguration http_config = new HttpConfiguration();
		http_config.setSecureScheme("https");
		http_config.setSecurePort(8443);
		http_config.setOutputBufferSize(32768);

		ServerConnector http = new ServerConnector(server, new HttpConnectionFactory(http_config));
		http.setPort(port);
		//http.setIdleTimeout(1000 * 60 * 60);
		http.setIdleTimeout(connectIdleTimeout);

		server.addConnector(http);

		Resource keystore = Resource.newClassPathResource("/keystore");
		if (keystore != null && keystore.exists())
		{
			// if a keystore for a SSL certificate is available, start a SSL
			// connector on port 8443.
			// By default, the quickstart comes with a Apache Wicket Quickstart
			// Certificate that expires about half way september 2031. Do not
			// use this certificate anywhere important as the passwords are
			// available in the source.

			SslContextFactory sslContextFactory = new SslContextFactory.Server();
			sslContextFactory.setKeyStoreResource(keystore);
			sslContextFactory.setKeyStorePassword("wicket");
			sslContextFactory.setKeyManagerPassword("wicket");

			HttpConfiguration https_config = new HttpConfiguration(http_config);
			https_config.addCustomizer(new SecureRequestCustomizer());

			ServerConnector https = new ServerConnector(server, new SslConnectionFactory(
				sslContextFactory, "http/1.1"), new HttpConnectionFactory(https_config));
			https.setPort(secport);
			//https.setIdleTimeout(500000);
			https.setIdleTimeout(connectIdleTimeout);

			server.addConnector(https);
			System.out.println("SSL access to the examples has been enabled on port 8443");
			System.out
				.println("You can access the application using SSL on https://localhost:8443");
			System.out.println();
		}

		
		
		WebAppContext context = new WebAppContext();
		context.setServer(server);
		context.setContextPath("/");
		//bb.setWar("/webapp");
		context.setBaseResource(Resource.newResource(Main.class.getResource("/webapp")));
		
		// bb.getSessionHandler().setSessionCache(sessionCache);

		ServerContainer serverContainer;
		try {
			serverContainer = WebSocketServerContainerInitializer.initialize(context);
			serverContainer.addEndpoint(new WicketServerEndpointConfig());
		} catch (ServletException e1) {
			log.error( e1.getMessage());
		} catch (DeploymentException e) {
			log.error(e.getMessage());
		}
		
		// uncomment next line if you want to test with JSESSIONID encoded in the urls
		// ((AbstractSessionManager)
		// bb.getSessionHandler().getSessionManager()).setUsingCookies(false);

		server.setHandler(context);

		/*
		MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
		MBeanContainer mBeanContainer = new MBeanContainer(mBeanServer);
		server.addEventListener(mBeanContainer);
		server.addBean(mBeanContainer);
		*/
		
        
		if (accesslogenabled) {
			Path logsDir = Paths.get(System.getProperty("user.dir"));
			
	        AsyncRequestLogWriter requestLogWriter = new AsyncRequestLogWriter();
	        requestLogWriter.setAppend(accesslogappend);
	        requestLogWriter.setFilename(logsDir.resolve(accesslogfilename).toString());
	        requestLogWriter.setRetainDays(1);
	        RequestLog requestLog = new CustomRequestLog(requestLogWriter, CustomRequestLog.NCSA_FORMAT);
	        //RequestLog requestLog = new CustomRequestLog(requestLogWriter, CustomRequestLog.EXTENDED_NCSA_FORMAT);
	        server.setRequestLog(requestLog);
		}
		
		try
		{
			server.start();
			server.join();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(100);
		}	
	}
		

	public static void main(String[] args) {
		Main main = new Main();
		main.run(args);
		
	}

}
