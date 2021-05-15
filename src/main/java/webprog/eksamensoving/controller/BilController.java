package webprog.eksamensoving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import webprog.eksamensoving.pojos.Bil;
import webprog.eksamensoving.repository.BilRepo;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class BilController {
    @Autowired
    BilRepo bilRepo;

    @GetMapping("/bil")
    public List<Bil> hent (HttpServletResponse response) throws IOException {
        List<Bil> listBiler = bilRepo.hentBiler();
        if (listBiler == null){
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(),"Feil i databasen - prøv igjen senere.");
        }
        return listBiler;
    }

}
