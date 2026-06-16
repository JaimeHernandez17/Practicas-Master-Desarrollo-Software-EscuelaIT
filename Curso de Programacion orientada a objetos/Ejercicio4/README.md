# ProgramacionOO-Ejercicio4-polimorfismo
Ejercicio de Programación Orientada a Objetos para practicar polimorfismo

En este ejercicio se propone la realización de las partes básicas de un juego del ajedrez. En este Juego nos vamos a centrar en el movimiento de las fichas esencial, descartando todas las reglas del juego que puedan tener más complejidad que la básica.

Queremos implementar el juego del ajedrez y el movimiento de sus fichas, comer una ficha y ganar la partida. No se pide realizar todas las fichas del juego, sino solamente un conjunto de ellas que resulte adecuado para poder implementar polimorfismo. No obstante, se sugiere que ese conjunto incluya las siguientes piezas:

- Peones
- Rey
- Caballo

Opcionalmente se puede implementar cualquier otra pieza del juego que se desee.

Los movimientos permitidos de cada ficha, si no los sabéis, los podéis consultar en cualquier manual o vídeo de Internet. Insistimos que nos limitaremos a las condiciones del juego elementales. Por ejemplo, no es necesario considerar situaciones como estas:

- No hay enroque
- Puedes mover las casillas a cualquier posición permitida, aunque el rey se quede desprotegido.
- Los peones no es necesario que se conviertan en ninguna otra pieza al llegar al lado opuesto del tablero
- No hay situaciones de tablas (empate)

## Lenguajes

Para la realización de este juego podemos usar lenguajes con una sintaxis y orientación a objetos similar a Java, trabajando con la consola. Entre los cuales estarían:

- Java
- C#
- Javascript, siempre que sea con programación en consola
  - NodeJS
  - Typescript, Deno
- PHP, siempre que sea programa de consola, nada de interfaces gráficas con HTML
- C++