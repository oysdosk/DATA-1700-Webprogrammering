package webprog.eksamensoving.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import webprog.eksamensoving.pojos.Bil;

import java.util.Collections;
import java.util.List;

@Repository
public class BilRepo {
    @Autowired
    private JdbcTemplate db;

    public List<Bil> hentBiler(){
        String sql = "SELECT * FROM Bilregister";
        try{
            List<Bil> listBiler = db.query(sql,new BeanPropertyRowMapper<>(Bil.class));
            Collections.sort(listBiler);
            return listBiler;
        }
        catch (Exception e){
            return null;
        }

        /*listBiler.add(new Bil("Ford","Mondeo"));
        listBiler.add(new Bil("Ford","Focus"));
        listBiler.add(new Bil("Honda","X40"));
        listBiler.add(new Bil("Jaguar","R-Type"));
        listBiler.add(new Bil("Jaguar","S-Type"));
        listBiler.add(new Bil("Honda","1"));
        listBiler.add(new Bil("Honda","2"));
        listBiler.add(new Bil("Ford","Fiesta"));*/
    }
}
