# Conversor de Monedas
Un simple conversor de monedas que utiliza https://www.exchangerate-api.com/ como API.  
La aplicación lee "YOUR-API-KEY" del archivo ".APIKEY" ubicado en la raíz (https://github.com/julian-gaitan/Conversor-de-Monedas/blob/7fd8d92e6a70db04ff1cd96015f2a392401fade5/src/main/java/org/challengeone/models/API_Response.java#L28), así que se debe suministrar una para el correcto funcionamiento.  
Para ingresar una monead (ya sea la de origen o destino) se puede ingresar el código https://en.wikipedia.org/wiki/ISO_4217 o buscar por la moneda o el país, la aplicación hará una búsqueda y arrogará resultados relevantes

# Ejemplo

## Paso 1

Se puede ingresar directamente a hacer una conversión o se pueden listar las diferentes monedas disponibles.  

![alt start](https://github.com/julian-gaitan/Conversor-de-Monedas/blob/main/images/start.jpg?raw=true)

## Paso 2

Digitamos la moneda BASE, si se busca por palabras clave como "dollar" se debe seleccionar la opción que realmente deseamos.  

![alt base currency](https://github.com/julian-gaitan/Conversor-de-Monedas/blob/main/images/baseCurrency.jpg?raw=true)

## Paso 3

Digitamos la moneda OBJETIVO, si la búsqueda solo arroja un resultado se debe confirmar de igual forma.  

![alt target currency](https://github.com/julian-gaitan/Conversor-de-Monedas/blob/main/images/targetCurrency.jpg?raw=true)

## Paso 4

Digitamos la cantidad para hacer la conversión.  

## Resultado

Finalmente se obtiene el resultado de los datos indicados.  

![alt result](https://github.com/julian-gaitan/Conversor-de-Monedas/blob/main/images/result.jpg?raw=true)
