package hello;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by zjluoyue on 2016/8/3.
 */
@RestController
@RequestMapping(value = "/greeting")
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    /**
     * GET请求
     * url可添加参数
     * url + "?name=*"
     * @param name
     * @return
     */
    @GetMapping("")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {

        System.out.println(String.format(template, name));
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    /**
     * GET请求
     * 参数为请求路径
     * @param name
     * @return
     */
    @GetMapping(value = "/{name}")
    public String sayHello(@PathVariable String name) {
        return String.format(template, name);
    }

    /**
     * 4.3之前的GET请求
     * 获取所有值
     * @return
     */
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public List<Country> getCountries() {
        List<Country> listOfCountries = new ArrayList<Country>();
        listOfCountries=createCountryList();
        return listOfCountries;
    }

    /**
     * GET
     * 获取指定 id 的值
     * @param id
     * @return
     */
    @GetMapping(value = "/countries/{id}")
    public Country getCountryById(@PathVariable int id) {
        List<Country> listOfCountries = new ArrayList<Country>();
        listOfCountries=createCountryList();
        for (Country country: listOfCountries) {
            if(country.getId()==id)
                return country;
        }
            return null;
    }

    /**
     * POST
     * 添加值
     * @param country1
     * @return
     */
    @PostMapping(value = "/countries")
    public Country addCounties(@RequestBody Country country1) {
        System.out.println(country1.toString());
        return country1;
    }

    /**
     * POST
     * 同上，另一种实现
     * @param country
     * @return
     */
    @RequestMapping(value = "/addCountry", method = RequestMethod.POST)
    public ResponseEntity<Country> update(@RequestBody Country country) {

        System.out.println(country.toString());
        return new ResponseEntity<Country>(country, HttpStatus.OK);
    }

    // Utiliy method to create country list.
    public List<Country> createCountryList() {
        Country indiaCountry = new Country(1, "India");
        Country chinaCountry = new Country(4, "China");
        Country nepalCountry = new Country(3, "Nepal");
        Country bhutanCountry = new Country(2, "Bhutan");
        List<Country> listOfCountries = new ArrayList<Country>();
        listOfCountries.add(indiaCountry);
        listOfCountries.add(chinaCountry);
        listOfCountries.add(nepalCountry);
        listOfCountries.add(bhutanCountry);
        return listOfCountries;
    }

}

