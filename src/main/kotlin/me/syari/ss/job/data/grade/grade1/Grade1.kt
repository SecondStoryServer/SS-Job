package me.syari.ss.job.data.grade.grade1

import me.syari.ss.job.data.JobData
import me.syari.ss.job.data.JobGrade

object Grade1: JobGrade {
    override val groupName = "グレード１"

    override val jobList: Map<JobData, Int> = mapOf(
        Warrior to 2,
        Knight to 3,
        Hunter to 4,
        Wizard to 5,
        Priest to 6
    )
}