package cn.wolfcode._03_datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.sql.DataSource;
import java.sql.SQLException;

@Controller
public class DSController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/ds")
    @ResponseBody
    public Object ds(){
        try {
            System.out.println(dataSource.getConnection());
            System.out.println(dataSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "ok";
    }
}
