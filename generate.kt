import java.io.*
import java.lang.reflect.Modifier
import java.net.*
import java.util.*
import java.util.zip.*

val PACKAGES = listOf(
    "jet",
    "kotlin",
    "org.jetbrains.annotations",
    "java.io",
    "java.lang",
    "java.math",
    "java.net",
    "java.nio",
    "java.util"
)

fun <T> Class<out T>.hasAnnotation(fqName: String) =
    getAnnotations() any { it.annotationType().getName().equals(fqName) }

fun process(file: File, processor: (fqName: String) -> Unit) {
    val url = file.toURI().toURL()
    val loader = URLClassLoader(array(url))
    
    val zip = ZipInputStream(url.openStream() ?: throw AssertionError("Couldn't open: $url"))
    while (true) {
        val filename = zip.getNextEntry()?.getName() ?: break
        if (!filename.endsWith(".class")) continue
        
        val internalName = filename.substring(0, filename.length - ".class".length)
        if ("$" in internalName) {
            // Anonymous classes, local function classes, $$TImpl are not needed
            // Also don't consider inner classes, since we're generating a list of simple words
            continue
        }
        val fqName = internalName.replace("/", ".")
        
        // Only consider some predefined packages
        if (!PACKAGES.any { fqName.startsWith("$it.") }) continue
        
        // "data" is handled separately (see kotlin.hrc) because of the high popularity of this word among identifier names
        if (fqName == "jet.data") continue
        
        val c = loader.loadClass(fqName) ?:
            throw AssertionError("Class $fqName is present as a file, but not found via reflection")
        
        // Don't consider classes whose names can't appear in the code
        if (c.hasAnnotation("jet.KotlinPackage")) continue
        if (c.hasAnnotation("jet.KotlinPackageFragment")) continue
        if (c.hasAnnotation("jet.KotlinTraitImpl")) continue
        
        // Only consider public classes
        if (!Modifier.isPublic(c.getModifiers())) continue
        
        processor(fqName)
    }
}

fun generate(jar: String, outputFile: String) {
    PrintWriter(outputFile).use { output ->
        try {
            val names = ArrayList<String>()
            process(File(jar)) { fqName ->
                names add fqName.substring(fqName.lastIndexOf('.') + 1)
            }
            for (name in names.sort()) {
                output.println("<word name=\"$name\"/>")
            }
        } catch (e: AssertionError) {
            e.printStackTrace()
        }
    }
}

fun main(args: Array<String>) {
    generate("D:\\c\\kotlin\\dist\\kotlinc\\lib\\kotlin-runtime.jar", "kotlin-std.ent.hrc")
    generate("C:\\Program Files\\Java\\jdk1.7.0_45\\jre\\lib\\rt.jar", "java-std.ent.hrc")
}
