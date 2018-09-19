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
    def c = [:]
    c << [ "Concepto 1" : 100.01]
    c << [ "Concepto 2" : 200.02]
    c << [ "Concepto 3" : 300.03]

    def p1 = c.collect()
    def p2 = p1.value

    r.setPrincipal(p2.sum())
    r.setConceptos(c)

    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un principal igual a 600.06 Euros
      Y la liquidación debe tener el siguiente conjunto de conceptos:
        | Concepto 1 | 100.01 |
        | Concepto 2 | 200.02 |
        | Concepto 3 | 300.03 |
