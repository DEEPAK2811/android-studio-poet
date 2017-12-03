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

package com.google.androidstudiopoet

import com.google.androidstudiopoet.generators.BuildGradleGenerator
import com.google.androidstudiopoet.generators.PackagesGenerator
import com.google.androidstudiopoet.generators.android_modules.*
import com.google.androidstudiopoet.generators.packages.JavaGenerator
import com.google.androidstudiopoet.generators.packages.KotlinGenerator
import com.google.androidstudiopoet.generators.project.GradleSettingsGenerator
import com.google.androidstudiopoet.generators.project.ProjectBuildGradleGenerator
import com.google.androidstudiopoet.writers.AndroidModuleWriter
import com.google.androidstudiopoet.writers.FileWriter
import com.google.androidstudiopoet.writers.SourceModuleWriter

object Injector {

    private val fileWriter = FileWriter()
    private val dependencyValidator = DependencyValidator()
    private val amBuildGradleGenerator = AndroidModuleBuildGradleGenerator(fileWriter)
    private val buildGradleGenerator = BuildGradleGenerator()
    private val gradleSettingsGenerator = GradleSettingsGenerator(fileWriter)
    private val projectBuildGradleGenerator = ProjectBuildGradleGenerator(fileWriter)
    private val stringResourcesGenerator: StringResourcesGenerator = StringResourcesGenerator(fileWriter)
    private val imageResourcesGenerator: ImagesGenerator = ImagesGenerator(fileWriter)
    private val layoutResourcesGenerator: LayoutResourcesGenerator = LayoutResourcesGenerator(fileWriter)
    private val javaGenerator: JavaGenerator = JavaGenerator(fileWriter)
    private val kotlinGenerator: KotlinGenerator = KotlinGenerator(fileWriter)
    private val activityGenerator: ActivityGenerator = ActivityGenerator(fileWriter)
    private val manifestGenerator: ManifestGenerator = ManifestGenerator(fileWriter)
    private val proguardGenerator: ProguardGenerator = ProguardGenerator(fileWriter)

    private val androidModuleGenerator =
            AndroidModuleWriter(
                    stringResourcesGenerator,
                    imageResourcesGenerator,
                    layoutResourcesGenerator,
                    javaGenerator,
                    kotlinGenerator,
                    activityGenerator,
                    manifestGenerator, proguardGenerator,
                    amBuildGradleGenerator,
                    fileWriter)

    private val packagesGenerator =
            PackagesGenerator(javaGenerator, kotlinGenerator)

    val modulesWriter =
            SourceModuleWriter(dependencyValidator, buildGradleGenerator, gradleSettingsGenerator,
                    projectBuildGradleGenerator, androidModuleGenerator, packagesGenerator, fileWriter)
}
