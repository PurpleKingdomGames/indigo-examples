# Indigo Examples

Examples of how to build things in Indigo using the latest public release.

There are two sections:

1. Demos
1. Examples

## Watch out!

If you try and run a number of these demos and examples back to back, you will likely fall foul of your browsers cache sooner or later. A lot of these demos and examples make use of assets and files with similar or identical names, and this can cause cache confusion.

> Consider testing in a private or incognito window, on _not_ your main browser, where you don't mind blowing away the cache from time to time.

## Demos

Snake is a Mill project, and assuming you have Mill installed can be built as follows from the `demos/snake` directory:

```bash
mill snake.buildGame
```

The Cursed Pirate is an SBT project, and can be built as follows from the `demos/pirate`:

```bash
sbt buildGame
```

To run either game, check the last line of output for the folder is has been built into, go to that folder in your terminal and run `http-server -c-1`. Then navigate to `http://127.0.0.1:8080/` in your browser of choice*. See [Setup & configuration](https://indigoengine.io/docs/quickstart/setup-and-configuration) for more details.

(* WebGL requirements not withstanding)

### The Cursed Pirate

The Cursed Pirate Demo was originally ~made~ hastily thrown together in about 5 hours for the first [talk that featured / introduced Indigo](https://www.youtube.com/watch?v=SmZETGZKCVU). The idea was to put together something memorable - not a game - just a small, fun demo.

This version is superficially similar, but is in fact almost a total rebuild of the code base.

Indigo had already changed enormously between the talk in Jan 2020 and the first release in June 2020, and has evolved further since.

So one reason for the rebuild was simply to bring it up to date, but the other was that it wasn't a good example of how to build a game. The original version took many shortcuts, focusing on showing off some of the features of Indigo to an audience via a happy path, rather than worrying about being a reasonable example of how one might build a game.

Hopefully this version is a better example for people looking for inspiration or reference.

#### Asset Credits

All assets bought on [itch.io](https://itch.io/).

Music - Tim Beek - [Sea Shanties Pirate Music Pack](https://timbeek.itch.io/seashanties)

Sound Effects - Yal - [Yal's Sound Effect Pack](https://yaru.itch.io/retro-sound-effect-pack-1)

Artwork - Pixel Frog

- [Captain Clown Nose](https://pixel-frog.itch.io/captain-clown-nose)
- [Palm Tree Island](https://pixel-frog.itch.io/palm-tree-island)
- [The Crusty Crew](https://pixel-frog.itch.io/the-crusty-crew)

#### Brilliant Tools Used

[Aseprite](https://dacap.itch.io/aseprite)

[Tiled Map Editor](https://thorbjorn.itch.io/tiled)

### Snake

This Snake implementation was the first full, albeit simple, game made using Indigo.

The code is probably not what we'd do now, but there are some nice aspects to it like the model of the Snake itself and it's tests, and so it's still a useful reference.

## Examples

The examples a minimal references of how to use a range of Indigo's features. They're not always elegant but they are useful.

It's worth noting that these examples were really put together to aid QA testing - rather than for public consumption. For example in a couple of them all the output is actually in your browsers console! PR's and suggestions to make them nicer are very welcome.

The whole lot can be built by running:

```bash
sbt buildExamples
```

As with the demos, you'll need to navigate to the specific demo's build folder and run a local http server.
