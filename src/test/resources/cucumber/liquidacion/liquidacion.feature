# language: es
Característica: El resultado de una regla de cálculo debe ser una liquidación
  Como programador
  Quiero obtener el resultado de la regla de cálculo como un objeto liquidación
  Para guarda en BD la liquidación resultado.

  Escenario:
    Dado
    Cuando
    Entonces


    Dado que tenemos la siguiente regla de cálculo:
    """
    # Regla de cálculo con liquidación resultado

    El objeto *r* es la liquidación resultado. Establecemos su principal = 100

    ```
    r.setPrincipal(100.0)
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un principal igual a 100
