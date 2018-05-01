package com.nextgen.integration.connector.oracle;

import com.datastax.driver.core.Session;

public interface DBConnector {
	
	void connect(String node, int port);

	Session getSession();

	void close();

}