package com.nextgen.integration.repository.ignite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.ignite.IgniteCache;
import org.apache.ignite.cache.query.QueryCursor;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.cache.store.cassandra.datasource.DataSource;
import org.apache.ignite.configuration.ClientConnectorConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.springdata.repository.config.EnableIgniteRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpointMetricReader;
import org.springframework.context.annotation.Bean;

import com.nextgen.integration.ignite.loader.IgniteLoader;
import com.nextgen.integration.model.Twitter;
@EnableIgniteRepositories
public class IgniteRepository {

	@Autowired
	DataSource datasource;
	
	@Bean
	public MetricsEndpointMetricReader metricsEndpointMetricReader(final MetricsEndpoint metricsEndpoint) {
		return new MetricsEndpointMetricReader(metricsEndpoint);
	}
	
	public static Connection getConnection() {
		try {
			IgniteConfiguration cfg = new IgniteConfiguration()
					.setClientConnectorConfiguration(new ClientConnectorConfiguration());
			Class.forName("org.apache.ignite.IgniteJdbcThinDriver");

			// Open the JDBC connection.
			Connection conn = DriverManager.getConnection("jdbc:ignite:thin://127.0.0.1");
			return conn;
		} catch (Exception e) {

		}
		return null;
	}
	
	
	public static void getSelectData(String query)  {
		try {
			// Query people with specific age using prepared statement.
			PreparedStatement stmt = getConnection().prepareStatement(query);
			//stmt.setInt(1, 30);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
			    String name = rs.getString("name");
			    String description = rs.getString("description");
			    System.out.println("Data:-------"+ name + ""  + description);
			}
		}catch (Exception e) {
			
		}
	
	}
	
	public static void getSelectDataById(String query, int id) throws Exception {
		// Query people with specific age using prepared statement.
		PreparedStatement stmt = getConnection().prepareStatement(query);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		 
		while (rs.next()) {
		    String name = rs.getString("name");
		    String description = rs.getString("description");
		    System.out.println("Data:-------"+ name + ""  + description);
		}
	}
	
	public static List<List<?>> selectFromIgnite() {
		IgniteCache<Long, Twitter> cache = IgniteLoader.ignite.getOrCreateCache("Twitter");
		// Load cache with data from the database.
		cache.loadCache(null);
		// Execute query on cache.
		QueryCursor<List<?>> cursor = cache.query(new SqlFieldsQuery("select * from twitter limit 1000"));
		List<List<?>> tweet = cursor.getAll();
		//System.out.println(tweet);
		return tweet;
	}
	
	public static List<List<?>> selectFromIgniteById(long id) {
		IgniteCache<Long, Twitter> cache = IgniteLoader.ignite.getOrCreateCache("Twitter");
		// Load cache with data from the database.
		cache.loadCache(null);
		// Execute query on cache.
		QueryCursor<List<?>> cursor = cache.query(new SqlFieldsQuery("select * from twitter where id = " + id));
		List<List<?>> tweet = cursor.getAll();
		//System.out.println(tweet);
		return tweet;
	}
	
	public static String insertFromIngnite(long id, Twitter twitter) {
		IgniteCache<Long, Twitter> cache = IgniteLoader.ignite.getOrCreateCache("Twitter");
		cache.loadCache(null);
		cache.put(id, twitter);
		return "Success";
	}
	

}
