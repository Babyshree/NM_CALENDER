package com.jgh.calendarjx.services;

import com.jgh.calendarjx.CalendarJX;
import com.jgh.calendarjx.calendar.CalendarFactory;
import com.jgh.calendarjx.model.ScheduleItem;
import com.jgh.calendarjx.model.ScheduleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JXScheduleService implements ScheduleService {

    @Override
    public void deleteAllAppointments() throws Exception {
        CalendarFactory.generateCalendar(LocalDate.now());
        ArrayList<ScheduleItem> res = new ArrayList<>();
        try (Reader in = Resources.getResourceAsReader(CalendarJX.RESOURCE)) {

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            ScheduleMapper sm = session.getMapper(ScheduleMapper.class);
            sm.deleteAllAppointments();
            session.close();
        } catch (IOException e) {
            System.out.println("Failed to get schedule items.");
            throw e;
        }
        catch (Exception ex){
            throw ex;
        }
    }

    @Override
    public ArrayList<ScheduleItem> getAllAppointments() {
        CalendarFactory.generateCalendar(LocalDate.now());
        ArrayList<ScheduleItem> res = new ArrayList<>();
        try (Reader in = Resources.getResourceAsReader(CalendarJX.RESOURCE)) {

            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            ScheduleMapper sm = session.getMapper(ScheduleMapper.class);
            List<ScheduleItem> result = sm.getAllAppointments();
            for (ScheduleItem m : result) {
                res.add(m);
            }
            session.close();
            return res;
        } catch (IOException e) {
            System.out.println("Failed to get schedule items.");
            return res;
        }
    }



    @Override
    public String insertNewScheduleItem(ScheduleItem item) {
        try (Reader in = Resources.getResourceAsReader(CalendarJX.RESOURCE)) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            ScheduleMapper i = session.getMapper(ScheduleMapper.class);
            i.insertSchedule(item);
            session.commit();
            session.close();
            return "success";
        } catch (IOException e) {
            return "fail";
        }
    }

    @Override
    public ScheduleItem getItem(int id) {
        try (Reader in = Resources.getResourceAsReader(CalendarJX.RESOURCE)) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            ScheduleMapper sm = session.getMapper(ScheduleMapper.class);
            ScheduleItem result = sm.getAppointment(id);
            session.close();
            return result;
        } catch (IOException e) {
            System.out.println("error "+e.getMessage());
            return null;
        }
    }
}
