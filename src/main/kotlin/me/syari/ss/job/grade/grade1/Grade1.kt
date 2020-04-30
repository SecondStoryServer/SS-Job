package me.syari.ss.job.grade.grade1

import me.syari.ss.job.grade.JobGrade

object Grade1: JobGrade {
    override val groupName = "グレード１"

    override val jobList = mapOf(
        Warrior to 0, Knight to 1, Ranger to 2, Mage to 3, Priest to 4, Fighter to 5
    )
}