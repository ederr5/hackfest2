package controllers;

import static play.data.Form.form;

import java.util.List;

import models.Pessoa;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.registro;

public class Registro extends Controller {
	
	private static GenericDAO dao = new GenericDAOImpl();
	static Form<Pessoa> registroForm = form(Pessoa.class).bindFromRequest();
	
	@Transactional
    public static Result show() {
        return ok(registro.render(registroForm));
    }
	
	@Transactional
	public static Result registrar() {
		DynamicForm requestData = Form.form().bindFromRequest();
		
		String nome = requestData.get("nome");
		String email = requestData.get("email");
		String senha = requestData.get("pass");
    	
		Pessoa u = new Pessoa(nome, email, senha);
		//dao.persist(p);
		//Pessoa u = registroForm.bindFromRequest().get();
    	
		if (//registroForm.hasErrors() || 
				validate(u.getEmail())) {
			flash("fail", "Email já está em uso");
            return badRequest(registro.render(registroForm));
        } else {
        	dao.persist(u);
            return redirect(
                routes.Login.show()
            );
        }
    }
	
	private static boolean validate(String email) {
		List<Pessoa> list = dao.findByAttributeName("Pessoa","email",email);
		if (list.isEmpty()) {
			return false;
		}
		return true;
	}


}
