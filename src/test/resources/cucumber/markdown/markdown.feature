# language: es
Característica: Uso de markdown con scripts groovy
  Como responsable de las reglas de cálculo
  Quiero poder usar reglas de cálculo en fichero markdown
  Para conseguir una documentación adecuda de las reglas

  Escenario: Regla de cálculo mínima en markdown
    Dado que tenemos la siguiente regla de cálculo:
    """
    # Regla de cálculo mínima

    Aquí va la regla en Groovy.
    Vamos a hacer que el markdown tenga 2 zonas de script sepadas

    Primero una zona de definición de variables y luego una de resultado

    ### variables
    ahí van:
    ```
    def a = 6
    def b = 4
    ```
    ### resultado
    Calculamos el resultado
    ```
    a + b
    ```
    el resultado de la regla debe ser 10
    """
    Cuando ejecuto la regla de cálculo
    Entonces el resultado de la regla de cálculo debe ser "10"
      Y el script groovy de la regla de cálculo debe ser:
      """
      def a = 6
      def b = 4
      a + b

      """

  Escenario: transformación de Markdown a HTML
    Dado que tenemos la siguiente regla de cálculo:
    """
    # Regla de cálculo mínima
    """
    Entonces el HTML de la regla de cálculo debe ser:
    """
    <h1>Regla de cálculo mínima</h1>

    """

  Escenario: Establecer variables java que puedan ser usadas en la regla de cálculo
    Dado que tenemos la siguiente regla de cálculo:
    """
    # Regla de cálculo con varables
    ```
    (a + b) * 2
    ```
    """
      Y que asignamos a la variable "a" el valor numérico 4
      Y que asignamos a la variable "b" el valor numérico 5
    Cuando ejecuto la regla de cálculo
    Entonces el resultado de la regla de cálculo debe ser "18"
