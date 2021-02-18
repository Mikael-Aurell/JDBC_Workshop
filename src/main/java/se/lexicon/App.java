package se.lexicon;

import se.lexicon.dao.CityDaoImp;
import se.lexicon.dao.CityDao;
import se.lexicon.model.City;

import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        CityDao dao = new CityDaoImp();

       //FindAll
       //List<City> findAll = dao.findAll();
       //findAll.forEach(System.out::println);

        //FindById
        //City city = dao.findById(6);
        //System.out.println(city.toString());

        //FindByName
        //List<City> findCities = dao.findByName("Stockholm2");




        //FindByCode
        //List<City> findCities = dao.findByCode("ARG");
        //findCities.forEach(System.out::println);

        //Add
        //City addCity = new City("Stockholm2","SWE","Dis",68);
        //City addCity_ = dao.add(aCity);
        //System.out.println(addCity_);

        City aCity = new City(4085,"Stockholm2","SWE","Dis",680);
        City updateCity_ = dao.update(aCity);
        System.out.println(updateCity_);
        List<City> findCities = dao.findByName("Stockholm2");
        findCities.forEach(System.out::println);
    }
}
