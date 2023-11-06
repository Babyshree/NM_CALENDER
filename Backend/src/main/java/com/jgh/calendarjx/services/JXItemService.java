package com.jgh.calendarjx.services;


import com.jgh.calendarjx.CalendarJX;
import com.jgh.calendarjx.model.Note;
import com.jgh.calendarjx.model.ScheduleItem;
import com.jgh.calendarjx.model.ScheduleMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;

@Service
public class JXItemService implements ItemService {


    @Override
    public void updateItem(int id, String itemText) throws IOException {
        try (Reader in = Resources.getResourceAsReader(CalendarJX.RESOURCE)) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            ScheduleMapper i = session.getMapper(ScheduleMapper.class);
            ScheduleItem item = new ScheduleItem();
            item.setId(id);
            item.setAppointment(itemText);
            i.updateItem(item);
            session.commit();
            session.close();
        } catch (IOException e) {
            System.out.println("error "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateNote(int id, String notes) throws IOException {
        try (Reader in = Resources.getResourceAsReader(CalendarJX.RESOURCE)) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            ScheduleMapper i = session.getMapper(ScheduleMapper.class);
            Note note = new Note();
            note.id = id;
            note.note = notes;
            i.updateNotes(note);
            session.commit();
            session.close();
        } catch (IOException e) {
            System.out.println("error "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateTime(int id, String time) throws IOException {
        try (Reader in = Resources.getResourceAsReader(CalendarJX.RESOURCE)) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            ScheduleMapper i = session.getMapper(ScheduleMapper.class);
            ScheduleItem item = new ScheduleItem();
            item.setId(id);
            item.setTime(time);
            i.updateTime(item);
            session.commit();
            session.close();
        } catch (IOException e) {
            System.out.println("error "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void updateDate(int id, String date) throws Exception {
        try (Reader in = Resources.getResourceAsReader(CalendarJX.RESOURCE)) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            ScheduleMapper i = session.getMapper(ScheduleMapper.class);
            ScheduleItem item = new ScheduleItem();
            item.setId(id);
            item.setAppointment_date(date);
            i.updateDate(item);
            session.commit();
            session.close();
        } catch (IOException e) {
            System.out.println("error "+e.getMessage());
            throw e;
        }
    }

    @Override
    public void deleteItem(int id) throws IOException {
        try (Reader in = Resources.getResourceAsReader(CalendarJX.RESOURCE)) {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            ScheduleMapper i = session.getMapper(ScheduleMapper.class);
            i.delete(id);
            session.commit();
            session.close();
        } catch (IOException e) {
            System.out.println("error "+e.getMessage());
            throw e;
        }
    }
}
