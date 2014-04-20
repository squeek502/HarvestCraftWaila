HarvestCraftWaila
=================

Minecraft mod that fixes some of Pam's HarvestCraft issues with Waila tooltips

[Minecraft Forum Thread](http://www.minecraftforum.net/topic/2509840-)

###Building

1. Clone this repository into a folder named HarvestCraftWaila
2. Create a folder called 'libs' inside the HarvestCraftWaila folder and put [HarvestCraft](http://www.minecraftforum.net/topic/402069-) and [Waila](http://minecraft.curseforge.com/mc-mods/waila/files/) [for 1.6.4] in it
3. If you have [Gradle](http://www.gradle.org/) installed, open a command line in the HarvestCraftWaila folder and execute: ```gradle build```. To give the build a version number, use ```gradle build -Pversion=<version>``` instead (example: ```gradle build -Pversion=1.0.0```)
 * If you don't have Gradle installed, you can use [ForgeGradle](http://www.minecraftforge.net/forum/index.php?topic=14048.0)'s gradlew/gradlew.bat instead
