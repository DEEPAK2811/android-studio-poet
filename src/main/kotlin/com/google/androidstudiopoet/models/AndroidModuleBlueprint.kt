/*
Copyright 2017 Google Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.google.androidstudiopoet.models

import com.google.androidstudiopoet.Blueprint
import com.google.androidstudiopoet.utils.joinPath
import com.google.androidstudiopoet.utils.joinPaths

data class AndroidModuleBlueprint(val index: Int,
                                  val numOfActivities: Int,
                                  val numOfStrings: Int,
                                  val numOfImages: Int,
                                  val projectRoot: String,
                                  val hasLaunchActivity: Boolean,
                                  val useKotlin: Boolean,
                                  val dependencies: List<String>,
                                  val productFlavors: List<Int>?): Blueprint {
    val name = "androidAppModule" + index
    val packageName = "com.$name"
    val moduleRoot = projectRoot.joinPath(name)
    val srcPath = moduleRoot.joinPath("src")
    val mainPath = srcPath.joinPath("main")
    val resDirPath = mainPath.joinPath("res")
    val codePath = mainPath.joinPath("java")
    val packagePath = codePath.joinPaths(packageName.split("."))
}
