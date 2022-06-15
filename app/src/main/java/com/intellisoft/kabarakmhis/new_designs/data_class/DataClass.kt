package com.intellisoft.kabarakmhis.new_designs.data_class

import android.service.carrier.CarrierIdentifier
import org.hl7.fhir.r4.model.Appointment

data class DbPatient(
    val resourceType: String,
    val id: String,
    val active: Boolean,
    val name: List<DbName>,
    val telecom: List<DbTelecom>,
    val gender: String,
    val birthDate: String,
    val address: List<DbAddress>,
    val contact: List<DbContact>
)
data class DbContact(
    val relationship: ArrayList<DbRshp>?,
    val name: DbName,
    val telecom: List<DbTelecom>
)
data class DbRshp(
    val text: String
)
data class DbName(
    val family: String,
    val given: List<String>
)
data class DbTelecom(
    val system: String,
    val value: String
)
data class DbAddress(
    val text: String,
    val line: List<String>,
    val city: String,
    val district: String,
    val state:String,
    val country:String
)


data class DbPatientSuccess(
    val resourceType: String,
    val id: String
)
data class DbPatientResult(
    val total: Int,
    val entry: ArrayList<DBEntry>?
)
data class DBEntry(
    val resource: DbResourceData
    )
data class DbResourceData(
    val resourceType: String,
    val id: String,
    val active: Boolean,
    val name: List<DbName>,
    val telecom: List<DbTelecom>,
    val gender: String,
    val birthDate: String,
    val address: List<DbAddress>,
    val contact: List<DbContact>?
)
data class DbEncounter(
    val resourceType: String,
    val id : String,
    val subject : DbSubject,
    val reasonCode: List<DbReasonCode>
)
data class DbSubject(
    val reference: String
)
data class DbEncounterData(
    val reference: String
)
data class DbReasonCode(
    val text: String
)


data class DbEncounterList(
    val total: Int,
    val entry: List<DbEncounterEntry>?
)
data class DbEncounterDetailsList(
    val total: Int,
    val entry: List<DbEncounterDataEntry>?
)
data class DbEncounterDataEntry(
    val resource: DbEncounterDataResourceData
)
data class DbEncounterEntry(
    val resource: DbEncounterResourceData
)
data class DbEncounterResourceData(
    val resourceType: String,
    val id: String,
    val reasonCode: List<DbReasonCode>
)
data class DbEncounterDataResourceData(
    val resourceType: String,
    val id: String,
    val code: DbCode?
)
data class DbObservation(
    val resourceType: String,
    val id: String,
    val subject: DbSubject,
    val encounter: DbEncounterData,
    val code: DbCode
)
data class DbCode(
    val coding: List<DbCodingData>,
    val text: String
)
data class DbCodingData(
    val system: String,
    val code: String,
    val display:String?
)

data class DbObservationValue(
    val valueList: HashSet<DbObservationData>
)
data class DbObservationData(
    val code: String,
    val valueList: HashSet<String>
)

data class DbObserveValue(
    val title: String,
    val value : String
)

data class DbSimpleEncounter(
    var id: String = "",
    var appointmentDate: String = "",
    var notes : String = "",
    var dateCollected : String = ""
)

data class DbPatientData(
    val title: String,
    val data : List<DbDataDetails>
)
data class DbDataDetails(
    val data_value: List<DbDataList>
)
data class DbDataList(
    val code: String,
    val value: String,
    val type: String,
    val identifier: String,

    )

enum class DbResourceType {
    Patient,
    Encounter,
    Observation
}
enum class DbResourceViews {
    MEDICAL_HISTORY,
    PREVIOUS_PREGNANCY,
    PHYSICAL_EXAMINATION,
    NEW_PATIENT_1,
    NEW_PATIENT_2,
    CLINICAL_NOTES,
    PRESENT_PREGNANCY,

    PRESENT_PREGNANCY_1,
    PRESENT_PREGNANCY_2,

    BIRTH_PLAN,

    PATIENT_INFO,
    SURGICAL_HISTORY,
    MEDICAL_DRUG_HISTORY,
    FAMILY_HISTORY,
    ANTENATAL_PROFILE,

    ANTENATAL_1,
    ANTENATAL_2,
    ANTENATAL_3,
    ANTENATAL_4,

    PHYSICAL_EXAMINATION_1,
    PHYSICAL_EXAMINATION_2,

    CHW_1,
    CHW_2,
}
data class DbTypeDataValue(
    val type: String,
    val dbObserveValue: DbObserveValue
)
