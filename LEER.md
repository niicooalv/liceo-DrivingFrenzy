# DrivingFrenzy

This is a vehicles speed competition. Several vehicles run individually in a track to do the best time.
From the Main menu in the ControlCenter you should launch one of the types of races (currently, only simpleRandomRace is implemented).

The main parts in this project are:
- ControlCenter, located in the ``main`` package. It is the one that has a ``main`` method and allows us to run races.
- ``Vehicle``, located in the ``vehicles`` package. It is an interface showing the methods that the ControlCenter can use to simulate how the vehicles drive.
    - In the same package there is a ``Scooter`` class, implementing this interface, as an example.
- ``Track``, located in the ``race`` package. It is just a placeholder for ``Section`` objects - each Track will have one or more sections (think of sections as the different turns and straight roads that a real track could have)
    - In the same package there is a ``Section`` interface defining which operations a section should have. There is also a ``StandardIndoorSection`` which represents just a simple road.
    - **The max speed in each section is not defined by the vehicle, but by the section. I.e, the max speed in a section is the same for a Ferrari and Go-Go-Action Bronco.**

Note that several of the interfaces define utility methods to return a description; this is used from the `ControlCenter` to print information in command line.

The goals of this example are to:
- show the use of interfaces - note that to actually do the competition we only need to access to "Vehicle" objects, we do not need to declare them as Scooter or any other vehicle type.
- show how we can create different objects of the same Class with different characteristics.
- show how we can use objects between functions.


## The Control Center
The control center simulates careers. After the track and Vehicles are defined, it will:
1. Choose the next vehicle.
2. Ask the vehicle to adapt its speed by giving the vehicle information about the next section. **The vehicles must only use the theoretical max speed of the section to adapt**.
3. Check what is the new speed of the vehicle. That speed will be used for that specific section.
4. Go back to point 2 until there are not any sections left.
5. Go back to point 1 until there are not any vehicles left.
6. Show the result (total time) of each vehicle.

Also, the Control Center will print information about the drivers and the track, and how each vehicle is doing.

The assignment follows; **the code must be properly commented**.

## Setup and Evaluation
The assignment is self-driven and self-paced.
- Each student will be assigned to a random group, each group will have 3 students.
- Only one laptop is allowed per group. Students will take turns to complete the assignmenttogether. This is an adaptation of [Pair programming](https://www.codementor.io/pair-programming).
- The steps of the assignment are sequential.
- At certain points the students will be questioned to evaluate the job done so far.
> These checkpoints are quoted as this line.
- It is not intended that the students reach the end of the assignment; the goal is to reach as far as possible.

## Pasos de la tarea
### Pasos iniciales

- Ejecute la aplicación varias veces tal como está e intente seguir el código para comprender cómo funciona.
- Crear un diagrama de clases UML del proyecto actual. Sea específico sobre la visibilidad de métodos y variables.
> Los diagramas serán revisados y evaluados. Todos los miembros del grupo deben explicar la ración que se les pide.
- Cree un método llamado ``defaultRace`` en el `ControlCenter`. Esta es una versión más simple del método ``simpleRandomRace``, pero en este caso todo está predefinido (no aleatorio):
    - Disponemos de una pista con 5 tramos de 1000, 2000, 3000, 2000 y 1000 metros de longitud; Puedes elegir la velocidad máxima.
    - Disponemos únicamente de 3 vehículos, serán Scooters. Proporcione a los vehículos descripciones y conductores adecuados.
- Corre la carrera.
> El código será revisado. Las preguntas sobre el código se harán de forma aleatoria a los estudiantes.
- Ordene los resultados para que imprimamos los diferentes vehículos/conductores en orden, por posición. Para ello se puede modificar el método ``start`` justo en la última parte, la que tiene el comentario ``MODIFICA ESTO para mostrar los resultados ordenados por tiempo total.``
- Implementar un nuevo tipo de vehículo. Este vehículo es un Kart de 2 marchas. Antes de codificarlo, agréguelo a su diagrama UML.
    - Cada marcha tendrá una velocidad mínima y máxima.
    - Al llamar a `adaptSpeed`, el Kart sólo puede hacer una de las siguientes cosas:
        - Aumentar o disminuir la velocidad dentro de la marcha actual.
        - Subir una marcha (la nueva velocidad será la velocidad mínima de la segunda marcha).
        - Bajar una marcha (la nueva velocidad puede ser cualquiera dentro del rango de la primera marcha).
- Crea una nueva carrera (es decir, un nuevo método en el Centro de control) y prueba el nuevo Kart.
> El código será revisado. Las preguntas sobre el código se harán de forma aleatoria a los estudiantes.

### Próximos pasos
- Crea una carrera en la que compitan juntos Scooters y Karts. Recuerde, **el método ``start`` no se puede modificar en este momento**.
    - Elija 3 scooters reales de una marcha y 3 karts reales de 2 marchas para obtener estadísticas reales sobre la velocidad; no pierda más de 10 minutos buscando; de lo contrario, simplemente invente los números.
- Cree un nuevo tipo de ``Sección`` llamada ``StandardOutsideSection``. Este tiene su velocidad máxima teórica, pero también la real. El real es sólo un porcentaje del teórico.
    - Por ejemplo, piensa en un día lluvioso. La velocidad máxima real podría ser ``0,7 * maxSpeed``. O piense en un día muy soleado; la velocidad máxima real podría ser ``1.1 * maxSpeed``.
    - Esto no es aleatorio. Al crear el Objeto se debe proporcionar el porcentaje y también una descripción adecuada.
> El código será revisado. Las preguntas sobre el código se harán de forma aleatoria a los estudiantes.
- **A partir de este momento, puedes modificar el método de inicio según sea necesario.**
- Modificar el método ``start``: si un vehículo supera la velocidad máxima real de un tramo, se sale de la pista y queda descalificado.
- Al enumerar las posiciones, también deberá mostrar los vehículos descalificados y en qué tramo fueron descalificados.
- Corre una nueva carrera de Scooters + Karts, pero con secciones tanto interiores como exteriores.
> El código será revisado. Las preguntas sobre el código se harán de forma aleatoria a los estudiantes.

### Pasos finales
- Implementar cierta aleatorización en la velocidad de los vehículos para simular que los conductores a veces cometen errores. Al adaptar la velocidad, todos los vehículos añaden un factor aleatorio a la velocidad multiplicándola por un número aleatorio entre 0,8 y 1,0.
    - No implementar esto directamente en todos los vehículos. Encuentre una manera de implementarlo solo una vez (herencia). Además, haga que el código sea menos redundante; es probable que Scooter y Kart tengan atributos y métodos en común.
- Automatizar la creación de una pista larga, de al menos 50 kilómetros con 5 tramos. Debe ser aleatorio y combinar diferentes tipos de secciones.
> El código será revisado. Las preguntas sobre el código se harán de forma aleatoria a los estudiantes.
- Modificar las secciones para que proporcionen información adicional a los conductores. Una sección ofrecerá un método `getVariationLevel` que será -1 si de alguna manera está afectado y los vehículos deben ir más lento, 0 si es estándar o 1 si los vehículos pueden ir más rápido. No proporcione el modificador exacto que se está aplicando...
    - Hazlo similar al último cambio de vehículos.
- Implementar un nuevo tipo de vehículos de 6 marchas, coches normales. Además, estos coches pueden utilizar la información de "getVariationLevel".
    - Implementar el método ``adaptSpeed`` similar al de los Karts. Sin embargo, el vehículo cuenta con un nuevo atributo `driverStyle` que puede ser estándar, agresivo o conservador.
        - Un conductor agresivo intentará jugar con las probabilidades de ir más rápido que la velocidad máxima teórica. Por ejemplo, si el nivel de variación es positivo, será agresivo, si es 0 seguirá siendo agresivo para compensar su propia velocidad de aleatorización (ver punto 1).
        - Un piloto conservador siempre intentará evitar salirse de la pista.
- Un conductor estándar tendrá un comportamiento medio.
    - Pruébalo en una carrera.
> El código será revisado. Las preguntas sobre el código se harán de forma aleatoria a los estudiantes.
- Modifica el código completo para que los vehículos compitan simultáneamente y añade menciones de comentaristas sobre cuándo un coche adelanta a otro, etc. ¡Hazlo real!
