object Vers {
  val compile_sdk = 28
  val min_sdk = 14
  val target_sdk = 28
}

private const val KOTLIN = "1.3.11"

object Libs {
  val android_plugin = "com.android.tools.build:gradle:3.2.1"
  val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN"
  val dokka_plugin = "org.jetbrains.dokka:dokka-android-gradle-plugin:0.9.16"
  val ktlint_plugin = "gradle.plugin.org.jlleitschuh.gradle:ktlint-gradle:4.1.0"
  val publish_plugin = "com.vanniktech:gradle-maven-publish-plugin:0.6.0"

  val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN"
  val rx_java = "io.reactivex.rxjava2:rxjava:2.2.0"
  val rx_android = "io.reactivex.rxjava2:rxandroid:2.1.0"

  val appcompat = "androidx.appcompat:appcompat:1.0.0"
  val android_annotation = "androidx.annotation:annotation:1.0.0"

  val junit = "junit:junit:4.12"
  val test_runner = "androidx.test:runner:1.1.0"
  val test_rule = "androidx.test:rules:1.1.0"
  val espresso = "androidx.test.espresso:espresso-core:3.1.0"
  val test_junit_runner = "androidx.test.ext:junit:1.0.0"

  val truth = "com.google.truth:truth:0.42"
  val mockito_kotlin = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
  val multidex = "androidx.multidex:multidex:2.0.0"
}
