# language: es
Característica: El resultado de una regla de cálculo debe ser una liquidación
  Como programador
  Quiero obtener el resultado de la regla de cálculo como un objeto liquidación
  Para guarda en BD la liquidación resultado.

  Escenario: La regla de cálculo debe contar con un objeto r que equivale a la Liquidación Resultado
    Dado que tenemos la siguiente tarifa simple:
          | concepto1.precio | 123.45 |
          | concepto2.precio | 100.01 |
      Y que tenemos la siguiente regla de cálculo:
      """
      # Regla de cálculo con Tarifa Simple

      La tarifa está disponible mediante el objeto *t* y dispone de un método
      *v* que devuelve el valor que le corresponde al concepto:

      ```
      r.setPrincipal(t.v("concepto1.precio") + t.v("concepto2.precio"))
      ```
      """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un principal igual a 223.46 Euros
