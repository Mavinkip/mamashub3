package com.kabarak.kabarakmhis.new_designs.screens

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kabarak.kabarakmhis.R
import com.kabarak.kabarakmhis.helperclass.DbMaternalProfileChild
import com.kabarak.kabarakmhis.new_designs.antenatal_profile.AntenatalProfileView
import com.kabarak.kabarakmhis.new_designs.birth_plan.BirthPlanView
import com.kabarak.kabarakmhis.new_designs.chw.referral.ReferralView
import com.kabarak.kabarakmhis.new_designs.clinical_notes.ClinicalNotesList
import com.kabarak.kabarakmhis.new_designs.counselling.CounsellingView
import com.kabarak.kabarakmhis.new_designs.deworming.DewormingView
import com.kabarak.kabarakmhis.new_designs.ifas.IfasList
import com.kabarak.kabarakmhis.new_designs.malaria_propylaxis.MalariaProphylaxisList
import com.kabarak.kabarakmhis.new_designs.matenal_serology.MaternalSerologyView
import com.kabarak.kabarakmhis.new_designs.medical_history.MedicalSurgicalHistoryView
import com.kabarak.kabarakmhis.new_designs.physical_examination.PhysicalExaminationList
import com.kabarak.kabarakmhis.new_designs.pmtct.PMTCTInterventionsView
import com.kabarak.kabarakmhis.new_designs.present_pregnancy.PresentPregnancyList
import com.kabarak.kabarakmhis.new_designs.previous_pregnancy.PreviousPregnancyList
import com.kabarak.kabarakmhis.new_designs.tetanus_diptheria.PreventiveServiceList
import com.kabarak.kabarakmhis.new_designs.weight_monitoring.WeightMonitoringChart


class MaternalProfileChildrenAdapter(private var entryList: ArrayList<DbMaternalProfileChild>,
                                     private val context: Context) : RecyclerView.Adapter<MaternalProfileChildrenAdapter.PagerViewHolder>() {

    inner class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val imgIcon: ImageView = itemView.findViewById(R.id.img_icon)
        val tvName: TextView = itemView.findViewById(R.id.tvName)

        init {

            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View) {

            val pos = adapterPosition
            val id = entryList[pos].id

            when (id) {
                1.1 -> { context.startActivity(Intent(context, PatientDetails::class.java)) }
                1.2 -> { context.startActivity(Intent(context, Summary::class.java)) }

                1.3 -> { context.startActivity(Intent(context, BpMonitoring::class.java)) }
                1.4 -> { context.startActivity(Intent(context, UpcomingAppointments::class.java)) }

                2.1 -> { context.startActivity(Intent(context, MedicalSurgicalHistoryView::class.java)) }
                2.2 -> { context.startActivity(Intent(context, PreviousPregnancyList::class.java)) }

                3.1 -> { context.startActivity(Intent(context, PhysicalExaminationList::class.java)) }
                3.2 -> { context.startActivity(Intent(context, AntenatalProfileView::class.java)) }
                3.3 -> { context.startActivity(Intent(context, PresentPregnancyList::class.java)) }
                3.4 -> { context.startActivity(Intent(context, WeightMonitoringChart::class.java)) }
                3.5 -> { context.startActivity(Intent(context, ClinicalNotesList::class.java)) }

                4.1 -> { context.startActivity(Intent(context, PreventiveServiceList::class.java)) }
                4.2 -> { context.startActivity(Intent(context, MalariaProphylaxisList::class.java)) }
                4.3 -> { context.startActivity(Intent(context, IfasList::class.java)) }
                4.4 -> { context.startActivity(Intent(context, MaternalSerologyView::class.java)) }
                4.5 -> { context.startActivity(Intent(context, PMTCTInterventionsView::class.java)) }
                4.6 -> { context.startActivity(Intent(context, DewormingView::class.java)) }

                5.1 -> { context.startActivity(Intent(context, BirthPlanView::class.java)) }

                6.1 -> { context.startActivity(Intent(context, CounsellingView::class.java)) }

                7.1 -> { context.startActivity(Intent(context, ReferralView::class.java)) }


            }

            Log.e("id", id.toString())


        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PagerViewHolder {
        return PagerViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.maternal_children,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {

        val image = entryList[position].image
        val title = entryList[position].title


        holder.imgIcon.background = image
        holder.tvName.text = title


    }




    override fun getItemCount(): Int {
        return entryList.size
    }

}