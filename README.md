# Indigo Examples

Examples of how to build things in Indigo using the latest public release.

There are three sections:

1. Demos
1. Examples
2. How to's!

## Watch out!

If you try and run a number of these demos and examples back to back, you will likely fall foul of your browsers cache sooner or later. A lot of these demos and examples make use of assets and files with similar or identical names, and this can cause cache confusion.

> Consider testing in a private or incognito window, on _not_ your main browser, where you don't mind blowing away the cache from time to time.

## Examples

The examples a minimal references of how to use a range of Indigo's features. They're not always elegant but they are useful.

It's worth noting that these examples were really put together to aid QA testing - rather than for public consumption. For example in a couple of them all the output is actually in your browsers console! PR's and suggestions to make them nicer are very welcome.

An easy way to run the demos it to run `sbt` in the folder, you will then be presented with a welcome message with commands to run all the examples.

To run any example, do the follow (using lighting as an example):

```bash
sbt lighting/fastOptJS lighting/indigoRun
```

The whole lot can be built by running:

```bash
sbt buildExamples
```

As with the demos, you'll need to navigate to the specific demo's build folder and run a local http server.
