# language: es
Característica: La Liquidación permitirá asociar un conjunto de conceptos
  Como reponsable de las reglas de cálculo
  Quiero guardar asociada a la liquidación aquellos conceptos que componen el total
  Para aumentar la claridad de la explicación del resultado de la liquidación

  Escenario: La liquidación pemitirá asociar un mapa de conceptos
    Dado que tenemos la siguiente regla de cálculo:
    """
    # Regla de cálculo con conceptos

    ```
    r.c << [ "Concepto 1" : 100.01]
    r.c << [ "Concepto 2" : 200.02]
    r.c << [ "Concepto 3" : 300.03]

    r.setPrincipal(r.c.collect().value.sum())
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un principal igual a 600.06 Euros
      Y la liquidación debe tener el siguiente conjunto de conceptos:
        | Concepto 1 | 100.01 |
        | Concepto 2 | 200.02 |
        | Concepto 3 | 300.03 |
