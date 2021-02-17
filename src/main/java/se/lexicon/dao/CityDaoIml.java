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
        String query = "select * from city where id=?";
        City findCity = new City();
        try(
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)
                ) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                findCity.setId(resultSet.getInt(1));
                findCity.setName(resultSet.getString(2));
                findCity.setCountryCode(resultSet.getString(3));
                findCity.setDistrict(resultSet.getString(4));
                findCity.setPopulation(resultSet.getInt(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findCity;
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
        String query = "insert into city values (?,?,?,?,?)";
        City addCity = new City();
        try (
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ) {

            preparedStatement.setInt(1, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(2, city.getName());
            preparedStatement.setString(3, city.getCountryCode());
            preparedStatement.setString(4, city.getDistrict());
            preparedStatement.setInt(5, city.getPopulation());

            int result = preparedStatement.executeUpdate();
            System.out.println((result==1) ? "New City added successfully to database" : "Not ok");

            } catch (SQLException e) {
            e.printStackTrace();
        }
        return addCity;
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
