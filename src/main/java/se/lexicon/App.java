package se.lexicon;

import se.lexicon.dao.CityDaoIml;
import se.lexicon.dao.CityDao;
import se.lexicon.dao.db.MySqlConnection;
import se.lexicon.model.City;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        CityDao dao = new CityDaoIml();
       /* MySqlConnection.getConnection();
       List<City> findAll = dao.findAll();
       findAll.forEach(System.out::println);*/

        //FindById
        //City city = dao.findById(6);
        //System.out.println(city.toString());

        //FindByName
        List<City> findCities = dao.findByName("Kabul");
        findCities.forEach(System.out::println);

       //City aCity = new City("test","tes","test",67);
       //City addCity_ = dao.add(aCity);
    }
}
