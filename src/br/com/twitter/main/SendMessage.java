package br.com.twitter.main;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class SendMessage {

	public static void main(String[] args) throws TwitterException {

		 Twitter twitter = TwitterFactory.getSingleton();	

		 Status status = twitter.updateStatus("creating twitter API");
		 
		 System.out.println("Mensage enviada: "+ status.getText());

	}

}
