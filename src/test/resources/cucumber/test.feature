# language: es
Característica: Uso de cucumber
  Como programador
  Quiero servicio test
  Para comprobar que el sistema de test funciona

  Hemos implementado un servicio 'test' que invoca un pequeño scrpit groovy que
  devuelve {"result":"4"}. Esto debe servir para comprobar que funciona:
    - REST sobre jaxrs
    - cucumber
    - rest-assured
    - resultado en json

  Escenario: El test debe responder un result = 4
    Cuando invoco al test
    Entonces el API debe devolver un statusCode 200
      Y el resultado debe ser "4"
