package controllers;

import java.util.List;

import models.*;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
//import play.*;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {
	static Form<Evento> eventoForm = Form.form(Evento.class);
	static Form<Pessoa> pessoaForm = Form.form(Pessoa.class);
	private static GenericDAO dao = new GenericDAOImpl();
	

	@Transactional
    public static Result index() {
    	if (session().get("user") == null) {
			return redirect(routes.Login.show());
		}
    		
    	
    	Sistema sistema = new Sistema();
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
        return ok(index.render("Your new application is ready.",sistema));
    }
    
    @Transactional
    public static Result sistema() {
    	
    	Sistema sistema = new Sistema();
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
    	return ok(views.html.sistema.render(sistema));
    	
    }
    
    @Transactional
    public static Result cadastro() {
    	if (session().get("user") == null) {
			return redirect(routes.Login.show());
		}
    	Sistema sistema = new Sistema();
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
    	return ok(views.html.cadastro.render(sistema));
    	
    }
    
    @Transactional
    public static Result newEvento() {
    	// Todos o eventos do Banco de Dados
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	// O formulario de evento
		Form<Evento> filledForm = eventoForm.bindFromRequest();
		
		
		Evento evento = filledForm.get();
		
		Sistema sistema = new Sistema();
    	sistema.setEventos(result);;
    	if (filledForm.hasErrors()) {
			return badRequest(views.html.cadastro.render(sistema));
		} else {
			// Persiste a meta criada
			getDao().persist(evento);
			// Espelha no Banco de Dados
			getDao().flush();
			return redirect(routes.Application.index());
		}
    	
    	
    }
    
    @Transactional
    public static Result participar(Long id) {
    	Evento evento = getDao().findByEntityId(Evento.class, id);
    	Sistema sistema = new Sistema();
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
    	return ok(views.html.evento.render(sistema, evento));
    }
    
    @Transactional
    public static Result addParticipante(Long id) {
    	// Todos o eventos do Banco de Dados
    	List<Evento> result = getDao().findAllByClassName("Evento");
    	// Lista de Pessoa
//    	List<Pessoa> pessoas = getDao().findAllByClassName("Pessoa");
    	// O formulario de pessoa
		Form<Pessoa> filledForm = pessoaForm.bindFromRequest();
		Pessoa pessoa = filledForm.get();
		Evento evento = getDao().findByEntityId(Evento.class, id);
		evento.addParticipanteNoEvento(pessoa);
		getDao().removeById(Evento.class, id);
		getDao().flush();
		getDao().persist(evento);
		Sistema sistema = new Sistema();
    	result = getDao().findAllByClassName("Evento");
    	sistema.setEventos(result);
		return ok(views.html.evento.render(sistema, evento));
		}
    	
    	
    	
    	
    

	public static GenericDAO getDao() {
		return dao;
	}

	public static void setDao(GenericDAO dao) {
		Application.dao = dao;
	}
    
    

}
