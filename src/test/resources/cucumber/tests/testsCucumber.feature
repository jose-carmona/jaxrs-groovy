# language: es
Característica: Sistema de test para reglas de cálculo con cucumber
  Como responsable de las reglas de cálculo
  Quiero comprobar que son correctas
  Para asegurar la calidad de mis cálculos

  Escenario: Ejecución de test en cucumber embebidos en la aplicación
    Dado que tenemos la siguiente regla de cálculo:
      """
      # Regla de cálculo simple

      ```
      r.setPrincipal( o["concepto1.ml"] * t["concepto1.precio"] )
      ```
      """
      Y que tenemos el siguiente test:
      """
      # language: es
      Característica: Test
        Como responsable de las reglas de cálculo
        Quiero comprobar que son correctas
        Para asegurar la calidad de mis cálculos

      Escenario:
        Dado que tenemos un objeto con los siguientes valores:
              | concepto1.ml | 15.0 |
          Y que tenemos la siguiente tarifa simple:
              | concepto1.precio | 123.45 |
        Cuando ejecuto la regla de cálculo
        Entonces la liquidación resultado debe tener un principal igual a 100.0 Euros
      """
    Cuando ejecuto el test de la regla de cálculo
    Entonces el resultado del test es correcto