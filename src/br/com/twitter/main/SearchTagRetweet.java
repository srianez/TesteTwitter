package br.com.twitter.main;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class SearchTagRetweet {

	public static void main(String[] args) throws TwitterException {

		Twitter twitter = TwitterFactory.getSingleton();

		// busca por pipipitchu, retorna o usuário que postou e a msg postada.
		Query query = new Query("#Chatiado");
		QueryResult result = twitter.search(query);
		for (Status c : result.getTweets()) {

			System.out.println("Quem postou = " + c.getUser().getScreenName() + "    Mensagem = " + c.getText()
					+ "    ID " + c.getId());
            
			//retuita a msg encontrada
			//twitter.retweetStatus(c.getId());
			
			//System.out.println("REtuitou " + c.getId());
			
		}
		

	}

}
