package se.lexicon.dao;

import se.lexicon.dao.CityDao;
import se.lexicon.dao.db.MySqlConnection;
import se.lexicon.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityDaoImp implements CityDao {

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
        if (code.length() == 3) {
            String query = "select * from city where countrycode=?";
            List<City> findCities_ = new ArrayList<>();
            try (
                    PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)
            ) {
                preparedStatement.setString(1, code);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    findCities_.add(new City(
                            resultSet.getInt(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getInt(5)
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return findCities_;
        } else {
            System.out.println("The countrycode needs to have 3 letters");
        }
        return null;
    }

    @Override
    public List<City> findByName(String name) {
        String query = "select * from city where name=?";
        List<City> findCities = new ArrayList<>();
        try(
                PreparedStatement preparedStatement = MySqlConnection.getConnection().prepareStatement(query)
        ) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                findCities.add(new City(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getInt(5)
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return findCities;
    }

    @Override
    public City add(City city) {
        String query = "insert into city (name, countrycode, district, population) values (?,?,?,?)";

        try (
                PreparedStatement preparedStatement =
                        MySqlConnection.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                )
        {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());

            int result = preparedStatement.executeUpdate();
            System.out.println((result==1) ? "New City added successfully to database" : "Not ok");

            //get generated key from prepared statement
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            int idKey = 0;
            while(resultSet.next()){
                idKey = resultSet.getInt(1);
            }

            } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public City update(City city) {
        findByName(city.getName());
        String query = "update city set name=?, countrycode=?, district=?, population=? where id=?";

        try (
                PreparedStatement preparedStatement =
                        MySqlConnection.getConnection().prepareStatement(query);
        )
        {
            preparedStatement.setString(1, city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4, city.getPopulation());
            preparedStatement.setInt(5, city.getId());

            int result = preparedStatement.executeUpdate();
            System.out.println((result==1) ? "New City updated successfully to database" : "Not ok");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
