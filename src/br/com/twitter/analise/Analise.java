package br.com.twitter.analise;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class Analise {
	
	DateFormat maskData = new SimpleDateFormat("yyyy-MM-dd"); 
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //define a formata��o das datas para a busca
	
	String Hashtag = "#jvm";
	
	public void tweet(List<Status> status) {
		
		int qtMensagens = 0;
		int qtRetweet = 0;
		int qtFavoritos = 0;
		
		String primeiraData;
		String ultimaData = null;
		String primeiroNome = null;
		String ultimoNome = null;
		int Aux = 0;
		
		String dataTexto = maskData.format(status.get(0).getCreatedAt()); //data inicial. data do primeiro elemento da lista
		primeiraData = dataTexto;


		//percorre a lista que foi criada a partir da classe getTweets
		//a lista � criada com a data "da menor pra maior"
		for (Status x : status) {
			Aux = Aux + 1;
			
			if (primeiroNome == null) {
				primeiroNome = status.get(0).getUser().getScreenName();
			}
			
			//msg para debug...
			System.out.println("dataTexto " + dataTexto + " maskData.format(x.getCreatedAt() " + maskData.format(x.getCreatedAt()));
			
			//totaliza enquanto as datas forem diferentes. (data da mensagem e data do range da �ltima semana)
			//os contatodes s�o zerados quando as datas mudarem
			if (!dataTexto.equals(maskData.format(x.getCreatedAt()))) {
				
				System.out.println("Dia: " + dataTexto);
				System.out.println("Tweets: " + qtMensagens);
				System.out.println("Retweets: " + qtRetweet);
				System.out.println("Favorita��es: " + qtFavoritos);
				
				System.out.println("*************************************");
				
				dataTexto = maskData.format(x.getCreatedAt()); //incrementa a data para pr�xima compara��o e get�s
				
				qtRetweet = 0;
				qtFavoritos = 0;
				qtMensagens = 0;
				
				ultimaData = dataTexto;
				ultimoNome = status.get(Aux).getUser().getScreenName();
				
			}
			
			qtRetweet = qtRetweet + x.getRetweetCount();
			qtFavoritos = qtFavoritos + x.getFavoriteCount();
			qtMensagens = qtMensagens + 1;
		}
		System.out.println("Dia: " + dataTexto);
		System.out.println("Tweets: " + qtMensagens);
		System.out.println("Retweets: " + qtRetweet);
		System.out.println("Favoritos: " + qtFavoritos);		
		
		System.out.println("===========================");	
		System.out.println("�ltima msg em: " + ultimaData); 
		System.out.println("�ltimo nome: "  + ultimoNome);
		System.out.println("Primeira msg em : "  + primeiraData);  
		System.out.println("Primeiro nome: " + primeiroNome);

	
	}
	
	public List<Status> getTweets(Twitter twitter) throws TwitterException {
			
		List<Status> msgTwt = new ArrayList<Status>();
		
		Query query = new Query(Hashtag);
		
		LocalDate baseDate = LocalDate.now().minusWeeks(1); //data referente a semana anterior, partindo da data atual
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); //define a formata��o das datas para a busca
		

//		Desde uma data espec�fica: quando � necess�rio buscar por algo a partir de uma
//		determinada data. Para este filtro, chamamos o m�todo Query.setSince(String data)
//		passando a data no formato �ano/m�s/dia�, por exemplo: 2014/01/14;
//		At� uma data espec�fica: quando � necess�rio buscar at� uma data determinada. Para este
//		filtro, chamamos o m�todo Query.setUntil(String data) passando a data no formato
//		�ano/m�s/dia�;
		
		query.setSince(formatter.format(baseDate));  
	    query.setUntil(formatter.format(baseDate.plusDays(1))); 
	    
	    //twitter.search: Retorna uma cole��o de Tweets relevantes que correspondem a consulta pela palavra chave.
		QueryResult result = twitter.search(query);
		
		for (int i = 1; i < 7; i++) {
			
			while (result.hasNext())
			{   
				//status = list que contem os tweets encontrados no per�odo correspondente
				query = result.nextQuery();
				msgTwt.addAll(result.getTweets());
				result = twitter.search(query);
			}
			
			//troca os par�metros para pr�xima busca
			baseDate = baseDate.plusDays(1);
			query = new Query(Hashtag);
			query.setSince(formatter.format(baseDate));
			query.setUntil(formatter.format(baseDate.plusDays(1)));
			
			result = twitter.search(query);

		}
		return msgTwt;
	}
	
}
