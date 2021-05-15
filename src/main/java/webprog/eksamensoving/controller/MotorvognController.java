package webprog.eksamensoving.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import webprog.eksamensoving.hjelpeklasser.Inputvalidering;
import webprog.eksamensoving.repository.MotorvognRepo;
import webprog.eksamensoving.pojos.Motorvogn;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
public class MotorvognController {
    @Autowired
    private MotorvognRepo motorvognRepo;

    @Autowired
    HttpSession session;

    private final Logger logger = LoggerFactory.getLogger(MotorvognController.class);

    @PostMapping("/motorvogn")
    public void lagre(Motorvogn motorvogn, HttpServletResponse response) throws IOException {
        if (Inputvalidering.validerMotorvogn(motorvogn,logger)){
            if(!motorvognRepo.lagreMotorvogn(motorvogn)){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i databasen - prøv igjen senere.");
            }
        }
        else{
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Valideringsfeil - prøv igjen.");
        }
    }

    @GetMapping("/motorvogn")
    public List<Motorvogn> hentAlle(HttpServletResponse response) throws IOException {
        if((boolean)session.getAttribute("loggetInn")){
            List<Motorvogn> alleMotorvogner = motorvognRepo.hentAlleMotorvogner();
            if (alleMotorvogner == null){
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i databasen - prøv igjen senere.");
            }
            return alleMotorvogner;
        }
        else{
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Må være logget inn for å vise registeret.");
            return null;
        }
    }

    @DeleteMapping("/motorvogn")
    public void slett(HttpServletResponse response) throws IOException{
        if(!motorvognRepo.slettAlleMotorvogner()){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i databasen - prøv igjen senere.");
        }
    }

    @GetMapping("/loggInn")
    public boolean loggInn(String brukernavn, String passord){
        if(motorvognRepo.loggInn(brukernavn,passord)){
            session.setAttribute("loggetInn",true);
            return true;
        }
        else{
            return false;
        }
    }

    @GetMapping("/loggUt")
    public void loggUt(){
        session.setAttribute("loggetInn",false);
    }

    @GetMapping("/krypterAllePassord")
    public boolean krypterAllePassord() {
        return motorvognRepo.krypterAllePassord();
    }
}
