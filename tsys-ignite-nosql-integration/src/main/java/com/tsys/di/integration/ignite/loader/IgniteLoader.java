package com.tsys.di.integration.ignite.loader;

import org.apache.ignite.Ignite;
import org.apache.ignite.Ignition;

public class IgniteLoader {

	public static Ignite ignite;

	public static void startIgnite(String[] args) {
		ignite = Ignition.start("apacheignite-cassandra.xml");
	}

	

}
