# language: es
Característica: Los datos de la Liquidación que son importes deben aplicar la reglas de redondeo correctamente
  Como responsable de la reglas de cálculo
  Quiero que los datos de la liquidación que son importes apliquen las reglas de redondeo
  Para no tener que realizar redondeos en las reglas de cálculo.

  Escenario: redondeo del principal de la liquidación hacia abajo
    Dado que tenemos la siguiente regla de cálculo:
    """
    Ojo al redondeo
    ```
    r.setPrincipal(100.00499999)
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un principal igual a 100.00 Euros

  Escenario: redondeo del principal de la liquidación hacia arriba (impar)
    Dado que tenemos la siguiente regla de cálculo:
    """
    Ojo al redondeo
    ```
    r.setPrincipal(100.015)
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un principal igual a 100.02 Euros

  Escenario: redondeo del principal de la liquidación hacia arriba (par)
    Dado que tenemos la siguiente regla de cálculo:
    """
    Ojo al redondeo
    ```
    r.setPrincipal(100.005)
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un principal igual a 100.01 Euros



  Escenario: redondeo de la Base Imponible de la liquidación hacia abajo
    Dado que tenemos la siguiente regla de cálculo:
    """
    Ojo al redondeo
    ```
    r.setBaseImponible(100.00499999)
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener una base imponible igual a 100.00 Euros

  Escenario: redondeo de la Base Imponible de la liquidación hacia arriba (impar)
    Dado que tenemos la siguiente regla de cálculo:
    """
    Ojo al redondeo
    ```
    r.setBaseImponible(100.015)
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener una base imponible igual a 100.02 Euros

  Escenario: redondeo de la Base Imponible de la liquidación hacia arriba (par)
    Dado que tenemos la siguiente regla de cálculo:
    """
    Ojo al redondeo
    ```
    r.setBaseImponible(100.005)
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener una base imponible igual a 100.01 Euros



  Escenario: redondeo dei IVA de la liquidación hacia abajo
    Dado que tenemos la siguiente regla de cálculo:
    """
    Ojo al redondeo
    ```
    r.setBaseImponible(11.14)
    r.setTipoIva(10)
    r.aplicarIva()
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un IVA igual a 1.11 Euros

  Escenario: redondeo dei IVA de la liquidación hacia arriba (impar)
    Dado que tenemos la siguiente regla de cálculo:
    """
    Ojo al redondeo
    ```
    r.setBaseImponible(11.15)
    r.setTipoIva(10)
    r.aplicarIva()
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un IVA igual a 1.12 Euros

  Escenario: redondeo dei IVA de la liquidación hacia arriba (par)
    Dado que tenemos la siguiente regla de cálculo:
    """
    Ojo al redondeo
    ```
    r.setBaseImponible(11.25)
    r.setTipoIva(10)
    r.aplicarIva()
    ```
    """
    Cuando ejecuto la regla de cálculo
    Entonces la liquidación resultado debe tener un IVA igual a 1.13 Euros
