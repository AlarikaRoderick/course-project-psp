package com.company.dao.session;

import com.company.dbHandler.DbHandler;
import com.company.entities.SessionEntity;
import com.company.entities.UserEntity;
import org.hibernate.jpa.criteria.expression.ConcatExpression;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SessionDAOImpl implements SessionDAO {
    //private Connection connection = DbHandler.getInstance().getConnection();

    @Override
    public SessionEntity findSessionById(int id) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        String findSession = "SELECT * FROM session WHERE id_session=" + id;
        ResultSet resultSet = null;
        SessionEntity session = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(findSession);
            resultSet = preparedStatement.executeQuery(findSession);
            while (resultSet.next()){
                session = getSessionFromDB(resultSet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return session;
    }

    public List<SessionEntity> findSessionsByFilmName(String filmName){
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        List<SessionEntity> sessions = new ArrayList<>();
        ResultSet resultSet = null;
        String findSessions = "select id_session, session_date, session_time_hour, session_time_minute\n" +
                "from session, film WHERE session.id_film_session=film.id_film and film.film_name='"+ filmName + "'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findSessions);
            resultSet = preparedStatement.executeQuery(findSessions);
            while (resultSet.next()){
                int id = resultSet.getInt("id_session");
                Date sessionDate = resultSet.getDate("session_date");
                int sessionHour = resultSet.getInt("session_time_hour");
                int sessionMinute = resultSet.getInt("session_time_minute");
                SessionEntity session = new SessionEntity(sessionDate, 0, 0, sessionHour, sessionMinute);
                session.setId_session(id);
                sessions.add(session);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sessions;
    }

    private SessionEntity getSessionFromDB(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        Date sessionDate = resultSet.getDate(2);
        int idFilmSession = resultSet.getInt(3);
        int idHallSession = resultSet.getInt(4);
        int sessionTimeHour = resultSet.getInt(5);
        int sessionTimeMinute = resultSet.getInt(6);
        SessionEntity session = new SessionEntity(sessionDate, idFilmSession, idHallSession, sessionTimeHour, sessionTimeMinute);
        session.setId_session(id);
        return session;
    }

    @Override
    public void saveSession(SessionEntity session) {
        String saveSession = "INSERT INTO session (session_date, id_film_session, id_hall_session, session_time_hour,"
                + "session_time_minute) VALUES(?,?,?,?,?)";
        try{
            setSessionInfo(session, saveSession);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void setSessionInfo(SessionEntity session, String saveSession) throws SQLException{
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(saveSession);
        preparedStatement.setDate(1, session.getSessionDate());
        preparedStatement.setInt(2, session.getIdFilmSession());
        preparedStatement.setInt(3, session.getIdHallSession());
        preparedStatement.setInt(4, session.getSessionTimeHour());
        preparedStatement.setInt(5, session.getSessionTimeMinute());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateSession(SessionEntity session) {
        int id = session.getId_session();
        String updateSession = "UPDATE session SET session_date=?, id_film_session=?, id_hall_session=?,"
                + "session_time_hour=?, session_time_minute=? WHERE id_session=" + id;
        try{
            setSessionInfo(session, updateSession);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSession(SessionEntity session) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        int id = session.getId_session();
        String deleteSession = "DELETE FROM session WHERE id_session=" + id;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSession);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    @Override
    public List<SessionEntity> findAll() {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        ResultSet resultSet = null;
        List<SessionEntity> sessions = new ArrayList<>();
        String selectAllSessions = "SELECT * FROM session";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllSessions);
            resultSet = preparedStatement.executeQuery(selectAllSessions);

            while (resultSet.next()){
                SessionEntity session = getSessionFromDB(resultSet);
                sessions.add(session);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return sessions;
    }
}
