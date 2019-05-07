package pe.com.ormdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.orm.SugarRecord.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var nameEditText: EditText
    lateinit var emailEditText: EditText
    lateinit var genderSwitch: Switch
    lateinit var malesProgressBar: ProgressBar
    lateinit var femalesProgressBar: ProgressBar
    lateinit var malesMaxTextView: TextView
    lateinit var femalesMaxTextView: TextView
    lateinit var male: Gender
    lateinit var female: Gender

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        genderSwitch = findViewById(R.id.genderSwitch)
        malesProgressBar = findViewById(R.id.malesProgressBar)
        femalesProgressBar = findViewById(R.id.femalesProgressBar)
        malesMaxTextView = findViewById(R.id.malesMaxTextView)
        femalesMaxTextView = findViewById(R.id.femalesMaxTextView)
        initDb()
        fab.setOnClickListener { view ->
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val gender = if (genderSwitch.isChecked) female else male
            var resultMessage = "Person added"
            if (name.isEmpty()) {
                resultMessage = "Name is empty"
            } else {
                val people = find(Person::class.java, "name = ?", name)
                if (people.isEmpty()) {
                    var person = Person(name, email, gender)
                    person.save()
                    val id = person.id
                    person = findById(
                        Person::class.java, id
                    )
                    Log.d(
                        "ORMDemo",
                        "Person id: " + person.id.toString() +
                                ", email: " + person.email +
                                ", gender: " + person.gender.name
                    )
                    updateProgress()
                } else {
                    resultMessage = "Person name already exists"
                }
            }

            Snackbar.make(
                view,
                resultMessage,
                Snackbar.LENGTH_LONG
            )
                .setAction("New Person") {
                    nameEditText.setText("")
                    emailEditText.setText("")
                    genderSwitch.isChecked = false
                }.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)

    }

    private fun initDb() {
        val genders = listAll(Gender::class.java)
        val people = listAll(Person::class.java)
        if (genders.isEmpty()) {
            male = Gender("Male")
            female = Gender("Female")
            male.save()
            female.save()

        } else {
            male = find(Gender::class.java, "name = ?", "Male").get(0)
            female = find(Gender::class.java, "name = ?", "Female").get(0)
        }
        if (!people.isEmpty()) {
            Log.i("PEOPLE", people.toString())
        }
        updateProgress()
    }

    fun clearAll(view: View) {
        deleteAll(Person::class.java)
        deleteAll(Gender::class.java)
        initDb()
    }

    fun updateProgress() {
        val malesCount = male.getPeople()?.size
        val femalesCount = female.getPeople()?.size
        val peopleCount = listAll(Person::class.java)?.size
        val malesProgress = if (peopleCount == 0) 0 else Math.round((malesCount!! * 100 / peopleCount!!).toFloat())
        val femalesProgress = if (peopleCount == 0) 0 else 100 - malesProgress
        malesProgressBar.setProgress(malesProgress)
        malesMaxTextView.text = "$malesProgress %"
        femalesProgressBar.setProgress(femalesProgress)
        femalesMaxTextView.text = "$femalesProgress %"
    }
}
