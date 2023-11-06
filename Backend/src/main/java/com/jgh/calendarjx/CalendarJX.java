package com.jgh.calendarjx;

import com.jgh.calendarjx.calendar.CalendarFactory;
import com.jgh.calendarjx.model.NewTableMapper;
import com.jgh.calendarjx.model.ScheduleItem;
import com.jgh.calendarjx.model.ScheduleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CalendarJX {


    public static final String RESOURCE = "mybatis-3-config.xml";
    public static final String DB_PATH= "/tmp/calendar.db";

    public static void main(String[] args) {

        createDatabaseIfNotExists();
        SpringApplication.run(CalendarJX.class, args);
    }


    /**
     * Creates a SQLite database if no database is found.
     */
    private static void createDatabaseIfNotExists(){
       ;

        File f = new File(DB_PATH);
        if(!f.exists()){
            System.out.println("database file is being created...");
            try {
                f.createNewFile();


            } catch (IOException e) {

                System.out.println("Error creating database.");
                e.printStackTrace();
            }
        }

        String resource = "mybatis-3-config.xml";
        try (Reader in = Resources.getResourceAsReader(resource)) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();

            System.out.println("creating table");
            NewTableMapper i = session.getMapper(NewTableMapper.class);
            i.createNewTable();
            session.commit();
            session.close();
        } catch (IOException e) {

            System.out.println("error creating table");
            e.printStackTrace();
        }
    }
}
