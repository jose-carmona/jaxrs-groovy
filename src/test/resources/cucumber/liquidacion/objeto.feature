# language: es
Característica: el objeto es la base para el cálculo de la liquidación
  Como reponsable de las reglas de cálculo
  Quiero que la liquidación se calcule en base a un "objeto"
  Para generalizar el cálculo de liquidaciones

  El objeto estará diponible en la regla de cálculo como un objeto denominado "o"
  y que tendrá acceso a los valores para poder realizar el cálculo de la
  liquidación.

  Escenario: Acceso al objeto desde la regla de cálculo
    Dado que tenemos un objeto con los siguientes valores:
          | concepto1.ml | 15.0 |
          | concepto2.m2 | 22.5 |
      Y que tenemos la siguiente regla de cálculo:
      """
      # Regla de cálculo

      Regla de cálculo que accede al objeto
      ```
      r.setPrincipal( o["concepto1.ml"] * 2 + o["concepto2.m2"] * 3 )
      ```
      """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un principal igual a 97.5 Euros
