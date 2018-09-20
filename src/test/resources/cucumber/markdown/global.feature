# language: es
Característica: markdown global para herramientas y utilidades
  Como responsable de las reglas de cálculo
  Quiero poder usar markdown global
  Para implementar herramientas y utilidades en groovy

  Se trata de un markdown en el que depositar scripts groovy en los que definir
  herramientas y utilidades que estén disponibles para todas la reglas de cálculo.

  Escenario: Markdown global para definir herramientas y utilidades
    Dado que tenemos el siguiente markdown global:
    """
    # Markdown global

    Vamos a definir una clase que luego se usaremos en la regla de cálculo
    ```
    class Foo {
      static int i
    }

    ```
    """
    Dado que tenemos la siguiente regla de cálculo:
    """
    # Regla de cálculo que usa elementos del markdown global

    ```
    foo = new Foo()

    foo.i = 27

    assert Foo.class.getDeclaredField('i').type == int.class

    foo.i
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces el resultado de la regla de cálculo debe ser "27"
