# kotlin-hrc

This is a [Kotlin](http://github.com/JetBrains/kotlin) color scheme for [Far Manager](http://farmanager.com)'s [colorer-take5](http://colorer.sf.net) plugin

## Installation

Far Manager and Colorer plugin **should be installed** before installing this color scheme.

1. Find your Colorer installation directory (default name is `farcolorer-take5.be5`) under Plugins directory.
   
   E.g. `C:\Program Files\Far\Plugins\farcolorer-take5.be5`

2. Download the `kotlin.hrc` file and save it to `hrc` folder of Colorer's home.

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
