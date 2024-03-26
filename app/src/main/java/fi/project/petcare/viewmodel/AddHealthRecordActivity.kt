package fi.project.petcare.viewmodel

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import fi.project.petcare.R
import fi.project.petcare.model.data.HealthRecord
import fi.project.petcare.model.data.HealthRecordType
import java.util.*

class AddHealthRecordActivity : AppCompatActivity() {
    private val healthRecordViewModel: HealthRecordViewModel by viewModels()

    private lateinit var inputRecordDetails: TextInputEditText
    private lateinit var addButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_health_record)

        inputRecordDetails = findViewById(R.id.input_record_details)
        addButton = findViewById(R.id.add_button)

        addButton.setOnClickListener {
            val recordDetails = inputRecordDetails.text.toString()
            if (recordDetails.isNotEmpty()) {
                val operationRecord = HealthRecord(
                    HealthRecordType.OPERATION,
                    Date(),
                    recordDetails
                )
                healthRecordViewModel.addHealthRecord(operationRecord)

                val veterinarianRecord = HealthRecord(
                    HealthRecordType.VETERINARIAN_VISIT,
                    Date(),
                    recordDetails
                )
                healthRecordViewModel.addHealthRecord(veterinarianRecord)

                val medicationRecord = HealthRecord(
                    HealthRecordType.MEDICATION,
                    Date(),
                    recordDetails
                )
                healthRecordViewModel.addHealthRecord(medicationRecord)

                val symptomRecord = HealthRecord(
                    HealthRecordType.SYMPTOM,
                    Date(),
                    recordDetails
                )
                healthRecordViewModel.addHealthRecord(symptomRecord)

                val allergyRecord = HealthRecord(
                    HealthRecordType.ALLERGY,
                    Date(),
                    recordDetails
                )
                healthRecordViewModel.addHealthRecord(allergyRecord)

                val exerciseRecord = HealthRecord(
                    HealthRecordType.EXERCISE,
                    Date(),
                    recordDetails
                )
                healthRecordViewModel.addHealthRecord(exerciseRecord)

                val weightRecord = HealthRecord(
                    HealthRecordType.WEIGHT_MEASUREMENT,
                    Date(),
                    recordDetails
                )
                healthRecordViewModel.addHealthRecord(weightRecord)
                finish()
            }
        }
    }
}
