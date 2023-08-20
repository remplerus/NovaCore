[//]: # (<p align="center">)

[//]: # (    <a href="https://www.curseforge.com/minecraft/mc-mods/ex-nihilo-sequentia"><img src="https://cf.way2muchnoise.eu/full_400012_downloads.svg" /></a>)

[//]: # (    <a href="https://www.curseforge.com/minecraft/mc-mods/ex-nihilo-sequentia"><img src="https://cf.way2muchnoise.eu/versions/400012.svg" /></a>)

[//]: # (    <a href="https://github.com/NovaMachina-Mods/ExNihiloSequentia/blob/1.19/LICENSE"><img alt="GitHub license" src="https://img.shields.io/badge/license-CC%20BY--NC--SA%204.0-brightgreen"></a>)

[//]: # (    <a href="https://github.com/NovaMachina-Mods/ExNihiloSequentia/issues"><img alt="GitHub issues" src="https://img.shields.io/github/issues/NovaMachina-Mods/ExNihiloSequentia"></a>)

[//]: # (    <a href="https://github.com/NovaMachina-Mods/ExNihiloSequentia/stargazers"><img alt="GitHub stars" src="https://img.shields.io/github/stars/NovaMachina-Mods/ExNihiloSequentia"></a>)

[//]: # (</p>)

# NovaCore for Minecraft 1.20

NovaCore is a library mod that supplies common code for all NovaMachina Mods.

# Discord

NovaCore is on the NovaMachina Mods [discord server](https://discord.gg/CJyAkuw) where you can chat with other NovaCore users and the developers.

# Translating

If you would like to help translate NovaCore, please create a pull request with your translation.

# License

[![](https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png)](http://creativecommons.org/licenses/by-nc-sa/4.0/)

The source code of this work is licensed under a [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License](http://creativecommons.org/licenses/by-nc-sa/4.0/).

Artwork of this mod is licensed under an All rights reserved license: Copyright &copy;2023 NovaMachina-Mods All rights reserved.

# Maven

NovaCore is avaliable via the [NovaMachina Mods artifact repository](https://cloudsmith.io/~novamachina-mods/repos/novacore/packages/) for developers wishing to utilize its API.

Add the following to your `build.gradle`:

```groovy
repositories {
    maven {
        url = "https://dl.cloudsmith.io/public/novamachina-mods/novacore/maven/"

    }
}

dependencies {
    implementation(fg.deobf("novamachina.novacore:NovaCore:${novacore_version}"))
}
```

Add the following to your `gradle.properties` (see [Maven](https://cloudsmith.io/~novamachina-mods/repos/novacore/packages/) for the list of available versions):

```properties
novacore_version: 0.1.0-alpha
```

[![Hosted By: Cloudsmith](https://img.shields.io/badge/OSS%20hosting%20by-cloudsmith-blue?logo=cloudsmith&style=for-the-badge)](https://cloudsmith.com)

Package repository hosting is graciously provided by  [Cloudsmith](https://cloudsmith.com).
Cloudsmith is the only fully hosted, cloud-native, universal package management solution, that
enables your organization to create, store and share packages in any format, to any place, with total
confidence.
