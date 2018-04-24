package com.tsys.di.integration.connector.cassandra;

import static java.lang.System.out;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;

/**
 * Class used for connecting to Cassandra database.
 */
public class CassandraConnector implements DBConnector {
	/** Cassandra Cluster. */
	private Cluster cluster;

	/** Cassandra Session. */
	private Session session;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tsys.di.integration.connector.cassandra.DBConnector#connect(java.lang.
	 * String, int)
	 */
	@Override
	public void connect(final String node, final int port) {
		this.cluster = Cluster.builder().addContactPoint(node).withPort(port).build();
		final Metadata metadata = cluster.getMetadata();
		out.printf("Connected to cluster: %s\n", metadata.getClusterName());
		for (final Host host : metadata.getAllHosts()) {
			out.printf("Datacenter: %s; Host: %s; Rack: %s\n", host.getDatacenter(), host.getAddress(), host.getRack());
		}
		session = cluster.connect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsys.di.integration.connector.cassandra.DBConnector#getSession()
	 */
	@Override
	public Session getSession() {
		return this.session;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tsys.di.integration.connector.cassandra.DBConnector#close()
	 */
	@Override
	public void close() {
		cluster.close();
	}
}