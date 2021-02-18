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
        List<City> findCities = dao.findByName("Stockholm");
        findCities.forEach(System.out::println);

        //FindByCode
        //List<City> findCities = dao.findByCode("ARG");
        //findCities.forEach(System.out::println);

       //City aCity = new City("Stockholm","SWE","Dis",67);
       //City addCity_ = dao.add(aCity);
    }
}
