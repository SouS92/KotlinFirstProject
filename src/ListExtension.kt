package io.openclassroom

import kotlin.random.Random

/**
 * @project OCWebServer
 * @author Tonys
 * 25/06/2019
 */

fun List<Course>.randomItem() = this.get(Random.nextInt(0,3))