package me.syari.ss.job.grade.grade1

import me.syari.ss.job.grade.JobGrade

object Grade1: JobGrade {
    override val groupName = "グレード１"

    override val jobList = mapOf(
        Warrior to 1, Knight to 2, Ranger to 3, Mage to 4, Priest to 5, Fighter to 6, Bard to 7
    )
}