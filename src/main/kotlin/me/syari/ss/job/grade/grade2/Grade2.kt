package me.syari.ss.job.grade.grade2

import me.syari.ss.job.grade.JobGrade

object Grade2: JobGrade {
    override val groupName = "グレード２"

    override val jobList = mapOf(
        SwordMan to 0, Berserker to 1, Paladin to 2
    )
}