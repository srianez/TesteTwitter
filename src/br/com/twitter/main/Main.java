package br.com.twitter.main;

import java.util.List;

import br.com.twitter.analise.Analise;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Main {

	public static void main(String[] args) {
		try {		
			
			ConfigurationBuilder builder = new ConfigurationBuilder();
			Configuration configuration = builder.build();
			TwitterFactory factory = new TwitterFactory(configuration);
			Twitter twitter = factory.getInstance();
			
			Analise analise = new Analise();
			List<Status> x = analise.getTweets(twitter);
			
			analise.tweet(x);
			
			twitter.updateStatus("Há! Yeah Yeah!");
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}


