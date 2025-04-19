import xyz.jpenilla.resourcefactory.bukkit.BukkitPluginYaml

plugins {
  java
  `java-library`

  id("io.papermc.paperweight.userdev") version "1.7.1"
  id("xyz.jpenilla.resource-factory-bukkit-convention") version "1.1.1"
  id("xyz.jpenilla.run-paper") version "2.3.0"

  id("io.github.goooler.shadow") version "8.1.7"
}

description = "A simple freeze wand plugin, which allows admins and staff to freeze players by right-clicking them with a custom stick item."

group = "dev.enderman"
version = "1.0.0-SNAPSHOT"

val javaVersion = 21
val minecraftVersion = "1.21"

java {
  toolchain.languageVersion = JavaLanguageVersion.of(javaVersion)
}

repositories {
    mavenCentral()
}

dependencies {
  paperweight.paperDevBundle("$minecraftVersion-R0.1-SNAPSHOT")

  implementation("dev.jorel" , "commandapi-bukkit-shade-mojang-mapped" , "9.5.1")
}

tasks {
  build {
    dependsOn(shadowJar)
  }

  compileJava {
    options.release = javaVersion
  }

  javadoc {
    options.encoding = Charsets.UTF_8.name()
  }
}

bukkitPluginYaml {
  name = "FreezeWand"
  description = project.description

  authors = listOf("Esoteric Enderman")

  website = "https://github.com/esotericenderman/freeze-wand"

  version = project.version.toString()
  apiVersion = minecraftVersion
  main = "$group.minecraft.plugins.freeze.${name.get()}Plugin"
  load = BukkitPluginYaml.PluginLoadOrder.STARTUP
}
