package br.com.twitter.main;

import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class GetMentions {
    /**
     * Usage: java twitter4j.examples.timeline.GetMentions
     *
     * @param args String[]
     */
    public static void main(String[] args) {
        
    	// Mostra as mensagens que foram direcionadas pro meu usuário
        Twitter twitter = new TwitterFactory().getInstance();
        try {
            User user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getMentionsTimeline();
            System.out.println("Monstrando os mentions para meu usuário @" + user.getScreenName());
            System.out.println("");
            for (Status status : statuses) {
                System.out.println("Fui mensionado pelo usuário @" + status.getUser().getScreenName() + " - mensagem enviada: " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
    }
}