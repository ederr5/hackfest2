import models.*;
import play.*;
import play.db.jpa.JPA;
import models.dao.GenericDAOImpl;
import models.dao.GenericDAO;



public class Global extends GlobalSettings {
	private static GenericDAO dao = new GenericDAOImpl();
	@Override
	public void onStart(Application app){
		Logger.info("aplicacao inicializada!");
		JPA.withTransaction(new play.libs.F.Callback0(){
			@Override
			public void invoke() throws Throwable{
				Evento evento1;
	    		Evento evento2;
	    		Evento evento3;
	    		Evento evento4;
	    		Evento evento5;
	    		Evento evento6;
	    		Evento evento7;
	    		Evento evento8;
	    		Evento evento9;
	    		Evento evento10;
	    		
	    		String tema1;
	    		String tema2;
	    		String tema3;
	    		String tema4;
	    		String tema5;
	    		
	    		
	    		evento1 = new Evento("Dados abertos", "Esse evento tem o objetivo de realizar atividades com dados abertos", "11/09/2014", "Jose", "jose@gmail.com");
	    		evento2 = new Evento("HTML", "Esse evento tem o objetivo de realizar atividades com HTML", "09/09/2014", "Jose", "jose@gmail.com");
	    		evento3 = new Evento("Computacao Desplugada", "Esse evento tem o objetivo de realizar atividades com computacao desplugada", "11/06/2014", "Jose", "jose@gmail.com");
	    		evento4 = new Evento("Metodos Formais", "Esse evento tem o objetivo de realizar atividades com metodos formais", "18/06/2014", "Jose", "jose@gmail.com");
	    		evento5 = new Evento("Banco de Dados Gerenciais", "Esse evento tem o objetivo de realizar atividades com banco de dados gerenciais", "11/09/2014", "Jose", "jose@gmail.com");
	    		evento6 = new Evento("Banco de Dados Sequenciais", "Esse evento tem o objetivo de realizar atividades com banco de dados sequenciais", "14/09/2014", "Jose", "jose@gmail.com");
	    		evento7 = new Evento("Sites Dinamicos", "Esse evento tem o objetivo de realizar atividades com sites dinamicos", "17/08/2014", "Jose", "jose@gmail.com");
	    		evento8 = new Evento("Aplicações Java", "Esse evento tem o objetivo de realizar atividades com aplicacoes java", "01/09/2014", "Jose", "jose@gmail.com");
	    		evento9 = new Evento("Estruturas de dados", "Esse evento tem o objetivo de realizar atividades com estruturas de dados", "12/07/2014", "Jose", "jose@gmail.com");
	    		evento10 = new Evento("Binarios", "Esse evento tem o objetivo de realizar atividades com binarios sem a utilizacao de computador", "11/09/2014", "Jose", "jose@gmail.com");
	    		
	    		tema1 = "Engenharia de Software";
	    		tema2 = "Sistemas da Informacao";
	    		tema3 = "Banco de Dados";
	    		tema4 = "Computacao Desplugada";
	    		tema5 = "Desenvolvimento para Web";
	    		
	    		// 5 eventos no tema 1
	    		evento1.addTema(tema1);
	    		evento4.addTema(tema1);
	    		evento8.addTema(tema1);
	    		evento9.addTema(tema1);
	    		evento10.addTema(tema1);
	    		
	    		// 2 eventos no tema 2
	    		evento4.addTema(tema2);
	    		evento10.addTema(tema2);
	    		
	    		// 4 eventos no tema 3
	    		evento2.addTema(tema3);		
	    		evento4.addTema(tema3);
	    		evento5.addTema(tema3);
	    		evento6.addTema(tema3);
	    		
	    		// 4 eventos no tema 4
	    		evento1.addTema(tema4);
	    		evento3.addTema(tema4);
	    		evento4.addTema(tema4);
	    		evento10.addTema(tema4);
	    		
	    		// 3 eventos no tema 5
	    		evento1.addTema(tema5);
	    		evento2.addTema(tema5);
	    		evento7.addTema(tema5);	
	    		
	    		
	    		
	    		dao.persist(evento1);
	    		dao.persist(evento2);
	    		dao.persist(evento3);
	    		dao.persist(evento4);
	    		dao.persist(evento5);
	    		dao.persist(evento6);
	    		dao.persist(evento7);
	    		dao.persist(evento8);
	    		dao.persist(evento9);
	    		dao.persist(evento10);
	    		dao.flush();
	    		
			}
		});
	}
	
	@Override
	public void onStop(Application app){
		Logger.info("Aplicacao desligada!");
	}

}
