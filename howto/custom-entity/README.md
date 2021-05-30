# "Blank Indigo Game Template for Mill"

A template and aid to memory to bootstrap your game.

> Everything is referred to as "my game", you'll need to replace those values with the name of your game.

## Contents

There are three source classes, one for each project type. The sandbox one is active while the others are commented out, you should remove whichever you don't need.

## Running the game

The easiest way to run the game is with the following command:

```bash
mill mygame.runGame
```

Alternatively, assuming you have [Mill](http://www.lihaoyi.com/mill/) and http-server set up as the [set up guide](https://indigoengine.io/docs/quickstart/setup-and-configuration) suggests, to run the demo, do the following from your command line:

```bash
mill mygame.buildGame
```

Which will generate output similar to:

```bash
> mill mygame.buildGame
(...)
[44/46] mygame.indigoBuild
dirPath: /Users/(...)/mygame/out/mygame/indigoBuild/dest
Copying assets...
/Users/(...)/mygame/out/mygame/indigoBuild/dest/index.html
[46/46] mygame.buildGame
```

Then:

1. `cd /Users/(...)/mygame/out/mygame/indigoBuild/dest/`
2. `http-server -c-1`
3. Navigate to [http://127.0.0.1:8080/](http://127.0.0.1:8080/) in your browser of choice.
