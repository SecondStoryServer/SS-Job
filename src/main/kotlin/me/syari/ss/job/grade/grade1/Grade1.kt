package me.syari.ss.job.grade.grade1

import me.syari.ss.job.grade.JobGrade

object Grade1: JobGrade {
    override val groupName = "グレード１"

    override val jobList = mapOf(
        Warrior to 2, Knight to 3, Ranger to 4, Mage to 5, Priest to 6
    )
}