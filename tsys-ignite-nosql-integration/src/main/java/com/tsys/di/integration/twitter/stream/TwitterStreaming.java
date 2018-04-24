package com.tsys.di.integration.twitter.stream;

import com.tsys.di.integration.repository.cassandra.CassandraRepository;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreaming {

	static ConfigurationBuilder cb = new ConfigurationBuilder().setDebugEnabled(true)
			.setOAuthConsumerKey("rj6gHLWimMlo2cmnnZHRX36dz")
			.setOAuthConsumerSecret("5yEbzYDv9AihPi5YxTgTcss1Z9APDpqJaLzuLxXMwTMBLLwlRv")
			.setOAuthAccessToken("223823500-RXeW8kRwERL7QGl0mFBWLgeOiOFjDVBg1jZTZebE")
			.setOAuthAccessTokenSecret("zUgz8FogcCuehgsjlS1zMGm3KggY6QmdvLHbrgG3NL6ps");

	public static Twitter getTwitterinstance() {
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		return twitter;
	}

	public static void twitterStreamer() {
		try {
			TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
			twitterStream.addListener(new StatusListener() {
				public void onStatus(Status status) {
					CassandraRepository cassandraRepository = null;
					if(cassandraRepository == null ) {
						cassandraRepository = new CassandraRepository("127.0.0.1", 9042);
					}

					User user = status.getUser();
					String username = status.getUser().getScreenName();
					System.out.println(username);
					String profileLocation = user.getLocation();
					System.out.println(profileLocation);
					long tweetId = status.getId();
					System.out.println(tweetId);
					String content = status.getText();
					System.out.println(content + "\n");
					System.out.println(status.getUser().getName() + " : " + status.getText());
					System.out.println(status.getText());// print tweet text to console
					cassandraRepository.persistTwitter(status.getId(), status.getCreatedAt().toString(), status.getText().toString());
					cassandraRepository.persistTwitterSourceInfo(status.getId(),  status.getSource(), status.getUser().toString());
				}

				@Override
				public void onException(Exception arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onDeletionNotice(StatusDeletionNotice arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onScrubGeo(long arg0, long arg1) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onStallWarning(StallWarning arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onTrackLimitationNotice(int arg0) {
					// TODO Auto-generated method stub

				}
			});

			FilterQuery tweetFilterQuery = new FilterQuery();
			tweetFilterQuery.track(new String[] { "India", "Math" });
			tweetFilterQuery.language(new String[] { "en" });
			twitterStream.filter(tweetFilterQuery);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
