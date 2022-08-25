package com.intellisoft.kabarakmhis.new_designs.matenal_serology

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.fhir.FhirEngine
import com.intellisoft.kabarakmhis.R
import com.intellisoft.kabarakmhis.fhir.FhirApplication
import com.intellisoft.kabarakmhis.fhir.viewmodels.PatientDetailsViewModel
import com.intellisoft.kabarakmhis.helperclass.DbSummaryTitle
import com.intellisoft.kabarakmhis.helperclass.FormatterClass
import com.intellisoft.kabarakmhis.network_request.requests.RetrofitCallsFhir
import com.intellisoft.kabarakmhis.new_designs.data_class.DbObservationFhirData
import com.intellisoft.kabarakmhis.new_designs.data_class.DbResourceViews
import com.intellisoft.kabarakmhis.new_designs.screens.ConfirmParentAdapter
import com.intellisoft.kabarakmhis.new_designs.screens.PatientProfile
import kotlinx.android.synthetic.main.activity_maternal_serology_view.*
import kotlinx.android.synthetic.main.activity_maternal_serology_view.no_record
import kotlinx.android.synthetic.main.activity_maternal_serology_view.recycler_view
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Stream

class MaternalSerologyView : AppCompatActivity() {

    private val formatter = FormatterClass()
    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private val retrofitCallsFhir = RetrofitCallsFhir()

    private lateinit var patientDetailsViewModel: PatientDetailsViewModel
    private lateinit var patientId: String
    private lateinit var fhirEngine: FhirEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maternal_serology_view)

        title = "Maternal Serology Details"

        patientId = formatter.retrieveSharedPreference(this, "patientId").toString()
        fhirEngine = FhirApplication.fhirEngine(this)

        patientDetailsViewModel = ViewModelProvider(this,
            PatientDetailsViewModel.PatientDetailsViewModelFactory(application,fhirEngine, patientId)
        )[PatientDetailsViewModel::class.java]
        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)

        btnAdd.setOnClickListener {

            startActivity(Intent(this, MaternalSerology::class.java))

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profile -> {

                startActivity(Intent(this, PatientProfile::class.java))
                finish()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onStart() {
        super.onStart()

        CoroutineScope(Dispatchers.IO).launch {
            getObservationDetails()
        }
        getUserData()

    }

    private fun getUserData() {

        val identifier = formatter.retrieveSharedPreference(this, "identifier")
        val patientName = formatter.retrieveSharedPreference(this, "patientName")

        tvPatient.text = patientName
        tvAncId.text = identifier

    }

    private fun getObservationDetails() {

        val encounterId = formatter.retrieveSharedPreference(this@MaternalSerologyView,
            DbResourceViews.MATERNAL_SEROLOGY.name)

        if (encounterId != null) {

            val text1 = DbObservationFhirData(
                DbSummaryTitle.A_MATERNAL_SEROLOGY.name,
                listOf("412690006","412690006-Y","412690006-N","412690006-R"))
            val text2 = DbObservationFhirData(
                DbSummaryTitle.B_REACTIVE.name,
                listOf("412690006-RRPMCT","412690006-PR"))
            val text3 = DbObservationFhirData(
                DbSummaryTitle.C_NON_REACTIVE.name,
                listOf("412690006-B", "412690006-CT", "412690006-A"))

            val text1List = formatter.getObservationList(patientDetailsViewModel, text1, encounterId)
            val text2List = formatter.getObservationList(patientDetailsViewModel,text2, encounterId)
            val text3List = formatter.getObservationList(patientDetailsViewModel,text3, encounterId)

            val observationDataList = merge(text1List, text2List, text3List)


            CoroutineScope(Dispatchers.Main).launch {
                if (observationDataList.isNotEmpty()) {
                    no_record.visibility = View.GONE
                    recycler_view.visibility = View.VISIBLE
                } else {
                    no_record.visibility = View.VISIBLE
                    recycler_view.visibility = View.GONE
                }

                val confirmParentAdapter = ConfirmParentAdapter(observationDataList,this@MaternalSerologyView)
                recyclerView.adapter = confirmParentAdapter
            }

        }


    }

    private fun <T> merge(first: List<T>, second: List<T>, third: List<T>): List<T> {
        val list: MutableList<T> = ArrayList()
        Stream.of(first, second, third).forEach { item: List<T>? -> list.addAll(item!!) }
        return list
    }
}