# language: es
Característica: La liquidación debe contar con soporte para el IVA
  Como reponsable de las reglas de cálculo
  Quiero que la liquidación calcule el Principal a partir de la Base Imponible
    aplicándole el tipo de IVA correspondiente
  Para simplificar las reglas de cálculo que necesiten IVA

  Escenario: La liquidación debe calcular el principal a partir de la Base Imponible aplicando el IVA
    Dado que tenemos la siguiente regla de cálculo:
    """
    # Regla de cálculo de liquidación con IVA

    El Principal de Calcula a partir de la Base Imponible aplicando el tipo
    de IVA

    ```
    r.setBaseImponible(12345.67)
    r.setTipoIva(21)
    r.aplicarIva()
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un IVA igual a 2592.59 Euros
      Y la liquidación resultado debe tener un principal igual a 14938.26 Euros
