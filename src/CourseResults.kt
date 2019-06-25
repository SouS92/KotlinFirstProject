package io.openclassroom

/**
 * @project OCWebServer
 * @author Tonys
 * 25/06/2019
 */

var  activated = {bool: Boolean -> if(bool) "activated" else "Not activated"}
fun Course.plainText() = "Here you course, " + this.title + ", with Status : " + activated(this.activeCourse)