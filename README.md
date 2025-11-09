<h2>ViewModel</h2>

Fuente: https://developer.android.com/topic/libraries/architecture/viewmodel?hl=es-419

La clase ViewModel es una lógica de negocio o un contenedor de estado a nivel de pantalla.

Expone el estado a la interfaz de usuario y encapsula la lógica de negocio relacionada.

Su principal ventaja es que almacena en caché el estado y lo conserva durante los cambios de configuración.
Esto significa que la interfaz de usuario no tiene que recuperar datos cuando navegas entre actividades o si se producen cambios de configuración, como cuando se rota la pantalla.

Cuando se usa Jetpack Compose, ViewModel es el medio principal para exponer el estado de la interfaz de usuario de la pantalla a los elementos componibles.
En una app híbrida, las actividades y los fragmentos simplemente alojan las funciones de componibilidad.

Para importar ViewModel a un proyecto de Android, hay que declarar las dependencias en el archivo build.gradle del módulo.

<pre>
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
</pre>

ViewModel incluye compatibilidad con corrutinas de Kotlin. Puede conservar el trabajo asíncrono de la misma manera que conserva el estado de la interfaz de usuario.

Es importante crear el objeto ViewModel de forma correcta para que su ciclo de vida tenga el alcance esperado.

<pre>
val viewModel: CounterViewModel = viewModel(
    factory = object : ViewModelProvider.Factory {
        override fun &lt;T : ViewModel&gt; create(modelClass: Class&lt;T&gt;): T {
            return CounterViewModel() as T
        }
    }
)
</pre>

En realidad, en el caso de que el ViewModel no recibe ningún parámetro, también se puede crear de forma más simplificada.

<pre>
val viewModel: CounterViewModel = viewModel()
</pre>

Otra opción bastante común es que se tenga que pasar el contexto al ViewModel.

<pre>
class FileViewModel(private val context: Context): ViewModel() { ...
</pre>

En este caso se puede obtener el contexto dentro de la función composable y después invocar la factoría
pasándole el constructor con el parámetro del contexto.

<pre>
val context = LocalContext.current
val viewModel: FileViewModel = viewModel(
    factory = object : ViewModelProvider.Factory {
        override fun &lt;T : ViewModel&gt; create(modelClass: Class&lt;T&gt;): T {
            return FileViewModel(context) as T
        }
    }
)
</pre>

Con <strong>MutableStateFlow</strong> se crea el estado observable dentro del ViewModel. Para encapsular la variable observable se expone una versión inmutable.

<pre>
private val _count = MutableStateFlow(0)
val count: StateFlow<Int> = _count
</pre>

Para conectar el ViewModel con la interfaz de usuario de Jetpack Compose se ha de acceder a la variable observable inmutable desde la función Composable.

<pre>
val count by viewModel.count.collectAsState()
</pre>

El método <strong>collectAsState()</strong> convierte el flujo (StateFlow) en un estado de Compose.
El operador <strong>by</strong> sirve para delegar el acceso al valor, de modo que se pueda usar count directamente como una variable normal.
