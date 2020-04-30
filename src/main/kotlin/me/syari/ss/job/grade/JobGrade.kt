package me.syari.ss.job.grade

import me.syari.ss.job.grade.grade1.Grade1

interface JobGrade {
    val groupName: String
    val jobList: Map<JobData, Int>

    companion object {
        val gradeList = listOf(Grade1)

        fun getByIndex(index: Int): JobGrade? {
            return gradeList.getOrNull(index)
        }
    }
}