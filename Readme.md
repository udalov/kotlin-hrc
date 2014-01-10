# kotlin-hrc

This is a [Kotlin](http://github.com/JetBrains/kotlin) syntax scheme for [Far Manager](http://farmanager.com)'s [colorer-take5](http://colorer.sf.net) plugin

Note that starting from **Far 3.0 build 3765** (10 Jan 2014) this syntax scheme is a part of the official Colorer plugin, distributed with the Far Manager itself. This repository is kept in sync with the official Colorer repository, located [here](https://github.com/colorer/Colorer-schemes).

## Installation (Far prior to 3.0b3765)

Far Manager and Colorer plugin **should be installed** before installing this color scheme.

1. Find your Colorer installation directory (default name is usually `farcolorer-take5.be5` or `FarColorer`) under Plugins directory.
   
   E.g. `C:\Program Files\Far\Plugins\farcolorer-take5.be5`

2. Download all the `*.hrc` files from this repository and save them to `hrc` folder of Colorer's home.

3. Insert the following lines into `hrc/proto.hrc`:

   ```xml
   <prototype name="kotlin" group="main" description="Kotlin">
     <location link="kotlin.hrc"/>
     <filename>/\.(kt|jet)$/i</filename>
   </prototype>
   ```
   
   Insert those anywhere near the other prototypes, keeping the correct structure of the document.

4. Restart Far Manager

##### Enjoy!
