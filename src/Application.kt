package io.openclassroom

import io.ktor.application.*
import io.ktor.response.*
import com.google.gson.Gson
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*
import com.fasterxml.jackson.databind.*
import io.ktor.jackson.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*

fun main(args: Array<String>): Unit {
    io.ktor.server.netty.EngineMain.main(args)
}

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val listOfCourses: List<Course> = listOf(Course.createInstanceOfCourse(1,"Kotlin : First Steps",1,true),
            Course.createInstanceOfCourse(2,"Spring Certification : all You need to know", 3, false),
            Course.createInstanceOfCourse(3, "Algorithms and data structure", 5, true))
    install(ContentNegotiation) {
        gson {
        }

        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    val client = HttpClient(Apache) {
    }

    val gson = Gson()

    routing {
        get("/") {
            call.respondText("Welcome to OpenClassrooms brand new server !", contentType = ContentType.Text.Plain)
        }

        get("/course/{id}") {
            val id = call.parameters["id"]?: "0"

            when(id.toInt()){
                in 1..3 -> call.respond(gson.toJson(listOfCourses.get(id.toInt() - 1)))

                else -> call.respondText("not found!", contentType =ContentType.Text.Plain)

            }

            }
        get("courseinfo/{id}"){
            val id = call.parameters["id"]?: "0"

            when(id.toInt()){
                in 1..3 -> call.respond(gson.toJson(listOfCourses.get(id.toInt() - 1).plainText()))

                else -> call.respond(gson.toJson(HttpStatusCode.NotFound.description("Sorry! Course not found")))

            }
        }

        get("course/top"){
            call.respond(gson.toJson(listOfCourses.randomItem()))
        }
    }
}

