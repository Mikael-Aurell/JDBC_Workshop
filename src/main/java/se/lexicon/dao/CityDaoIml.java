package se.lexicon.dao;

import se.lexicon.dao.CityDao;
import se.lexicon.dao.db.MySqlConnection;
import se.lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoIml implements CityDao {

    @Override
    public List<City> findAll(){
        String selectAllCities = "select id, name, countrycode, district,population from city";
        List<City> findAllList = new ArrayList<>();
        try {
            Statement statement = MySqlConnection.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(selectAllCities);
            while(resultSet.next()){
                findAllList.add(new City(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("countrycode"),
                        resultSet.getString("district"),
                        resultSet.getInt("population")
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return findAllList;
    }

    @Override
    public City findById(int id) {
        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        return null;
    }

    @Override
    public List<City> findByName(String name) {
        return null;
    }


    @Override
    public City add(City city) {
        return null;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
