package controllers;

import static play.data.Form.form;

import java.util.List;

import models.Pessoa;
import models.dao.GenericDAO;
import models.dao.GenericDAOImpl;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import views.html.login;

public class Login extends Controller {
	
	private static GenericDAO dao = new GenericDAOImpl();
	static Form<Pessoa> loginForm = form(Pessoa.class).bindFromRequest();

	
	@Transactional
    public static Result show() {
		if (session().get("user") != null) {
			return redirect(routes.Application.index());
		}
        return ok(login.render(loginForm));
    }
	
	
	@Transactional
	public static Result logout() {
		session().clear();
		return show();
	}
	
	
	@Transactional
	public static Result authenticate() {
		
		DynamicForm requestData = Form.form().bindFromRequest();
		
		String email = requestData.get("email");
		String senha = requestData.get("pass");
		
		Pessoa u = new Pessoa(null, email, senha);
		
		/**Pessoa u =  loginForm.bindFromRequest().get();
		
		String email = u.getEmail();
		String senha = u.getPassword();**/

        if (//loginForm.hasErrors() ||
        		!validate(email, senha)) {
        	flash("fail", "Email ou Senha Inválidos");
        	return badRequest(login.render(loginForm));
        } else {
        	Pessoa user = (Pessoa) dao.findByAttributeName(
        			"Pessoa", "email", u.getEmail()).get(0);
            session().clear();
            session("user", user.getNome());
            return redirect(
                routes.Application.index()
            );
        }
    }
	
	
	private static boolean validate(String email, String senha) {
		List<Pessoa> u = dao.findByAttributeName("Pessoa", "email", email);
		if (u == null || u.isEmpty() ) {
			return false;
		}
		if (! senha.equals(u.get(0).getPassword())) {
			return false;
		}
		return true;
	}

	
}
