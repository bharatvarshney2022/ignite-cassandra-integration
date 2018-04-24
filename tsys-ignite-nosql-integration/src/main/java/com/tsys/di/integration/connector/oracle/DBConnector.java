package com.tsys.di.integration.connector.oracle;

import com.datastax.driver.core.Session;

public interface DBConnector {
	
	void connect(String node, int port);

	Session getSession();

	void close();

}