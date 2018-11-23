# language: es
Característica: La reglas de cálculo deben acceder a datos de tarifas
  Como responsable de las reglas de cálculo
  Quiero acceder a los datos de tarifas
  Para que dichos datos no estén dentro de las reglas de cálculo y, por tanto,
    las reglas de cálculo no necesiten modificarse cada vez que se actualicen
    los precios y/o límites.

  Escenario: La regla de cálculo sencialla accesible como objeto "t"
    Dado que tenemos la siguiente tarifa simple:
          | concepto1.precio | 123.45 |
          | concepto2.precio | 100.01 |
      Y que tenemos la siguiente regla de cálculo:
      """
      # Regla de cálculo con Tarifa Simple

      La tarifa simple está disponible mediante el objeto Map *t*:

      ```
      r.setPrincipal( t["concepto1.precio"] + t["concepto2.precio"] )
      ```
      """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un principal igual a 223.46 Euros
