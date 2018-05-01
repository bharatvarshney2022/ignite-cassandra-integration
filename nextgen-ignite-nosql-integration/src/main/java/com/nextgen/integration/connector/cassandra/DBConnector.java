package com.nextgen.integration.connector.cassandra;

import com.datastax.driver.core.Session;

public interface DBConnector {

	/**
	 * Connect to Cassandra Cluster specified by provided node IP address and port
	 * number.
	 * 
	 * @param node
	 *            Cluster node IP address.
	 * @param port
	 *            Port of cluster host.
	 */
	void connect(String node, int port);

	/**
	 * Provide my Session.
	 * 
	 * @return My session.
	 */
	Session getSession();

	/** Close cluster. */
	void close();

}