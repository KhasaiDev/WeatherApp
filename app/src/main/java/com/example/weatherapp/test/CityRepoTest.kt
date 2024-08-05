import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.database.CityDataBaseHelper
import com.example.weatherapp.model.City
import com.example.weatherapp.repo.CityRepo
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CityRepoTest {
    private lateinit var dbHelper: CityDataBaseHelper
    private lateinit var database: SQLiteDatabase

    @Before
    fun setUp() {
        dbHelper = CityDataBaseHelper(ApplicationProvider.getApplicationContext())
        database = dbHelper.writableDatabase
        dbHelper.onCreate(database)  // Crear tablas y datos iniciales
    }

    @After
    fun tearDown() {
        database.close()
        dbHelper.close()
    }

    @Test
    fun testInsertCity() {
        val city = City(city_name = "TestCity", temp = 25.0, temp_min = 20.0, temp_max = 30.0, pressure = 1012, humidity = 60, wind_speed = 5.0, sunrise = 123456789, sunset = 987654321)
        val cityRepo = CityRepo(ApplicationProvider.getApplicationContext())
        val id = cityRepo.insert(city)
        assertTrue(id > 0)
    }

    @Test
    fun testReadCities() {
        val cityRepo = CityRepo(ApplicationProvider.getApplicationContext())
        val cities = cityRepo.readCities()
        assertNotNull(cities)
        assertTrue(cities.isNotEmpty())
    }

    @Test
    fun testDeleteCity() {
        val cityRepo = CityRepo(ApplicationProvider.getApplicationContext())
        val city = City(city_name = "TestCity", temp = 25.0, temp_min = 20.0, temp_max = 30.0, pressure = 1012, humidity = 60, wind_speed = 5.0, sunrise = 123456789, sunset = 987654321)
        val id = cityRepo.insert(city)
        val rowsDeleted = database.delete(CityDataBaseHelper.TABLE_USER, "${CityDataBaseHelper.COLUMN_ID}=?", arrayOf(id.toString()))
        assertEquals(1, rowsDeleted)
    }
}