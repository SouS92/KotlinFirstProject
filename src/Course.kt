package io.openclassroom

/**
 * @project OCWebServer
 * @author Tonys
 * 25/06/2019
 */

data class Course(var id: Int, var title: String, var complexityLevel: Int, var activeCourse: Boolean){

    companion object {
        fun createInstanceOfCourse(id: Int, title: String, complexityLevel: Int, activeCourse: Boolean) = Course(id,title, complexityLevel, activeCourse)
    }

}