package webprog.eksamensoving.eksamen2021;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EksamensController2021 {
    @Autowired
    private JdbcTemplate db;



}
