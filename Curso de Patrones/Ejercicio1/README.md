# Práctica 1 - Curso de Patrones de Diseño
# Undo / Redo

Realizar el juego del Mastermind con la funcionalidad de Undo / Redo.

Esta práctica se debe realizar siguiendo la analogía del juego de TicTacToe, en la versión Undo / Redo que hemos visto en la clase 9 del [curso de Patrones de Diseño](https://escuela.it/cursos/patrones) de EscuelaIT.

Para la realización de la práctica vamos a partir de una base de código que se encuentra en este mismo repositorio. Este es el código del Mastermind con la técnica del doble despacho, sobre el que agregaremos los patrones y funcionalidad necesaria para construir el Undo / Redo con un diseño correcto.

Recuerda que los requisitos y otras informaciones relativas al juego de Mastermind los hemos visto en los cursos anteriores (Curso de diseño y anteriores). Los [requisitos de esta versión se pueden ver en este enlace](https://github.com/USantaTecla-project-mastermind/requirements/tree/master/3.UndoRedo).

A continuación encuentras el README que había en el repositorio original, de USantaTecla, que se ha copiado en este repositorio para la práctica.

<h1 align="center">Welcome to Master Mind. Solución 4.2. ** dv.+doubleDispathing **
 👋</h1>
<p>
  <a href="/docs" target="_blank">
    <img alt="Documentation" src="https://img.shields.io/badge/documentation-yes-brightgreen.svg" />
  </a>
  <a href="#" target="_blank">
    <img alt="License: MIT" src="https://img.shields.io/badge/License-MIT-yellow.svg" />
  </a>
</p>

> Master Mind. Solución dv.withdoubleDispathing

## Install

```sh
mvn install
```

## Usage

For Console edition:

Edit pom.xml line 42 for this:

```sh
<mainClass>usantatecla.mastermind.ConsoleMastermind</mainClass>
```
And then:

```sh
mvn clean
mvn package
java -jar target/mastermind-1.0-SNAPSHOT.jar
```

For Graphics edition:

Edit pom.xml line 42 for this:

```sh
<mainClass>usantatecla.mastermind.GraphicsMastermind</mainClass>
```
And then:

```sh
mvn clean
mvn package
java -jar target/mastermind-1.0-SNAPSHOT.jar
```

## Run tests

```sh
mvn test
```

## Author

👤 **USantaTecla**

* Github: [@USantaTecla](https://github.com/USantaTecla)


Note that you should have to [install JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) and [Maven](https://maven.apache.org/install.html) as prerequisite.

---

## Cambios Aplicados para Implementar Undo/Redo

Para implementar la funcionalidad de **Undo/Redo** se aplicó el **Patrón Command** siguiendo los siguientes cambios:

### 1. Interfaz `Command`
Se creó la interfaz `Command` que define los métodos `execute()` y `undo()`, estableciendo el contrato para todos los comandos ejecutables y reversibles.

```java
public interface Command {
    void execute();
    void undo();
}
```

### 2. Clase `ProposedCombinationCommand`
Implementación concreta de `Command` que encapsula la acción de agregar una combinación propuesta. El método `execute()` añade la combinación al juego, mientras que `undo()` la elimina.

### 3. Clase `CommandRegistry`
Gestiona el historial de comandos mediante dos pilas (`Stack`):
- **undoStack**: Almacena los comandos ejecutados para poder deshacerlos.
- **redoStack**: Almacena los comandos deshechos para poder rehacerlos.

Incluye métodos:
- `execute(Command)`: Ejecuta un comando y lo guarda en undoStack.
- `undo()`: Deshace el último comando y lo mueve a redoStack.
- `redo()`: Rehace el último comando deshecho y lo mueve a undoStack.
- `isUndoable()` / `isRedoable()`: Verifican si es posible hacer undo/redo.

### 4. Modificación de `Game`
Se añadió el método `removeLastProposedCombination()` que permite revertir la última jugada, eliminando la última combinación propuesta y su resultado asociado.

### 5. Modificación de `ProposalController`
Se integró el `CommandRegistry` y se añadieron los métodos:
- `undo()`: Delega al CommandRegistry.
- `redo()`: Delega al CommandRegistry y verifica el estado del juego.
- `isUndoable()` / `isRedoable()`: Exponen el estado del historial.

### Diagrama de Clases Simplificado

```
┌──────────────────┐       ┌─────────────────────────────┐
│    <<interface>> │       │     CommandRegistry         │
│      Command     │◄──────│ - undoStack: Stack<Command> │
├──────────────────┤       │ - redoStack: Stack<Command> │
│ + execute()      │       ├─────────────────────────────┤
│ + undo()         │       │ + execute(Command)          │
└────────▲─────────┘       │ + undo()                    │
         │                 │ + redo()                    │
         │                 │ + isUndoable(): boolean     │
┌────────┴─────────────┐   │ + isRedoable(): boolean     │
│ProposedCombination   │   └─────────────────────────────┘
│      Command         │
├──────────────────────┤
│ - game: Game         │
│ - colors: List<Color>│
├──────────────────────┤
│ + execute()          │
│ + undo()             │
└──────────────────────┘
```

Este diseño sigue el principio de **responsabilidad única**, donde cada clase tiene una única responsabilidad, y el principio de **abierto/cerrado**, permitiendo agregar nuevos tipos de comandos sin modificar el código existente.
