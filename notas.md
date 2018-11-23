Siguiente funcionalidad:
* pojo de liquidación resultado en cliente

# language: es
Característica: POJO liquidación resultado en el lado cliente
  Como integrador
  Quiero un objeto plano java (POJO) que refleje el resultado de la liquidación
  Para que el API sea sencillo de integrar en otras aplicaciones
  Tener en cuenta:
    * servicio jaxrs
    * la liquidación en formato json
    * se traduce a POJO
    * llamada síncrona / asíncrona

  Antecedentes:
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

  Escenario: La liquidación completa en el lado cliente (petición síncrona)
    Cuando ejecuto invoco al api para calcular la liquidación
    Entonces la liquidación en el lado cliente debe tener un principal igual a 600.06 Euros
      Y la liquidación en el lado cliente debe tener el siguiente conjunto de conceptos:
        | Concepto 1 | 100.01 |
        | Concepto 2 | 200.02 |
        | Concepto 3 | 300.03 |
